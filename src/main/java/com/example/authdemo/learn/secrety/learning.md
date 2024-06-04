# 记录Security-oauth2源码使用流程

## 背景介绍

手上一个项目需要以SP的身份实现使用OAuth2协议接入SSO单点登陆系统，项目上已经支持了SAML。项目上有很多协议实现是自己写的，真是深受其害啊。所以这次直接使用spring-boot-secrety。

maven依赖：
```xml
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-oauth2-client</artifactId>
</dependency>
```
## 授权请求流程

在网上翻了翻教程感觉都很基础，由于接口和一些逻辑需要自定义，所以只能翻源码。

一开始没有头绪不知道从哪里入手，后来下翻网站了解到security有默认实现，感觉应该是通过拦截器实现的，所以到依赖包里搜索了filter，果然在web目录下发现了流程入口。

```java
package org.springframework.security.oauth2.client.web;
public class OAuth2AuthorizationRequestRedirectFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            // 1 决定是否需要开始授权请求
            OAuth2AuthorizationRequest authorizationRequest = this.authorizationRequestResolver.resolve(request);
            if (authorizationRequest != null) {
                // 2 开始进行授权流程
                this.sendRedirectForAuthorization(request, response, authorizationRequest);
                return;
            }
        } catch (Exception ex) {
            this.unsuccessfulRedirectForAuthorization(request, response, ex);
            return;
        }
        // 后面和调用链有关，这里不在展示
    }
}
```

看到这个类继承了OncePerRequestFilter，根据类名大概知道如果把这个filter注册到spring，则每个请求都会过滤一次。然后查看实现方法可以看到授权的主要流程。

1. 根据上下文，这里使用俩决定当前请求是否是开启授权登陆流程的请求
2. 根据1 获取到的配置信息进行重定向到Authorization server

我们先来看1的流程，查看方法实现：
```java
	@Override
	public OAuth2AuthorizationRequest resolve(HttpServletRequest request) {
        // 1
		String registrationId = resolveRegistrationId(request);
		if (registrationId == null) {
			return null;
		}
		String redirectUriAction = getAction(request, "login");
        // 2
		return resolve(request, registrationId, redirectUriAction);
	}
```
1. 获取请求参数中的registrationId(应该是类似配置id的主键)以及检查请求的url是否是预设路径
2. 继续查看2的实现

```java
	private OAuth2AuthorizationRequest resolve(HttpServletRequest request, String registrationId,
			String redirectUriAction) {
		if (registrationId == null) {
			return null;
		}
		// 1
		ClientRegistration clientRegistration = this.clientRegistrationRepository.findByRegistrationId(registrationId);
		if (clientRegistration == null) {
			throw new IllegalArgumentException("Invalid Client Registration with Id: " + registrationId);
		}
		// 2
		OAuth2AuthorizationRequest.Builder builder = getBuilder(clientRegistration);

		String redirectUriStr = expandRedirectUri(request, clientRegistration, redirectUriAction);

		// @formatter:off
		builder.clientId(clientRegistration.getClientId())
				.authorizationUri(clientRegistration.getProviderDetails().getAuthorizationUri())
				.redirectUri(redirectUriStr)
				.scopes(clientRegistration.getScopes())
				.state(DEFAULT_STATE_GENERATOR.generateKey());
		// @formatter:on
		// 3
		this.authorizationRequestCustomizer.accept(builder);

		return builder.build();
	}
```
1. 根据registrationId获取到ClientRegistration查看他的类结构不难发现这是进行OAuth2授权的配置信息
2. 这里开始组装授权所需要的配置信息，包括重定向的url、scopes以及state
3. 这里如果实现authorizationRequestCustomizer 应该是可以自定增加或者修改一些参数

整个resolve流程结束了，总的来说是检查url以及参数确定是否是发起授权登陆的请求，如果是的话就组装授权请求信息。回到过滤器OAuth2AuthorizationRequestRedirectFilter的实现方法，拿到授权请求后，我们查看他的实现 

```java
	private void sendRedirectForAuthorization(HttpServletRequest request, HttpServletResponse response,
			OAuth2AuthorizationRequest authorizationRequest) throws IOException {
			// 1
		if (AuthorizationGrantType.AUTHORIZATION_CODE.equals(authorizationRequest.getGrantType())) {
			this.authorizationRequestRepository.saveAuthorizationRequest(authorizationRequest, request, response);
		}
		// 2
		this.authorizationRedirectStrategy.sendRedirect(request, response,
				authorizationRequest.getAuthorizationRequestUri());
	}
```
1. 判断授权类型是否是授权码，如果是的话往session里保存了一些授权信息
2. 开始重定向到授权服务，这个步骤后慢流程是在授权服务登陆，登录成功后拿到Access Token

## 获取授权码流程

改步骤是跳转到授权服务器拿到Access Token后跳转到资源服务器的流程

```java
package org.springframework.security.oauth2.client.web;

public class OAuth2AuthorizationCodeGrantFilter extends OncePerRequestFilter {
    // ...
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (matchesAuthorizationResponse(request)) {
            processAuthorizationResponse(request, response);
            return;
        }
        filterChain.doFilter(request, response);
    }
    // ...
}
```

过滤器一开始判断这个请求是授权请求（因为本次实现不采用过滤器的方式，有兴趣的可以看下内部实现），如果是的话会开始下面的授权流程。流程正式开启

```java
public class OAuth2AuthorizationCodeGrantFilter extends OncePerRequestFilter {
    // ...
	private void processAuthorizationResponse(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
    // 1
		OAuth2AuthorizationRequest authorizationRequest = this.authorizationRequestRepository
				.removeAuthorizationRequest(request, response);
		String registrationId = authorizationRequest.getAttribute(OAuth2ParameterNames.REGISTRATION_ID);
		ClientRegistration clientRegistration = this.clientRegistrationRepository.findByRegistrationId(registrationId);
		MultiValueMap<String, String> params = OAuth2AuthorizationResponseUtils.toMultiMap(request.getParameterMap());
		String redirectUri = UrlUtils.buildFullRequestUrl(request);
    // 2
		OAuth2AuthorizationResponse authorizationResponse = OAuth2AuthorizationResponseUtils.convert(params,
				redirectUri);
		OAuth2AuthorizationCodeAuthenticationToken authenticationRequest = new OAuth2AuthorizationCodeAuthenticationToken(
				clientRegistration, new OAuth2AuthorizationExchange(authorizationRequest, authorizationResponse));
		authenticationRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
		OAuth2AuthorizationCodeAuthenticationToken authenticationResult;
		try {
      // 3
			authenticationResult = (OAuth2AuthorizationCodeAuthenticationToken) this.authenticationManager
					.authenticate(authenticationRequest);
		}
		catch (OAuth2AuthorizationException ex) {
			OAuth2Error error = ex.getError();
			UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(authorizationRequest.getRedirectUri())
					.queryParam(OAuth2ParameterNames.ERROR, error.getErrorCode());
			if (!StringUtils.isEmpty(error.getDescription())) {
				uriBuilder.queryParam(OAuth2ParameterNames.ERROR_DESCRIPTION, error.getDescription());
			}
			if (!StringUtils.isEmpty(error.getUri())) {
				uriBuilder.queryParam(OAuth2ParameterNames.ERROR_URI, error.getUri());
			}
      // 4
			this.redirectStrategy.sendRedirect(request, response, uriBuilder.build().encode().toString());
			return;
		}
    // 5
		Authentication currentAuthentication = SecurityContextHolder.getContext().getAuthentication();
		String principalName = (currentAuthentication != null) ? currentAuthentication.getName() : "anonymousUser";
		OAuth2AuthorizedClient authorizedClient = new OAuth2AuthorizedClient(
				authenticationResult.getClientRegistration(), principalName, authenticationResult.getAccessToken(),
				authenticationResult.getRefreshToken());
		this.authorizedClientRepository.saveAuthorizedClient(authorizedClient, currentAuthentication, request,
				response);
		String redirectUrl = authorizationRequest.getRedirectUri();
		SavedRequest savedRequest = this.requestCache.getRequest(request, response);
		if (savedRequest != null) {
			redirectUrl = savedRequest.getRedirectUrl();
			this.requestCache.removeRequest(request, response);
		}
		this.redirectStrategy.sendRedirect(request, response, redirectUrl);
	}
    // ...
}
```

1. 获取到发起授权登陆时放在session中的授权请求，进而拿到ClientRegistration
1. 封装了请求和response信息，像是在组装上下文
1. 根据try-catch应该猜到，这里是重点！！开始进行授权流程，授权流程我们稍后分析，先看下整个方法的逻辑
1. 授权中产生的OAuth2AuthorizationException会被重定向到重定向页面，具体地址可以追踪下，在调用resolve的时候设置的，代码可以查看`org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver#expandRedirectUri`uri是` ClientRegistration.redirectUri`,还拼接了一些参数
1. 授权成功后保存授权请求，(session和jdbc可选)
1. 重定向到` ClientRegistration.redirectUri`

### 授权流程

回过头来看下步骤3的逻辑authenticate流程，查看具体实现

```java
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		Class<? extends Authentication> toTest = authentication.getClass();
		AuthenticationException lastException = null;
		AuthenticationException parentException = null;
		Authentication result = null;
		Authentication parentResult = null;
		int currentPosition = 0;
		int size = this.providers.size();
    // 1
		for (AuthenticationProvider provider : getProviders()) {
			if (!provider.supports(toTest)) {
				continue;
			}
			if (logger.isTraceEnabled()) {
				logger.trace(LogMessage.format("Authenticating request with %s (%d/%d)",
						provider.getClass().getSimpleName(), ++currentPosition, size));
			}
			try {
				result = provider.authenticate(authentication);
				if (result != null) {
					copyDetails(authentication, result);
					break;
				}
			}
			catch (AccountStatusException | InternalAuthenticationServiceException ex) {
				prepareException(ex, authentication);
				throw ex;
			}
			catch (AuthenticationException ex) {
				lastException = ex;
			}
		}
    // 2
		if (result == null && this.parent != null) {
			try {
				parentResult = this.parent.authenticate(authentication);
				result = parentResult;
			}
			catch (ProviderNotFoundException ex) {
			}
			catch (AuthenticationException ex) {
				parentException = ex;
				lastException = ex;
			}
		}
    // 3
		if (result != null) {
			if (this.eraseCredentialsAfterAuthentication && (result instanceof CredentialsContainer)) {
				((CredentialsContainer) result).eraseCredentials();
			}
			if (parentResult == null) {
				this.eventPublisher.publishAuthenticationSuccess(result);
			}
			return result;
		}

		if (lastException == null) {
			lastException = new ProviderNotFoundException(this.messages.getMessage("ProviderManager.providerNotFound",
					new Object[] { toTest.getName() }, "No AuthenticationProvider found for {0}"));
		}
		if (parentException == null) {
			prepareException(lastException, authentication);
		}
		throw lastException;
	}
```

1. 遍历所有的AuthenticationProvider，找到支持当前身份验证的Provider然后进行授权

2. 如果所有的Provider都无法进行授权，则使用parent来尝试授权，应该是某个设计模式
3. 拿到了result，根据设置清楚授权凭证，然后返回登陆结果
4. 如果上述操作都没有拿到授权结果，则返回最近一次异常信息

*责任链设计模式spring代码是真规范,看着真舒服*

看到这里重点就在Provider是怎么实现了，这次重点关注OAuth2（**OAuth2LoginAuthenticationProvider**），其他也可以参考OIDC和匿名等

先来看看supports实现，其实很简单，就是判断class类型

```java
	@Override
	public boolean supports(Class<?> authentication) {
		return OAuth2LoginAuthenticationToken.class.isAssignableFrom(authentication);
	}
```

继续看authenticate

```java
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		OAuth2LoginAuthenticationToken loginAuthenticationToken = (OAuth2LoginAuthenticationToken) authentication;
    // 1
		if (loginAuthenticationToken.getAuthorizationExchange().getAuthorizationRequest().getScopes()
				.contains("openid")) {
			return null;
		}
		OAuth2AuthorizationCodeAuthenticationToken authorizationCodeAuthenticationToken;
		try {
      // 2
			authorizationCodeAuthenticationToken = (OAuth2AuthorizationCodeAuthenticationToken) this.authorizationCodeAuthenticationProvider
					.authenticate(new OAuth2AuthorizationCodeAuthenticationToken(
							loginAuthenticationToken.getClientRegistration(),
							loginAuthenticationToken.getAuthorizationExchange()));
		}
		catch (OAuth2AuthorizationException ex) {
			OAuth2Error oauth2Error = ex.getError();
			throw new OAuth2AuthenticationException(oauth2Error, oauth2Error.toString(), ex);
		}
    // 3
		OAuth2AccessToken accessToken = authorizationCodeAuthenticationToken.getAccessToken();
		Map<String, Object> additionalParameters = authorizationCodeAuthenticationToken.getAdditionalParameters();
		OAuth2User oauth2User = this.userService.loadUser(new OAuth2UserRequest(
				loginAuthenticationToken.getClientRegistration(), accessToken, additionalParameters));
		Collection<? extends GrantedAuthority> mappedAuthorities = this.authoritiesMapper
				.mapAuthorities(oauth2User.getAuthorities());
		OAuth2LoginAuthenticationToken authenticationResult = new OAuth2LoginAuthenticationToken(
				loginAuthenticationToken.getClientRegistration(), loginAuthenticationToken.getAuthorizationExchange(),
				oauth2User, mappedAuthorities, accessToken, authorizationCodeAuthenticationToken.getRefreshToken());
		authenticationResult.setDetails(loginAuthenticationToken.getDetails());
		return authenticationResult;
	}
```

1. 如果scopes没有**openid**则直接return null（openid有单独的实现，应该是前面的流程公用了一些类型
2. 使用authorizationCodeAuthenticationProvider 进行授权
3. 授权成功后就拿到了accessToken，然后就可以通过accessToken获取用户信息，返回授权结果，完成授权



```java
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		OAuth2AuthorizationCodeAuthenticationToken authorizationCodeAuthentication = (OAuth2AuthorizationCodeAuthenticationToken) authentication;
		OAuth2AuthorizationResponse authorizationResponse = authorizationCodeAuthentication.getAuthorizationExchange()
				.getAuthorizationResponse();
    // 1
		if (authorizationResponse.statusError()) {
			throw new OAuth2AuthorizationException(authorizationResponse.getError());
		}
		OAuth2AuthorizationRequest authorizationRequest = authorizationCodeAuthentication.getAuthorizationExchange()
				.getAuthorizationRequest();
		if (!authorizationResponse.getState().equals(authorizationRequest.getState())) {
			OAuth2Error oauth2Error = new OAuth2Error(INVALID_STATE_PARAMETER_ERROR_CODE);
			throw new OAuth2AuthorizationException(oauth2Error);
		}
    // 2
		OAuth2AccessTokenResponse accessTokenResponse = this.accessTokenResponseClient.getTokenResponse(
				new OAuth2AuthorizationCodeGrantRequest(authorizationCodeAuthentication.getClientRegistration(),
						authorizationCodeAuthentication.getAuthorizationExchange()));
		OAuth2AuthorizationCodeAuthenticationToken authenticationResult = new OAuth2AuthorizationCodeAuthenticationToken(
				authorizationCodeAuthentication.getClientRegistration(),
				authorizationCodeAuthentication.getAuthorizationExchange(), accessTokenResponse.getAccessToken(),
				accessTokenResponse.getRefreshToken(), accessTokenResponse.getAdditionalParameters());
		authenticationResult.setDetails(authorizationCodeAuthentication.getDetails());
		return authenticationResult;
	}
```

`OAuth2LoginAuthenticationProvider`里面授权还是通过`OAuth2AuthorizationCodeAuthenticationProvider`来实现的，login怎加了获取用户信息的流程

1. 检查response status是否是异常的，是否和request status一致。。。
2. 调用授权服务，获取accessTokenResponse

授权流程结束,（撒花🎉🎉🎉

## 登出

如果使用OIDC则有一个登出流程


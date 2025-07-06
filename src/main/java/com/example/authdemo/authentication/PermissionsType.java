package com.example.authdemo.authentication;

/**
 * 权限类型
 */
public enum PermissionsType {
    /**
     * 匿名即可访问
     */
    ANONYMOUS,
    /**
     * 登录即可访问
     */
    APPROVE,
    /**
     * 需要授权
     */
    AUTHORIZED;
}

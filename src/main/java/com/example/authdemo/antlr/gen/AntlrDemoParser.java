// Generated from java-escape by ANTLR 4.11.1
package com.example.authdemo.antlr.gen;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class AntlrDemoParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.11.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, PLUS=5, MINUS=6, MULT=7, DIV=8, LPAREN=9, 
		RPAREN=10, OPENCURLY=11, CLOSECURLY=12, INTEGER=13, DOUBLE=14, BOOLEAN=15, 
		IF=16, ELSE=17, A=18, B=19, C=20, D=21, E=22, F=23, G=24, H=25, I=26, 
		J=27, K=28, L=29, M=30, N=31, O=32, P=33, Q=34, R=35, S=36, T=37, U=38, 
		V=39, W=40, X=41, Y=42, Z=43, WS=44;
	public static final int
		RULE_script = 0, RULE_statements = 1, RULE_statementBlock = 2, RULE_statement = 3, 
		RULE_expr = 4, RULE_factor = 5, RULE_ifExpression = 6;
	private static String[] makeRuleNames() {
		return new String[] {
			"script", "statements", "statementBlock", "statement", "expr", "factor", 
			"ifExpression"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'=='", "'?'", "':'", "'+'", "'-'", "'*'", "'/'", "'('", 
			"')'", "'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, "PLUS", "MINUS", "MULT", "DIV", "LPAREN", 
			"RPAREN", "OPENCURLY", "CLOSECURLY", "INTEGER", "DOUBLE", "BOOLEAN", 
			"IF", "ELSE", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", 
			"L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", 
			"Z", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "java-escape"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public AntlrDemoParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ScriptContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(AntlrDemoParser.EOF, 0); }
		public List<StatementsContext> statements() {
			return getRuleContexts(StatementsContext.class);
		}
		public StatementsContext statements(int i) {
			return getRuleContext(StatementsContext.class,i);
		}
		public ScriptContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_script; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AntlrDemoListener ) ((AntlrDemoListener)listener).enterScript(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AntlrDemoListener ) ((AntlrDemoListener)listener).exitScript(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AntlrDemoVisitor ) return ((AntlrDemoVisitor<? extends T>)visitor).visitScript(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ScriptContext script() throws RecognitionException {
		ScriptContext _localctx = new ScriptContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_script);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(15); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(14);
				statements();
				}
				}
				setState(17); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( ((_la) & ~0x3f) == 0 && ((1L << _la) & 125440L) != 0 );
			setState(19);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementsContext extends ParserRuleContext {
		public StatementBlockContext statementBlock() {
			return getRuleContext(StatementBlockContext.class,0);
		}
		public IfExpressionContext ifExpression() {
			return getRuleContext(IfExpressionContext.class,0);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public StatementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AntlrDemoListener ) ((AntlrDemoListener)listener).enterStatements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AntlrDemoListener ) ((AntlrDemoListener)listener).exitStatements(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AntlrDemoVisitor ) return ((AntlrDemoVisitor<? extends T>)visitor).visitStatements(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementsContext statements() throws RecognitionException {
		StatementsContext _localctx = new StatementsContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statements);
		int _la;
		try {
			int _alt;
			setState(34);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPENCURLY:
				enterOuterAlt(_localctx, 1);
				{
				setState(21);
				statementBlock();
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 2);
				{
				setState(22);
				ifExpression();
				}
				break;
			case LPAREN:
			case INTEGER:
			case DOUBLE:
			case BOOLEAN:
				enterOuterAlt(_localctx, 3);
				{
				setState(23);
				statement();
				setState(28);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(24);
						match(T__0);
						setState(25);
						statement();
						}
						} 
					}
					setState(30);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
				}
				setState(32);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__0) {
					{
					setState(31);
					match(T__0);
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementBlockContext extends ParserRuleContext {
		public TerminalNode OPENCURLY() { return getToken(AntlrDemoParser.OPENCURLY, 0); }
		public TerminalNode CLOSECURLY() { return getToken(AntlrDemoParser.CLOSECURLY, 0); }
		public List<StatementsContext> statements() {
			return getRuleContexts(StatementsContext.class);
		}
		public StatementsContext statements(int i) {
			return getRuleContext(StatementsContext.class,i);
		}
		public StatementBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statementBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AntlrDemoListener ) ((AntlrDemoListener)listener).enterStatementBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AntlrDemoListener ) ((AntlrDemoListener)listener).exitStatementBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AntlrDemoVisitor ) return ((AntlrDemoVisitor<? extends T>)visitor).visitStatementBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementBlockContext statementBlock() throws RecognitionException {
		StatementBlockContext _localctx = new StatementBlockContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_statementBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36);
			match(OPENCURLY);
			setState(40);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((_la) & ~0x3f) == 0 && ((1L << _la) & 125440L) != 0) {
				{
				{
				setState(37);
				statements();
				}
				}
				setState(42);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(43);
			match(CLOSECURLY);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AntlrDemoListener ) ((AntlrDemoListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AntlrDemoListener ) ((AntlrDemoListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AntlrDemoVisitor ) return ((AntlrDemoVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			expr(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MultOrDivContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode MULT() { return getToken(AntlrDemoParser.MULT, 0); }
		public TerminalNode DIV() { return getToken(AntlrDemoParser.DIV, 0); }
		public MultOrDivContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AntlrDemoListener ) ((AntlrDemoListener)listener).enterMultOrDiv(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AntlrDemoListener ) ((AntlrDemoListener)listener).exitMultOrDiv(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AntlrDemoVisitor ) return ((AntlrDemoVisitor<? extends T>)visitor).visitMultOrDiv(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class OperatorExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public OperatorExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AntlrDemoListener ) ((AntlrDemoListener)listener).enterOperatorExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AntlrDemoListener ) ((AntlrDemoListener)listener).exitOperatorExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AntlrDemoVisitor ) return ((AntlrDemoVisitor<? extends T>)visitor).visitOperatorExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PlusOrMinusContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode PLUS() { return getToken(AntlrDemoParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(AntlrDemoParser.MINUS, 0); }
		public PlusOrMinusContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AntlrDemoListener ) ((AntlrDemoListener)listener).enterPlusOrMinus(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AntlrDemoListener ) ((AntlrDemoListener)listener).exitPlusOrMinus(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AntlrDemoVisitor ) return ((AntlrDemoVisitor<? extends T>)visitor).visitPlusOrMinus(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TernaryOperatorContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TernaryOperatorContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AntlrDemoListener ) ((AntlrDemoListener)listener).enterTernaryOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AntlrDemoListener ) ((AntlrDemoListener)listener).exitTernaryOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AntlrDemoVisitor ) return ((AntlrDemoVisitor<? extends T>)visitor).visitTernaryOperator(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FactorExprContext extends ExprContext {
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public FactorExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AntlrDemoListener ) ((AntlrDemoListener)listener).enterFactorExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AntlrDemoListener ) ((AntlrDemoListener)listener).exitFactorExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AntlrDemoVisitor ) return ((AntlrDemoVisitor<? extends T>)visitor).visitFactorExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParenExprContext extends ExprContext {
		public TerminalNode LPAREN() { return getToken(AntlrDemoParser.LPAREN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(AntlrDemoParser.RPAREN, 0); }
		public ParenExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AntlrDemoListener ) ((AntlrDemoListener)listener).enterParenExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AntlrDemoListener ) ((AntlrDemoListener)listener).exitParenExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AntlrDemoVisitor ) return ((AntlrDemoVisitor<? extends T>)visitor).visitParenExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 8;
		enterRecursionRule(_localctx, 8, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LPAREN:
				{
				_localctx = new ParenExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(48);
				match(LPAREN);
				setState(49);
				expr(0);
				setState(50);
				match(RPAREN);
				}
				break;
			case INTEGER:
			case DOUBLE:
			case BOOLEAN:
				{
				_localctx = new FactorExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(52);
				factor();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(72);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(70);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
					case 1:
						{
						_localctx = new MultOrDivContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(55);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(56);
						_la = _input.LA(1);
						if ( !(_la==MULT || _la==DIV) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(57);
						expr(6);
						}
						break;
					case 2:
						{
						_localctx = new PlusOrMinusContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(58);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(59);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(60);
						expr(5);
						}
						break;
					case 3:
						{
						_localctx = new OperatorExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(61);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(62);
						match(T__1);
						setState(63);
						expr(4);
						}
						break;
					case 4:
						{
						_localctx = new TernaryOperatorContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(64);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(65);
						match(T__2);
						setState(66);
						expr(0);
						setState(67);
						match(T__3);
						setState(68);
						expr(3);
						}
						break;
					}
					} 
				}
				setState(74);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FactorContext extends ParserRuleContext {
		public FactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor; }
	 
		public FactorContext() { }
		public void copyFrom(FactorContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ObjFactoryContext extends FactorContext {
		public TerminalNode INTEGER() { return getToken(AntlrDemoParser.INTEGER, 0); }
		public TerminalNode BOOLEAN() { return getToken(AntlrDemoParser.BOOLEAN, 0); }
		public TerminalNode DOUBLE() { return getToken(AntlrDemoParser.DOUBLE, 0); }
		public ObjFactoryContext(FactorContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AntlrDemoListener ) ((AntlrDemoListener)listener).enterObjFactory(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AntlrDemoListener ) ((AntlrDemoListener)listener).exitObjFactory(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AntlrDemoVisitor ) return ((AntlrDemoVisitor<? extends T>)visitor).visitObjFactory(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_factor);
		try {
			setState(78);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INTEGER:
				_localctx = new ObjFactoryContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(75);
				match(INTEGER);
				}
				break;
			case BOOLEAN:
				_localctx = new ObjFactoryContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(76);
				match(BOOLEAN);
				}
				break;
			case DOUBLE:
				_localctx = new ObjFactoryContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(77);
				match(DOUBLE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IfExpressionContext extends ParserRuleContext {
		public IfExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifExpression; }
	 
		public IfExpressionContext() { }
		public void copyFrom(IfExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IfContext extends IfExpressionContext {
		public TerminalNode IF() { return getToken(AntlrDemoParser.IF, 0); }
		public TerminalNode LPAREN() { return getToken(AntlrDemoParser.LPAREN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(AntlrDemoParser.RPAREN, 0); }
		public StatementBlockContext statementBlock() {
			return getRuleContext(StatementBlockContext.class,0);
		}
		public IfContext(IfExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AntlrDemoListener ) ((AntlrDemoListener)listener).enterIf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AntlrDemoListener ) ((AntlrDemoListener)listener).exitIf(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AntlrDemoVisitor ) return ((AntlrDemoVisitor<? extends T>)visitor).visitIf(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IfElseContext extends IfExpressionContext {
		public TerminalNode IF() { return getToken(AntlrDemoParser.IF, 0); }
		public TerminalNode LPAREN() { return getToken(AntlrDemoParser.LPAREN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(AntlrDemoParser.RPAREN, 0); }
		public List<StatementBlockContext> statementBlock() {
			return getRuleContexts(StatementBlockContext.class);
		}
		public StatementBlockContext statementBlock(int i) {
			return getRuleContext(StatementBlockContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(AntlrDemoParser.ELSE, 0); }
		public IfElseContext(IfExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AntlrDemoListener ) ((AntlrDemoListener)listener).enterIfElse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AntlrDemoListener ) ((AntlrDemoListener)listener).exitIfElse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AntlrDemoVisitor ) return ((AntlrDemoVisitor<? extends T>)visitor).visitIfElse(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IfElseIfContext extends IfExpressionContext {
		public TerminalNode IF() { return getToken(AntlrDemoParser.IF, 0); }
		public TerminalNode LPAREN() { return getToken(AntlrDemoParser.LPAREN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(AntlrDemoParser.RPAREN, 0); }
		public StatementBlockContext statementBlock() {
			return getRuleContext(StatementBlockContext.class,0);
		}
		public TerminalNode ELSE() { return getToken(AntlrDemoParser.ELSE, 0); }
		public IfExpressionContext ifExpression() {
			return getRuleContext(IfExpressionContext.class,0);
		}
		public IfElseIfContext(IfExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AntlrDemoListener ) ((AntlrDemoListener)listener).enterIfElseIf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AntlrDemoListener ) ((AntlrDemoListener)listener).exitIfElseIf(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AntlrDemoVisitor ) return ((AntlrDemoVisitor<? extends T>)visitor).visitIfElseIf(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfExpressionContext ifExpression() throws RecognitionException {
		IfExpressionContext _localctx = new IfExpressionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_ifExpression);
		try {
			setState(102);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				_localctx = new IfContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(80);
				match(IF);
				setState(81);
				match(LPAREN);
				setState(82);
				expr(0);
				setState(83);
				match(RPAREN);
				setState(84);
				statementBlock();
				}
				break;
			case 2:
				_localctx = new IfElseContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(86);
				match(IF);
				setState(87);
				match(LPAREN);
				setState(88);
				expr(0);
				setState(89);
				match(RPAREN);
				setState(90);
				statementBlock();
				setState(91);
				match(ELSE);
				setState(92);
				statementBlock();
				}
				break;
			case 3:
				_localctx = new IfElseIfContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(94);
				match(IF);
				setState(95);
				match(LPAREN);
				setState(96);
				expr(0);
				setState(97);
				match(RPAREN);
				setState(98);
				statementBlock();
				setState(99);
				match(ELSE);
				setState(100);
				ifExpression();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 4:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 5);
		case 1:
			return precpred(_ctx, 4);
		case 2:
			return precpred(_ctx, 3);
		case 3:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001,i\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002\u0002"+
		"\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002\u0005"+
		"\u0007\u0005\u0002\u0006\u0007\u0006\u0001\u0000\u0004\u0000\u0010\b\u0000"+
		"\u000b\u0000\f\u0000\u0011\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u0001\u001b\b\u0001\n\u0001"+
		"\f\u0001\u001e\t\u0001\u0001\u0001\u0003\u0001!\b\u0001\u0003\u0001#\b"+
		"\u0001\u0001\u0002\u0001\u0002\u0005\u0002\'\b\u0002\n\u0002\f\u0002*"+
		"\t\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u00046\b"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0005\u0004G\b\u0004\n\u0004"+
		"\f\u0004J\t\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005O\b\u0005"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006g\b\u0006"+
		"\u0001\u0006\u0000\u0001\b\u0007\u0000\u0002\u0004\u0006\b\n\f\u0000\u0002"+
		"\u0001\u0000\u0007\b\u0001\u0000\u0005\u0006p\u0000\u000f\u0001\u0000"+
		"\u0000\u0000\u0002\"\u0001\u0000\u0000\u0000\u0004$\u0001\u0000\u0000"+
		"\u0000\u0006-\u0001\u0000\u0000\u0000\b5\u0001\u0000\u0000\u0000\nN\u0001"+
		"\u0000\u0000\u0000\ff\u0001\u0000\u0000\u0000\u000e\u0010\u0003\u0002"+
		"\u0001\u0000\u000f\u000e\u0001\u0000\u0000\u0000\u0010\u0011\u0001\u0000"+
		"\u0000\u0000\u0011\u000f\u0001\u0000\u0000\u0000\u0011\u0012\u0001\u0000"+
		"\u0000\u0000\u0012\u0013\u0001\u0000\u0000\u0000\u0013\u0014\u0005\u0000"+
		"\u0000\u0001\u0014\u0001\u0001\u0000\u0000\u0000\u0015#\u0003\u0004\u0002"+
		"\u0000\u0016#\u0003\f\u0006\u0000\u0017\u001c\u0003\u0006\u0003\u0000"+
		"\u0018\u0019\u0005\u0001\u0000\u0000\u0019\u001b\u0003\u0006\u0003\u0000"+
		"\u001a\u0018\u0001\u0000\u0000\u0000\u001b\u001e\u0001\u0000\u0000\u0000"+
		"\u001c\u001a\u0001\u0000\u0000\u0000\u001c\u001d\u0001\u0000\u0000\u0000"+
		"\u001d \u0001\u0000\u0000\u0000\u001e\u001c\u0001\u0000\u0000\u0000\u001f"+
		"!\u0005\u0001\u0000\u0000 \u001f\u0001\u0000\u0000\u0000 !\u0001\u0000"+
		"\u0000\u0000!#\u0001\u0000\u0000\u0000\"\u0015\u0001\u0000\u0000\u0000"+
		"\"\u0016\u0001\u0000\u0000\u0000\"\u0017\u0001\u0000\u0000\u0000#\u0003"+
		"\u0001\u0000\u0000\u0000$(\u0005\u000b\u0000\u0000%\'\u0003\u0002\u0001"+
		"\u0000&%\u0001\u0000\u0000\u0000\'*\u0001\u0000\u0000\u0000(&\u0001\u0000"+
		"\u0000\u0000()\u0001\u0000\u0000\u0000)+\u0001\u0000\u0000\u0000*(\u0001"+
		"\u0000\u0000\u0000+,\u0005\f\u0000\u0000,\u0005\u0001\u0000\u0000\u0000"+
		"-.\u0003\b\u0004\u0000.\u0007\u0001\u0000\u0000\u0000/0\u0006\u0004\uffff"+
		"\uffff\u000001\u0005\t\u0000\u000012\u0003\b\u0004\u000023\u0005\n\u0000"+
		"\u000036\u0001\u0000\u0000\u000046\u0003\n\u0005\u00005/\u0001\u0000\u0000"+
		"\u000054\u0001\u0000\u0000\u00006H\u0001\u0000\u0000\u000078\n\u0005\u0000"+
		"\u000089\u0007\u0000\u0000\u00009G\u0003\b\u0004\u0006:;\n\u0004\u0000"+
		"\u0000;<\u0007\u0001\u0000\u0000<G\u0003\b\u0004\u0005=>\n\u0003\u0000"+
		"\u0000>?\u0005\u0002\u0000\u0000?G\u0003\b\u0004\u0004@A\n\u0002\u0000"+
		"\u0000AB\u0005\u0003\u0000\u0000BC\u0003\b\u0004\u0000CD\u0005\u0004\u0000"+
		"\u0000DE\u0003\b\u0004\u0003EG\u0001\u0000\u0000\u0000F7\u0001\u0000\u0000"+
		"\u0000F:\u0001\u0000\u0000\u0000F=\u0001\u0000\u0000\u0000F@\u0001\u0000"+
		"\u0000\u0000GJ\u0001\u0000\u0000\u0000HF\u0001\u0000\u0000\u0000HI\u0001"+
		"\u0000\u0000\u0000I\t\u0001\u0000\u0000\u0000JH\u0001\u0000\u0000\u0000"+
		"KO\u0005\r\u0000\u0000LO\u0005\u000f\u0000\u0000MO\u0005\u000e\u0000\u0000"+
		"NK\u0001\u0000\u0000\u0000NL\u0001\u0000\u0000\u0000NM\u0001\u0000\u0000"+
		"\u0000O\u000b\u0001\u0000\u0000\u0000PQ\u0005\u0010\u0000\u0000QR\u0005"+
		"\t\u0000\u0000RS\u0003\b\u0004\u0000ST\u0005\n\u0000\u0000TU\u0003\u0004"+
		"\u0002\u0000Ug\u0001\u0000\u0000\u0000VW\u0005\u0010\u0000\u0000WX\u0005"+
		"\t\u0000\u0000XY\u0003\b\u0004\u0000YZ\u0005\n\u0000\u0000Z[\u0003\u0004"+
		"\u0002\u0000[\\\u0005\u0011\u0000\u0000\\]\u0003\u0004\u0002\u0000]g\u0001"+
		"\u0000\u0000\u0000^_\u0005\u0010\u0000\u0000_`\u0005\t\u0000\u0000`a\u0003"+
		"\b\u0004\u0000ab\u0005\n\u0000\u0000bc\u0003\u0004\u0002\u0000cd\u0005"+
		"\u0011\u0000\u0000de\u0003\f\u0006\u0000eg\u0001\u0000\u0000\u0000fP\u0001"+
		"\u0000\u0000\u0000fV\u0001\u0000\u0000\u0000f^\u0001\u0000\u0000\u0000"+
		"g\r\u0001\u0000\u0000\u0000\n\u0011\u001c \"(5FHNf";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
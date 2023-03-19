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
		T__0=1, T__1=2, T__2=3, PLUS=4, MINUS=5, MULT=6, DIV=7, LPAREN=8, RPAREN=9, 
		OPENCURLY=10, CLOSECURLY=11, INTEGER=12, DOUBLE=13, BOOLEAN=14, IF=15, 
		ELSE=16, A=17, B=18, C=19, D=20, E=21, F=22, G=23, H=24, I=25, J=26, K=27, 
		L=28, M=29, N=30, O=31, P=32, Q=33, R=34, S=35, T=36, U=37, V=38, W=39, 
		X=40, Y=41, Z=42, WS=43;
	public static final int
		RULE_expr = 0, RULE_factor = 1;
	private static String[] makeRuleNames() {
		return new String[] {
			"expr", "factor"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'=='", "'?'", "':'", "'+'", "'-'", "'*'", "'/'", "'('", "')'", 
			"'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, "PLUS", "MINUS", "MULT", "DIV", "LPAREN", "RPAREN", 
			"OPENCURLY", "CLOSECURLY", "INTEGER", "DOUBLE", "BOOLEAN", "IF", "ELSE", 
			"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", 
			"O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "WS"
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
		int _startState = 0;
		enterRecursionRule(_localctx, 0, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(10);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LPAREN:
				{
				_localctx = new ParenExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(5);
				match(LPAREN);
				setState(6);
				expr(0);
				setState(7);
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
				setState(9);
				factor();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(29);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(27);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
					case 1:
						{
						_localctx = new MultOrDivContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(12);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(13);
						_la = _input.LA(1);
						if ( !(_la==MULT || _la==DIV) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(14);
						expr(6);
						}
						break;
					case 2:
						{
						_localctx = new PlusOrMinusContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(15);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(16);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(17);
						expr(5);
						}
						break;
					case 3:
						{
						_localctx = new OperatorExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(18);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(19);
						match(T__0);
						setState(20);
						expr(4);
						}
						break;
					case 4:
						{
						_localctx = new TernaryOperatorContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(21);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(22);
						match(T__1);
						setState(23);
						expr(0);
						setState(24);
						match(T__2);
						setState(25);
						expr(3);
						}
						break;
					}
					} 
				}
				setState(31);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
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
		enterRule(_localctx, 2, RULE_factor);
		try {
			setState(35);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INTEGER:
				_localctx = new ObjFactoryContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(32);
				match(INTEGER);
				}
				break;
			case BOOLEAN:
				_localctx = new ObjFactoryContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(33);
				match(BOOLEAN);
				}
				break;
			case DOUBLE:
				_localctx = new ObjFactoryContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(34);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 0:
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
		"\u0004\u0001+&\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0003\u0000"+
		"\u000b\b\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0005\u0000\u001c\b\u0000"+
		"\n\u0000\f\u0000\u001f\t\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0003"+
		"\u0001$\b\u0001\u0001\u0001\u0000\u0001\u0000\u0002\u0000\u0002\u0000"+
		"\u0002\u0001\u0000\u0006\u0007\u0001\u0000\u0004\u0005*\u0000\n\u0001"+
		"\u0000\u0000\u0000\u0002#\u0001\u0000\u0000\u0000\u0004\u0005\u0006\u0000"+
		"\uffff\uffff\u0000\u0005\u0006\u0005\b\u0000\u0000\u0006\u0007\u0003\u0000"+
		"\u0000\u0000\u0007\b\u0005\t\u0000\u0000\b\u000b\u0001\u0000\u0000\u0000"+
		"\t\u000b\u0003\u0002\u0001\u0000\n\u0004\u0001\u0000\u0000\u0000\n\t\u0001"+
		"\u0000\u0000\u0000\u000b\u001d\u0001\u0000\u0000\u0000\f\r\n\u0005\u0000"+
		"\u0000\r\u000e\u0007\u0000\u0000\u0000\u000e\u001c\u0003\u0000\u0000\u0006"+
		"\u000f\u0010\n\u0004\u0000\u0000\u0010\u0011\u0007\u0001\u0000\u0000\u0011"+
		"\u001c\u0003\u0000\u0000\u0005\u0012\u0013\n\u0003\u0000\u0000\u0013\u0014"+
		"\u0005\u0001\u0000\u0000\u0014\u001c\u0003\u0000\u0000\u0004\u0015\u0016"+
		"\n\u0002\u0000\u0000\u0016\u0017\u0005\u0002\u0000\u0000\u0017\u0018\u0003"+
		"\u0000\u0000\u0000\u0018\u0019\u0005\u0003\u0000\u0000\u0019\u001a\u0003"+
		"\u0000\u0000\u0003\u001a\u001c\u0001\u0000\u0000\u0000\u001b\f\u0001\u0000"+
		"\u0000\u0000\u001b\u000f\u0001\u0000\u0000\u0000\u001b\u0012\u0001\u0000"+
		"\u0000\u0000\u001b\u0015\u0001\u0000\u0000\u0000\u001c\u001f\u0001\u0000"+
		"\u0000\u0000\u001d\u001b\u0001\u0000\u0000\u0000\u001d\u001e\u0001\u0000"+
		"\u0000\u0000\u001e\u0001\u0001\u0000\u0000\u0000\u001f\u001d\u0001\u0000"+
		"\u0000\u0000 $\u0005\f\u0000\u0000!$\u0005\u000e\u0000\u0000\"$\u0005"+
		"\r\u0000\u0000# \u0001\u0000\u0000\u0000#!\u0001\u0000\u0000\u0000#\""+
		"\u0001\u0000\u0000\u0000$\u0003\u0001\u0000\u0000\u0000\u0004\n\u001b"+
		"\u001d#";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
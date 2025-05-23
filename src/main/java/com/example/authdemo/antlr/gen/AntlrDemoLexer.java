// Generated from C:/Users/shen/Desktop/worker/github/auth-demo/src/main/java/com/example/authdemo/antlr\AntlrDemo.g4 by ANTLR 4.12.0
package com.example.authdemo.antlr.gen;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class AntlrDemoLexer extends Lexer {
    static {
        RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION);
    }

    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache =
                    new PredictionContextCache();
    public static final int
                    T__0 = 1, T__1 = 2, T__2 = 3, T__3 = 4, T__4 = 5, T__5 = 6, PLUS = 7, MINUS = 8, MULT = 9,
                    DIV = 10, AND = 11, OR = 12, NOT = 13, GREATEREQUAL = 14, LESSEQUAL = 15, LESS = 16,
                    GREATER = 17, LPAREN = 18, RPAREN = 19, OPENCURLY = 20, CLOSECURLY = 21, INTEGER = 22,
                    DOUBLE = 23, BOOLEAN = 24, IF = 25, ELSE = 26, VARIABLE = 27, A = 28, B = 29, C = 30,
                    D = 31, E = 32, F = 33, G = 34, H = 35, I = 36, J = 37, K = 38, L = 39, M = 40, N = 41, O = 42,
                    P = 43, Q = 44, R = 45, S = 46, T = 47, U = 48, V = 49, W = 50, X = 51, Y = 52, Z = 53, WS = 54;
    public static String[] channelNames = {
                    "DEFAULT_TOKEN_CHANNEL", "HIDDEN"
    };

    public static String[] modeNames = {
                    "DEFAULT_MODE"
    };

    private static String[] makeRuleNames() {
        return new String[]{
                        "T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "PLUS", "MINUS", "MULT",
                        "DIV", "AND", "OR", "NOT", "GREATEREQUAL", "LESSEQUAL", "LESS", "GREATER",
                        "LPAREN", "RPAREN", "OPENCURLY", "CLOSECURLY", "INTEGER", "DOUBLE", "BOOLEAN",
                        "IF", "ELSE", "VARIABLE", "A", "B", "C", "D", "E", "F", "G", "H", "I",
                        "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",
                        "X", "Y", "Z", "WS"
        };
    }

    public static final String[] ruleNames = makeRuleNames();

    private static String[] makeLiteralNames() {
        return new String[]{
                        null, "';'", "'=='", "'!='", "'?'", "':'", "'='", "'+'", "'-'", "'*'",
                        "'/'", "'&&'", "'||'", "'!'", "'>='", "'<='", "'<'", "'>'", "'('", "')'",
                        "'{'", "'}'"
        };
    }

    private static final String[] _LITERAL_NAMES = makeLiteralNames();

    private static String[] makeSymbolicNames() {
        return new String[]{
                        null, null, null, null, null, null, null, "PLUS", "MINUS", "MULT", "DIV",
                        "AND", "OR", "NOT", "GREATEREQUAL", "LESSEQUAL", "LESS", "GREATER", "LPAREN",
                        "RPAREN", "OPENCURLY", "CLOSECURLY", "INTEGER", "DOUBLE", "BOOLEAN",
                        "IF", "ELSE", "VARIABLE", "A", "B", "C", "D", "E", "F", "G", "H", "I",
                        "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",
                        "X", "Y", "Z", "WS"
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


    public AntlrDemoLexer(CharStream input) {
        super(input);
        _interp = new LexerATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
    }

    @Override
    public String getGrammarFileName() {
        return "AntlrDemo.g4";
    }

    @Override
    public String[] getRuleNames() {
        return ruleNames;
    }

    @Override
    public String getSerializedATN() {
        return _serializedATN;
    }

    @Override
    public String[] getChannelNames() {
        return channelNames;
    }

    @Override
    public String[] getModeNames() {
        return modeNames;
    }

    @Override
    public ATN getATN() {
        return _ATN;
    }

    public static final String _serializedATN =
                    "\u0004\u00006\u0104\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001" +
                                    "\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004" +
                                    "\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007" +
                                    "\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b" +
                                    "\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002" +
                                    "\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002" +
                                    "\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002" +
                                    "\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002" +
                                    "\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002" +
                                    "\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002" +
                                    "\u001e\u0007\u001e\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007" +
                                    "!\u0002\"\u0007\"\u0002#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007" +
                                    "&\u0002\'\u0007\'\u0002(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007" +
                                    "+\u0002,\u0007,\u0002-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u0007" +
                                    "0\u00021\u00071\u00022\u00072\u00023\u00073\u00024\u00074\u00025\u0007" +
                                    "5\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002" +
                                    "\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004" +
                                    "\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007" +
                                    "\u0001\b\u0001\b\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001" +
                                    "\u000b\u0001\u000b\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\u000e" +
                                    "\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010" +
                                    "\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013" +
                                    "\u0001\u0014\u0001\u0014\u0001\u0015\u0004\u0015\u009f\b\u0015\u000b\u0015" +
                                    "\f\u0015\u00a0\u0001\u0016\u0004\u0016\u00a4\b\u0016\u000b\u0016\f\u0016" +
                                    "\u00a5\u0001\u0016\u0001\u0016\u0004\u0016\u00aa\b\u0016\u000b\u0016\f" +
                                    "\u0016\u00ab\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017" +
                                    "\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017" +
                                    "\u0003\u0017\u00b9\b\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0019" +
                                    "\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a" +
                                    "\u0005\u001a\u00c5\b\u001a\n\u001a\f\u001a\u00c8\t\u001a\u0001\u001b\u0001" +
                                    "\u001b\u0001\u001c\u0001\u001c\u0001\u001d\u0001\u001d\u0001\u001e\u0001" +
                                    "\u001e\u0001\u001f\u0001\u001f\u0001 \u0001 \u0001!\u0001!\u0001\"\u0001" +
                                    "\"\u0001#\u0001#\u0001$\u0001$\u0001%\u0001%\u0001&\u0001&\u0001\'\u0001" +
                                    "\'\u0001(\u0001(\u0001)\u0001)\u0001*\u0001*\u0001+\u0001+\u0001,\u0001" +
                                    ",\u0001-\u0001-\u0001.\u0001.\u0001/\u0001/\u00010\u00010\u00011\u0001" +
                                    "1\u00012\u00012\u00013\u00013\u00014\u00014\u00015\u00045\u00ff\b5\u000b" +
                                    "5\f5\u0100\u00015\u00015\u0000\u00006\u0001\u0001\u0003\u0002\u0005\u0003" +
                                    "\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015" +
                                    "\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012" +
                                    "%\u0013\'\u0014)\u0015+\u0016-\u0017/\u00181\u00193\u001a5\u001b7\u001c" +
                                    "9\u001d;\u001e=\u001f? A!C\"E#G$I%K&M\'O(Q)S*U+W,Y-[.]/_0a1c2e3g4i5k6" +
                                    "\u0001\u0000\u001e\u0001\u000009\u0002\u0000__az\u0004\u000009AZ__az\u0002" +
                                    "\u0000AAaa\u0002\u0000BBbb\u0002\u0000CCcc\u0002\u0000DDdd\u0002\u0000" +
                                    "EEee\u0002\u0000FFff\u0002\u0000GGgg\u0002\u0000HHhh\u0002\u0000IIii\u0002" +
                                    "\u0000JJjj\u0002\u0000KKkk\u0002\u0000LLll\u0002\u0000MMmm\u0002\u0000" +
                                    "NNnn\u0002\u0000OOoo\u0002\u0000PPpp\u0002\u0000QQqq\u0002\u0000RRrr\u0002" +
                                    "\u0000SSss\u0002\u0000TTtt\u0002\u0000UUuu\u0002\u0000VVvv\u0002\u0000" +
                                    "WWww\u0002\u0000XXxx\u0002\u0000YYyy\u0002\u0000ZZzz\u0003\u0000\t\n\r" +
                                    "\r  \u0109\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000" +
                                    "\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000" +
                                    "\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000" +
                                    "\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000" +
                                    "\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000" +
                                    "\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000" +
                                    "\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000" +
                                    "\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000" +
                                    "\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000\u0000%" +
                                    "\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000\u0000\u0000)\u0001" +
                                    "\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000\u0000-\u0001\u0000\u0000" +
                                    "\u0000\u0000/\u0001\u0000\u0000\u0000\u00001\u0001\u0000\u0000\u0000\u0000" +
                                    "3\u0001\u0000\u0000\u0000\u00005\u0001\u0000\u0000\u0000\u00007\u0001" +
                                    "\u0000\u0000\u0000\u00009\u0001\u0000\u0000\u0000\u0000;\u0001\u0000\u0000" +
                                    "\u0000\u0000=\u0001\u0000\u0000\u0000\u0000?\u0001\u0000\u0000\u0000\u0000" +
                                    "A\u0001\u0000\u0000\u0000\u0000C\u0001\u0000\u0000\u0000\u0000E\u0001" +
                                    "\u0000\u0000\u0000\u0000G\u0001\u0000\u0000\u0000\u0000I\u0001\u0000\u0000" +
                                    "\u0000\u0000K\u0001\u0000\u0000\u0000\u0000M\u0001\u0000\u0000\u0000\u0000" +
                                    "O\u0001\u0000\u0000\u0000\u0000Q\u0001\u0000\u0000\u0000\u0000S\u0001" +
                                    "\u0000\u0000\u0000\u0000U\u0001\u0000\u0000\u0000\u0000W\u0001\u0000\u0000" +
                                    "\u0000\u0000Y\u0001\u0000\u0000\u0000\u0000[\u0001\u0000\u0000\u0000\u0000" +
                                    "]\u0001\u0000\u0000\u0000\u0000_\u0001\u0000\u0000\u0000\u0000a\u0001" +
                                    "\u0000\u0000\u0000\u0000c\u0001\u0000\u0000\u0000\u0000e\u0001\u0000\u0000" +
                                    "\u0000\u0000g\u0001\u0000\u0000\u0000\u0000i\u0001\u0000\u0000\u0000\u0000" +
                                    "k\u0001\u0000\u0000\u0000\u0001m\u0001\u0000\u0000\u0000\u0003o\u0001" +
                                    "\u0000\u0000\u0000\u0005r\u0001\u0000\u0000\u0000\u0007u\u0001\u0000\u0000" +
                                    "\u0000\tw\u0001\u0000\u0000\u0000\u000by\u0001\u0000\u0000\u0000\r{\u0001" +
                                    "\u0000\u0000\u0000\u000f}\u0001\u0000\u0000\u0000\u0011\u007f\u0001\u0000" +
                                    "\u0000\u0000\u0013\u0081\u0001\u0000\u0000\u0000\u0015\u0083\u0001\u0000" +
                                    "\u0000\u0000\u0017\u0086\u0001\u0000\u0000\u0000\u0019\u0089\u0001\u0000" +
                                    "\u0000\u0000\u001b\u008b\u0001\u0000\u0000\u0000\u001d\u008e\u0001\u0000" +
                                    "\u0000\u0000\u001f\u0091\u0001\u0000\u0000\u0000!\u0093\u0001\u0000\u0000" +
                                    "\u0000#\u0095\u0001\u0000\u0000\u0000%\u0097\u0001\u0000\u0000\u0000\'" +
                                    "\u0099\u0001\u0000\u0000\u0000)\u009b\u0001\u0000\u0000\u0000+\u009e\u0001" +
                                    "\u0000\u0000\u0000-\u00a3\u0001\u0000\u0000\u0000/\u00b8\u0001\u0000\u0000" +
                                    "\u00001\u00ba\u0001\u0000\u0000\u00003\u00bd\u0001\u0000\u0000\u00005" +
                                    "\u00c2\u0001\u0000\u0000\u00007\u00c9\u0001\u0000\u0000\u00009\u00cb\u0001" +
                                    "\u0000\u0000\u0000;\u00cd\u0001\u0000\u0000\u0000=\u00cf\u0001\u0000\u0000" +
                                    "\u0000?\u00d1\u0001\u0000\u0000\u0000A\u00d3\u0001\u0000\u0000\u0000C" +
                                    "\u00d5\u0001\u0000\u0000\u0000E\u00d7\u0001\u0000\u0000\u0000G\u00d9\u0001" +
                                    "\u0000\u0000\u0000I\u00db\u0001\u0000\u0000\u0000K\u00dd\u0001\u0000\u0000" +
                                    "\u0000M\u00df\u0001\u0000\u0000\u0000O\u00e1\u0001\u0000\u0000\u0000Q" +
                                    "\u00e3\u0001\u0000\u0000\u0000S\u00e5\u0001\u0000\u0000\u0000U\u00e7\u0001" +
                                    "\u0000\u0000\u0000W\u00e9\u0001\u0000\u0000\u0000Y\u00eb\u0001\u0000\u0000" +
                                    "\u0000[\u00ed\u0001\u0000\u0000\u0000]\u00ef\u0001\u0000\u0000\u0000_" +
                                    "\u00f1\u0001\u0000\u0000\u0000a\u00f3\u0001\u0000\u0000\u0000c\u00f5\u0001" +
                                    "\u0000\u0000\u0000e\u00f7\u0001\u0000\u0000\u0000g\u00f9\u0001\u0000\u0000" +
                                    "\u0000i\u00fb\u0001\u0000\u0000\u0000k\u00fe\u0001\u0000\u0000\u0000m" +
                                    "n\u0005;\u0000\u0000n\u0002\u0001\u0000\u0000\u0000op\u0005=\u0000\u0000" +
                                    "pq\u0005=\u0000\u0000q\u0004\u0001\u0000\u0000\u0000rs\u0005!\u0000\u0000" +
                                    "st\u0005=\u0000\u0000t\u0006\u0001\u0000\u0000\u0000uv\u0005?\u0000\u0000" +
                                    "v\b\u0001\u0000\u0000\u0000wx\u0005:\u0000\u0000x\n\u0001\u0000\u0000" +
                                    "\u0000yz\u0005=\u0000\u0000z\f\u0001\u0000\u0000\u0000{|\u0005+\u0000" +
                                    "\u0000|\u000e\u0001\u0000\u0000\u0000}~\u0005-\u0000\u0000~\u0010\u0001" +
                                    "\u0000\u0000\u0000\u007f\u0080\u0005*\u0000\u0000\u0080\u0012\u0001\u0000" +
                                    "\u0000\u0000\u0081\u0082\u0005/\u0000\u0000\u0082\u0014\u0001\u0000\u0000" +
                                    "\u0000\u0083\u0084\u0005&\u0000\u0000\u0084\u0085\u0005&\u0000\u0000\u0085" +
                                    "\u0016\u0001\u0000\u0000\u0000\u0086\u0087\u0005|\u0000\u0000\u0087\u0088" +
                                    "\u0005|\u0000\u0000\u0088\u0018\u0001\u0000\u0000\u0000\u0089\u008a\u0005" +
                                    "!\u0000\u0000\u008a\u001a\u0001\u0000\u0000\u0000\u008b\u008c\u0005>\u0000" +
                                    "\u0000\u008c\u008d\u0005=\u0000\u0000\u008d\u001c\u0001\u0000\u0000\u0000" +
                                    "\u008e\u008f\u0005<\u0000\u0000\u008f\u0090\u0005=\u0000\u0000\u0090\u001e" +
                                    "\u0001\u0000\u0000\u0000\u0091\u0092\u0005<\u0000\u0000\u0092 \u0001\u0000" +
                                    "\u0000\u0000\u0093\u0094\u0005>\u0000\u0000\u0094\"\u0001\u0000\u0000" +
                                    "\u0000\u0095\u0096\u0005(\u0000\u0000\u0096$\u0001\u0000\u0000\u0000\u0097" +
                                    "\u0098\u0005)\u0000\u0000\u0098&\u0001\u0000\u0000\u0000\u0099\u009a\u0005" +
                                    "{\u0000\u0000\u009a(\u0001\u0000\u0000\u0000\u009b\u009c\u0005}\u0000" +
                                    "\u0000\u009c*\u0001\u0000\u0000\u0000\u009d\u009f\u0007\u0000\u0000\u0000" +
                                    "\u009e\u009d\u0001\u0000\u0000\u0000\u009f\u00a0\u0001\u0000\u0000\u0000" +
                                    "\u00a0\u009e\u0001\u0000\u0000\u0000\u00a0\u00a1\u0001\u0000\u0000\u0000" +
                                    "\u00a1,\u0001\u0000\u0000\u0000\u00a2\u00a4\u0007\u0000\u0000\u0000\u00a3" +
                                    "\u00a2\u0001\u0000\u0000\u0000\u00a4\u00a5\u0001\u0000\u0000\u0000\u00a5" +
                                    "\u00a3\u0001\u0000\u0000\u0000\u00a5\u00a6\u0001\u0000\u0000\u0000\u00a6" +
                                    "\u00a7\u0001\u0000\u0000\u0000\u00a7\u00a9\u0005.\u0000\u0000\u00a8\u00aa" +
                                    "\u0007\u0000\u0000\u0000\u00a9\u00a8\u0001\u0000\u0000\u0000\u00aa\u00ab" +
                                    "\u0001\u0000\u0000\u0000\u00ab\u00a9\u0001\u0000\u0000\u0000\u00ab\u00ac" +
                                    "\u0001\u0000\u0000\u0000\u00ac.\u0001\u0000\u0000\u0000\u00ad\u00ae\u0003" +
                                    "].\u0000\u00ae\u00af\u0003Y,\u0000\u00af\u00b0\u0003_/\u0000\u00b0\u00b1" +
                                    "\u0003?\u001f\u0000\u00b1\u00b9\u0001\u0000\u0000\u0000\u00b2\u00b3\u0003" +
                                    "A \u0000\u00b3\u00b4\u00037\u001b\u0000\u00b4\u00b5\u0003M&\u0000\u00b5" +
                                    "\u00b6\u0003[-\u0000\u00b6\u00b7\u0003?\u001f\u0000\u00b7\u00b9\u0001" +
                                    "\u0000\u0000\u0000\u00b8\u00ad\u0001\u0000\u0000\u0000\u00b8\u00b2\u0001" +
                                    "\u0000\u0000\u0000\u00b90\u0001\u0000\u0000\u0000\u00ba\u00bb\u0003G#" +
                                    "\u0000\u00bb\u00bc\u0003A \u0000\u00bc2\u0001\u0000\u0000\u0000\u00bd" +
                                    "\u00be\u0003?\u001f\u0000\u00be\u00bf\u0003M&\u0000\u00bf\u00c0\u0003" +
                                    "[-\u0000\u00c0\u00c1\u0003?\u001f\u0000\u00c14\u0001\u0000\u0000\u0000" +
                                    "\u00c2\u00c6\u0007\u0001\u0000\u0000\u00c3\u00c5\u0007\u0002\u0000\u0000" +
                                    "\u00c4\u00c3\u0001\u0000\u0000\u0000\u00c5\u00c8\u0001\u0000\u0000\u0000" +
                                    "\u00c6\u00c4\u0001\u0000\u0000\u0000\u00c6\u00c7\u0001\u0000\u0000\u0000" +
                                    "\u00c76\u0001\u0000\u0000\u0000\u00c8\u00c6\u0001\u0000\u0000\u0000\u00c9" +
                                    "\u00ca\u0007\u0003\u0000\u0000\u00ca8\u0001\u0000\u0000\u0000\u00cb\u00cc" +
                                    "\u0007\u0004\u0000\u0000\u00cc:\u0001\u0000\u0000\u0000\u00cd\u00ce\u0007" +
                                    "\u0005\u0000\u0000\u00ce<\u0001\u0000\u0000\u0000\u00cf\u00d0\u0007\u0006" +
                                    "\u0000\u0000\u00d0>\u0001\u0000\u0000\u0000\u00d1\u00d2\u0007\u0007\u0000" +
                                    "\u0000\u00d2@\u0001\u0000\u0000\u0000\u00d3\u00d4\u0007\b\u0000\u0000" +
                                    "\u00d4B\u0001\u0000\u0000\u0000\u00d5\u00d6\u0007\t\u0000\u0000\u00d6" +
                                    "D\u0001\u0000\u0000\u0000\u00d7\u00d8\u0007\n\u0000\u0000\u00d8F\u0001" +
                                    "\u0000\u0000\u0000\u00d9\u00da\u0007\u000b\u0000\u0000\u00daH\u0001\u0000" +
                                    "\u0000\u0000\u00db\u00dc\u0007\f\u0000\u0000\u00dcJ\u0001\u0000\u0000" +
                                    "\u0000\u00dd\u00de\u0007\r\u0000\u0000\u00deL\u0001\u0000\u0000\u0000" +
                                    "\u00df\u00e0\u0007\u000e\u0000\u0000\u00e0N\u0001\u0000\u0000\u0000\u00e1" +
                                    "\u00e2\u0007\u000f\u0000\u0000\u00e2P\u0001\u0000\u0000\u0000\u00e3\u00e4" +
                                    "\u0007\u0010\u0000\u0000\u00e4R\u0001\u0000\u0000\u0000\u00e5\u00e6\u0007" +
                                    "\u0011\u0000\u0000\u00e6T\u0001\u0000\u0000\u0000\u00e7\u00e8\u0007\u0012" +
                                    "\u0000\u0000\u00e8V\u0001\u0000\u0000\u0000\u00e9\u00ea\u0007\u0013\u0000" +
                                    "\u0000\u00eaX\u0001\u0000\u0000\u0000\u00eb\u00ec\u0007\u0014\u0000\u0000" +
                                    "\u00ecZ\u0001\u0000\u0000\u0000\u00ed\u00ee\u0007\u0015\u0000\u0000\u00ee" +
                                    "\\\u0001\u0000\u0000\u0000\u00ef\u00f0\u0007\u0016\u0000\u0000\u00f0^" +
                                    "\u0001\u0000\u0000\u0000\u00f1\u00f2\u0007\u0017\u0000\u0000\u00f2`\u0001" +
                                    "\u0000\u0000\u0000\u00f3\u00f4\u0007\u0018\u0000\u0000\u00f4b\u0001\u0000" +
                                    "\u0000\u0000\u00f5\u00f6\u0007\u0019\u0000\u0000\u00f6d\u0001\u0000\u0000" +
                                    "\u0000\u00f7\u00f8\u0007\u001a\u0000\u0000\u00f8f\u0001\u0000\u0000\u0000" +
                                    "\u00f9\u00fa\u0007\u001b\u0000\u0000\u00fah\u0001\u0000\u0000\u0000\u00fb" +
                                    "\u00fc\u0007\u001c\u0000\u0000\u00fcj\u0001\u0000\u0000\u0000\u00fd\u00ff" +
                                    "\u0007\u001d\u0000\u0000\u00fe\u00fd\u0001\u0000\u0000\u0000\u00ff\u0100" +
                                    "\u0001\u0000\u0000\u0000\u0100\u00fe\u0001\u0000\u0000\u0000\u0100\u0101" +
                                    "\u0001\u0000\u0000\u0000\u0101\u0102\u0001\u0000\u0000\u0000\u0102\u0103" +
                                    "\u00065\u0000\u0000\u0103l\u0001\u0000\u0000\u0000\u0007\u0000\u00a0\u00a5" +
                                    "\u00ab\u00b8\u00c6\u0100\u0001\u0006\u0000\u0000";
    public static final ATN _ATN =
                    new ATNDeserializer().deserialize(_serializedATN.toCharArray());

    static {
        _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
        for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
            _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
        }
    }
}
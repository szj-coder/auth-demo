// Generated from C:/Users/shen/Desktop/worker/auth-demo/src/main/java/com/example/authdemo/antlr/demo1\Rows.g4 by ANTLR 4.12.0
package com.example.authdemo.antlr.demo1.gen;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class RowsLexer extends Lexer {
    static {
        RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION);
    }

    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache =
            new PredictionContextCache();
    public static final int
            TAB = 1, NL = 2, STUFF = 3;
    public static String[] channelNames = {
            "DEFAULT_TOKEN_CHANNEL", "HIDDEN"
    };

    public static String[] modeNames = {
            "DEFAULT_MODE"
    };

    private static String[] makeRuleNames() {
        return new String[]{
                "TAB", "NL", "STUFF"
        };
    }

    public static final String[] ruleNames = makeRuleNames();

    private static String[] makeLiteralNames() {
        return new String[]{
                null, "'\\t'"
        };
    }

    private static final String[] _LITERAL_NAMES = makeLiteralNames();

    private static String[] makeSymbolicNames() {
        return new String[]{
                null, "TAB", "NL", "STUFF"
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


    public RowsLexer(CharStream input) {
        super(input);
        _interp = new LexerATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
    }

    @Override
    public String getGrammarFileName() {
        return "Rows.g4";
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
            "\u0004\u0000\u0003\u0015\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002" +
                    "\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0001\u0000\u0001\u0000\u0001" +
                    "\u0000\u0001\u0000\u0001\u0001\u0003\u0001\r\b\u0001\u0001\u0001\u0001" +
                    "\u0001\u0001\u0002\u0004\u0002\u0012\b\u0002\u000b\u0002\f\u0002\u0013" +
                    "\u0000\u0000\u0003\u0001\u0001\u0003\u0002\u0005\u0003\u0001\u0000\u0001" +
                    "\u0002\u0000\t\n\r\r\u0016\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003" +
                    "\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0001\u0007" +
                    "\u0001\u0000\u0000\u0000\u0003\f\u0001\u0000\u0000\u0000\u0005\u0011\u0001" +
                    "\u0000\u0000\u0000\u0007\b\u0005\t\u0000\u0000\b\t\u0001\u0000\u0000\u0000" +
                    "\t\n\u0006\u0000\u0000\u0000\n\u0002\u0001\u0000\u0000\u0000\u000b\r\u0005" +
                    "\r\u0000\u0000\f\u000b\u0001\u0000\u0000\u0000\f\r\u0001\u0000\u0000\u0000" +
                    "\r\u000e\u0001\u0000\u0000\u0000\u000e\u000f\u0005\n\u0000\u0000\u000f" +
                    "\u0004\u0001\u0000\u0000\u0000\u0010\u0012\b\u0000\u0000\u0000\u0011\u0010" +
                    "\u0001\u0000\u0000\u0000\u0012\u0013\u0001\u0000\u0000\u0000\u0013\u0011" +
                    "\u0001\u0000\u0000\u0000\u0013\u0014\u0001\u0000\u0000\u0000\u0014\u0006" +
                    "\u0001\u0000\u0000\u0000\u0003\u0000\f\u0013\u0001\u0006\u0000\u0000";
    public static final ATN _ATN =
            new ATNDeserializer().deserialize(_serializedATN.toCharArray());

    static {
        _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
        for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
            _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
        }
    }
}
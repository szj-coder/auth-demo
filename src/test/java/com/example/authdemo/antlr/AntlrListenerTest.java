package com.example.authdemo.antlr;

import com.example.authdemo.antlr.gen.AntlrDemoLexer;
import com.example.authdemo.antlr.gen.AntlrDemoParser;
import com.example.authdemo.antlr.kernal.MyAntlrListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * listener 测试类
 */
public class AntlrListenerTest {

    @Test
    public void test() {
        assertEquals(7, execute("1+2*3"));
    }

    @Test
    public void operationTest() {
        assertEquals(3, execute("1+2"));
        assertEquals(1, execute("1"));
        assertEquals(7, execute("1+2*3"));
//        assertEquals(0, execute("1+2-3"));
    }

    private static Object execute(String expression) {
        return execute(expression, new HashMap<>());
    }

    private static Object execute(String expression, Map<String, Object> map) {
        CharStream input = CharStreams.fromString(expression);
        AntlrDemoLexer lexer = new AntlrDemoLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        AntlrDemoParser parser = new AntlrDemoParser(tokens);
        final MyAntlrListener listener = new MyAntlrListener();
        parser.addParseListener(listener);
        parser.script();
        return listener.getResult();
    }
}

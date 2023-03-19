package com.example.authdemo.antlr;

import com.example.authdemo.antlr.gen.AntlrDemoLexer;
import com.example.authdemo.antlr.gen.AntlrDemoParser;
import com.example.authdemo.antlr.kernal.MyAntlrListener;
import com.example.authdemo.antlr.kernal.MyDynamicVisitor;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AntlrDemoTest {

    @Test
    public void test() {
        Assertions.assertEquals(1.0, execute("1 / 0"));
    }

    @Test
    public void batchTest() {
        Assertions.assertEquals(1, execute("1"));
        Assertions.assertEquals(3, execute("1+2"));
        Assertions.assertEquals(133, execute(" 111 + 22 "));
        Assertions.assertEquals(7, execute("1+2 * 3"));
        Assertions.assertEquals(5, execute("1*2 + 3"));
        Assertions.assertEquals(9, execute("(1+2) * 3"));
        Assertions.assertEquals(2, execute("1==1? 2:3"));
        Assertions.assertEquals(2, execute("1 +2==3? 2:3"));
        Assertions.assertEquals(1.0, execute("1 * 1.0"));
    }

    private Object execute(String expression) {
        CharStream input = CharStreams.fromString(expression);
        AntlrDemoLexer lexer = new AntlrDemoLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        AntlrDemoParser parser = new AntlrDemoParser(tokens);
        parser.addParseListener(new MyAntlrListener());
        final MyDynamicVisitor intVisitor = new MyDynamicVisitor();
        return intVisitor.visit(parser.expr());
    }
}

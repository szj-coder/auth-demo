package com.example.authdemo.antlr;

import com.example.authdemo.antlr.gen.AntlrDemoLexer;
import com.example.authdemo.antlr.gen.AntlrDemoParser;
import com.example.authdemo.antlr.kernal.IntegerDemoVisitor;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * antlr 的一些用法
 * @author szj
 */
public class AntlrDemoTest {

    @Test
    public void demo() {
        CharStream input = CharStreams.fromString("-1 * ( 0 + -10 ) / -5");
        AntlrDemoLexer lexer = new AntlrDemoLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        AntlrDemoParser parser = new AntlrDemoParser(tokens);
//        System.out.println(parser.expr().toStringTree(parser));
        final IntegerDemoVisitor intVisitor = new IntegerDemoVisitor();
        final Integer result = intVisitor.visit(parser.expr());
        System.out.println(result);
    }

    @Test
    public void test() {
        Assertions.assertEquals(3, execute("1+2"));
        Assertions.assertEquals(7, execute("1+2 * 3"));
        Assertions.assertEquals(9, execute("(1+2) * 3"));
    }

    private Integer execute(String expression) {
        CharStream input = CharStreams.fromString(expression);
        AntlrDemoLexer lexer = new AntlrDemoLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        AntlrDemoParser parser = new AntlrDemoParser(tokens);
        final IntegerDemoVisitor intVisitor = new IntegerDemoVisitor();
        return intVisitor.visit(parser.expr());
    }
}

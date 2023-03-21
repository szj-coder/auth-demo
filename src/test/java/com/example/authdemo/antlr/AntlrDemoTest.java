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
    public void singleTest() {
    }

    @Test
    public void baseTest() {
        Assertions.assertEquals(1, execute("1"));
        Assertions.assertEquals(3, execute("1+2"));
        Assertions.assertEquals(133, execute(" 111 + 22 "));
        Assertions.assertEquals(7, execute("1+2 * 3"));
        Assertions.assertEquals(5, execute("1*2 + 3"));
        Assertions.assertEquals(9, execute("(1+2) * 3"));
        Assertions.assertEquals(2, execute("1==1? 2:3"));
        Assertions.assertEquals(2, execute("1 +2==3? 2:3"));
        Assertions.assertEquals(1.0, execute("1 * 1.0"));
        Assertions.assertEquals(7, execute("(1*2) + (2 + 3)"));
    }

    @Test
    public void blockTest() {
        Assertions.assertEquals(6, execute("{1+2;3+3}"));
        Assertions.assertEquals(6, execute("{1+2;3+3;}"));
        Assertions.assertEquals(333, execute("{1+2;\n 111+ 222}"));
        Assertions.assertEquals(333, execute("1+2; {1+2;\n 111+ 222}"));
        Assertions.assertEquals(2, execute("{1+2;\n 111+ 222; {1+1}}"));
        Assertions.assertEquals(2, execute("{1+2;\n 111+ 222 {1+1}}"));
        Assertions.assertEquals(7, execute("{1+2;{2+3}3+4;}"));
        Assertions.assertEquals(2, execute("{{{{{1+1}}}}}"));
        Assertions.assertEquals(4, execute("{1+1}1+2{2+2}"));
        Assertions.assertEquals(0, execute("{0}"));
    }

    @Test
    public void ifTest() {
        Assertions.assertEquals(3, execute("if(true){1+2}"));
        Assertions.assertEquals(3, execute("if(false){2} else {3}"));
        Assertions.assertEquals(2, execute("if(true){2} else {3}"));
        Assertions.assertEquals(4, execute("if(false){2} else if(true){4}"));
        Assertions.assertEquals(8, execute("if(1==2){2+1} else if(2==2){4+4}"));
    }

    private Object execute(String expression) {
        CharStream input = CharStreams.fromString(expression);
        AntlrDemoLexer lexer = new AntlrDemoLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        AntlrDemoParser parser = new AntlrDemoParser(tokens);
        parser.addParseListener(new MyAntlrListener());
        final MyDynamicVisitor intVisitor = new MyDynamicVisitor();
        return intVisitor.visit(parser.script());
    }
}

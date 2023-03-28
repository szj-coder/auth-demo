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

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * antlr测试
 */
public class AntlrDemoTest {

    @Test
    public void scriptTest() {
        assertEquals(444, execute("a = 123;b=321;a+b"));
    }

    @Test
    public void baseTest() {
        assertEquals(1, execute("1"));
        assertEquals(3, execute("1+2"));
        assertEquals(133, execute(" 111 + 22 "));
        assertEquals(7, execute("1+2 * 3"));
        assertEquals(5, execute("1*2 + 3"));
        assertEquals(9, execute("(1+2) * 3"));
        assertEquals(2, execute("1==1? 2:3"));
        assertEquals(2, execute("1 +2==3? 2:3"));
        assertEquals(1.0, execute("1 * 1.0"));
        assertEquals(7, execute("(1*2) + (2 + 3)"));
        assertEquals(true, execute("true"));
        assertEquals(false, execute("false"));
    }

    @Test
    public void blockTest() {
        assertEquals(6, execute("{1+2;3+3}"));
        assertEquals(6, execute("{1+2;3+3;}"));
        assertEquals(333, execute("{1+2;\n 111+ 222}"));
        assertEquals(333, execute("1+2; {1+2;\n 111+ 222}"));
        assertEquals(2, execute("{1+2;\n 111+ 222; {1+1}}"));
        assertEquals(2, execute("{1+2;\n 111+ 222 {1+1}}"));
        assertEquals(7, execute("{1+2;{2+3}3+4;}"));
        assertEquals(2, execute("{{{{{1+1}}}}}"));
        assertEquals(4, execute("{1+1}1+2{2+2}"));
        assertEquals(0, execute("{0}"));
    }

    @Test
    public void ifTest() {
        assertEquals(3, execute("IF(true){1+2}"));
        assertEquals(3, execute("if(true)1+2;"));
        assertEquals(3, execute("if(true)1+2"));
        assertEquals(5, execute("if(true)1+2; 2+3"));
        assertEquals(5, execute("if(true){1+2; 2+3}"));
        assertEquals(3, execute("if(false){2} else {3}"));
        assertEquals(2, execute("if(true){2} else {3}"));
        assertEquals(4, execute("if(false){2} else if(true){4}"));
        assertEquals(8, execute("if(1==2){2+1} else if(2==2){4+4}"));
    }

    @Test
    public void varTest() {
        assertEquals(123, execute("a = 123;"));
        assertEquals(444, execute("a = 123;b=321;a+b"));
        Assertions.assertThrows(RuntimeException.class, () -> execute("a+b"));
        Assertions.assertThrows(RuntimeException.class, () -> execute("a=123;a+b"));
    }

    @Test
    public void operationTest() {
        assertEquals(false, execute("1 > 2"));
        assertEquals(true, execute("2>1"));
        assertEquals(true, execute("1>=1"));
        assertEquals(123, execute("if(a>10){123}else{321}", Map.of("a", 11)));
        assertEquals(3211, execute("if(a>10){123}else{3211}", Map.of("a", 10)));
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

    private Object execute(String expression, Map<String, Object> map) {
        CharStream input = CharStreams.fromString(expression);
        AntlrDemoLexer lexer = new AntlrDemoLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        AntlrDemoParser parser = new AntlrDemoParser(tokens);
        parser.addParseListener(new MyAntlrListener());
        final MyDynamicVisitor intVisitor = new MyDynamicVisitor(Optional.ofNullable(map).orElse(new HashMap<>()));
        return intVisitor.visit(parser.script());
    }
}

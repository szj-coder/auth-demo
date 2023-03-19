package com.example.authdemo.antlr.kernal;

import com.example.authdemo.antlr.gen.AntlrDemoBaseListener;
import com.example.authdemo.antlr.gen.AntlrDemoParser;
import lombok.extern.slf4j.Slf4j;

import java.util.Stack;

@Slf4j
public class MyAntlrListener extends AntlrDemoBaseListener {
    private Stack<Object> stack = new Stack<>();


    @Override
    public void exitPlusOrMinus(AntlrDemoParser.PlusOrMinusContext ctx) {
        log.info("exit Plus Or Minus");
    }

    @Override
    public void exitObjFactory(AntlrDemoParser.ObjFactoryContext ctx) {
        log.info("exit obj factory");
    }
}

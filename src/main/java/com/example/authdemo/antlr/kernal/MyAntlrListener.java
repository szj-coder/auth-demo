package com.example.authdemo.antlr.kernal;

import com.example.authdemo.antlr.gen.AntlrDemoBaseListener;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Stack;

@Slf4j
public class MyAntlrListener extends AntlrDemoBaseListener {
    private Stack<Object> stack = new Stack<>();

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {
        log.info(String.format("exit %20.20s >> %s", ctx.getClass().getSimpleName(), ctx.getText()));
    }
}

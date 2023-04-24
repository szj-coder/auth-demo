package com.example.authdemo.antlr.kernal;

import com.example.authdemo.antlr.gen.AntlrDemoBaseListener;
import com.example.authdemo.antlr.gen.AntlrDemoParser;
import com.example.authdemo.antlr.util.BaseTypePromotion;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.Stack;

@Slf4j
public class MyAntlrListener extends AntlrDemoBaseListener {
    private final Stack<Object> stack = new Stack<>();
    private final AntlrContext varContext = new AntlrContext();

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {
        log.info(String.format("enter %20.20s >> %s", ctx.getClass().getSimpleName(), ctx.getText()));
    }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {
        log.info(String.format("exit %20.20s >> %s", ctx.getClass().getSimpleName(), ctx.getText()));
    }

    @Override
    public void exitMultOrDiv(AntlrDemoParser.MultOrDivContext ctx) {
        final Object right = stack.pop();
        final Object left = stack.pop();

        Object result = null;
        if (ctx.DIV() != null) {
            result = BaseTypePromotion.div(left, right);
        } else if (ctx.MULT() != null) {
            result = BaseTypePromotion.mult(left, right);
        }
        if (result == null) {
            throw new RuntimeException("不识别的操作符");
        }
        stack.push(result);
    }

    @Override
    public void exitPlusOrMinus(AntlrDemoParser.PlusOrMinusContext ctx) {
        final Object right = stack.pop();
        final Object left = stack.pop();

        Object result = null;
        if (ctx.PLUS() != null) {
            result = BaseTypePromotion.plus(left, right);
        } else if (ctx.MINUS() != null) {
            result = BaseTypePromotion.minus(left, right);
        }
        if (result == null) {
            throw new RuntimeException("不识别的操作符");
        }
        stack.push(result);
    }

    @Override
    public void exitObjFactory(AntlrDemoParser.ObjFactoryContext ctx) {
        Object result = null;
        if (ctx.INTEGER() != null) {
            result = Integer.valueOf(ctx.getText());
        } else if (ctx.BOOLEAN() != null) {
            result = Boolean.parseBoolean(ctx.getText());
        } else if (ctx.DOUBLE() != null) {
            result = Double.valueOf(ctx.getText());
        } else if (ctx.VARIABLE() != null) {
            final TerminalNode variable = ctx.VARIABLE();
            if (!varContext.containsKey(ctx.getText())) {
                throw new RuntimeException(String.format("变量:%s 不存在", ctx.getText()));
            }
            result = varContext.getValue(variable.getText());
        }
        stack.push(result);
    }

    public Object getResult() {
        if (stack.isEmpty()) {
            return null;
        }
        return stack.pop();
    }

}

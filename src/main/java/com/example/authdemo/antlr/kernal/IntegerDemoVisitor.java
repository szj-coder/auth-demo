package com.example.authdemo.antlr.kernal;

import com.example.authdemo.antlr.gen.AntlrDemoBaseVisitor;
import com.example.authdemo.antlr.gen.AntlrDemoParser;

public class IntegerDemoVisitor extends AntlrDemoBaseVisitor<Integer> {
    @Override
    public Integer visitMultOrDiv(AntlrDemoParser.MultOrDivContext ctx) {
        final Integer left = visit(ctx.expr(0));
        final Integer right = visit(ctx.expr(1));
        if (ctx.MULT() != null) {
            return left * right;
        } else if (ctx.DIV() != null){
            return left / right;
        } else {
            throw new RuntimeException("不识别");
        }
    }

    @Override
    public Integer visitPlusOrMinus(AntlrDemoParser.PlusOrMinusContext ctx) {
        final Integer left = visit(ctx.expr(0));
        final Integer right = visit(ctx.expr(1));
        if (ctx.PLUS() != null) {
            return left + right;
        } else if (ctx.MINUS() != null){
            return left - right;
        } else {
            throw new RuntimeException("不识别");
        }
    }

    @Override
    public Integer visitFactorExpr(AntlrDemoParser.FactorExprContext ctx) {
        return visit(ctx.factor());
    }

    @Override
    public Integer visitParenExpr(AntlrDemoParser.ParenExprContext ctx) {
        return visit(ctx.expr());
    }

    private Integer parseFactor(AntlrDemoParser.FactorContext factorContext) {
        if (factorContext.INTEGER() != null) {
            return Integer.parseInt(factorContext.getText());
        }
        return null;
    }

    @Override
    public Integer visitFactor(AntlrDemoParser.FactorContext ctx) {
        if (ctx.INTEGER() != null) {
            return Integer.parseInt(ctx.INTEGER().getText());
        } else {
            throw new RuntimeException("123");
        }
    }
}

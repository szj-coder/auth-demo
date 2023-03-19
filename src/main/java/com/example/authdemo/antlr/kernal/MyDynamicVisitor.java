package com.example.authdemo.antlr.kernal;

import com.example.authdemo.antlr.gen.AntlrDemoBaseVisitor;
import com.example.authdemo.antlr.gen.AntlrDemoParser;

import java.util.Arrays;
import java.util.List;

public class MyDynamicVisitor extends AntlrDemoBaseVisitor<Object> {
    @Override
    public Object visitMultOrDiv(AntlrDemoParser.MultOrDivContext ctx) {
        final Object left = visit(ctx.expr(0));
        final Object right = visit(ctx.expr(1));

        final List<? extends Class<? extends Number>> integers = Arrays.asList(Byte.class, Short.class, Integer.class, Long.class);
        if (ctx.DIV() != null) {
            if (integers.contains(left.getClass()) && integers.contains(right.getClass())) {
                return ((Number) left).intValue() / ((Number) right).intValue();
            } else {
                return ((Number) left).doubleValue() / ((Number) right).doubleValue();
            }
        } else if (ctx.MULT() != null) {
            if (integers.contains(left.getClass()) && integers.contains(right.getClass())) {
                return ((Number) left).intValue() * ((Number) right).intValue();
            } else {
                return ((Number) left).doubleValue() * ((Number) right).doubleValue();
            }
        }
        throw new RuntimeException("不识别的操作符");
    }

    @Override
    public Object visitPlusOrMinus(AntlrDemoParser.PlusOrMinusContext ctx) {
        final Object left = visit(ctx.expr(0));
        final Object right = visit(ctx.expr(1));

        final List<? extends Class<? extends Number>> integers = Arrays.asList(Byte.class, Short.class, Integer.class, Long.class);
        if (ctx.PLUS() != null) {
            if (left instanceof String || right instanceof String) {
                return String.valueOf(left) + right;
            } else if (integers.contains(left.getClass())&& integers.contains(right.getClass())) {
                return ((Number) left).intValue() + ((Number) right).intValue();
            } else {
                return ((Number) left).doubleValue() + ((Number) right).doubleValue();
            }
        } else if (ctx.MINUS() != null) {
            if (integers.contains(left.getClass()) && integers.contains(right.getClass())) {
                return ((Number) left).intValue() - ((Number) right).intValue();
            } else {
                return ((Number) left).doubleValue() - ((Number) right).doubleValue();
            }
        }
        throw new RuntimeException("不识别的操作符");
    }

    @Override
    public Object visitOperatorExpr(AntlrDemoParser.OperatorExprContext ctx) {
        final Object left = visit(ctx.expr(0));
        final Object right = visit(ctx.expr(1));
        return left == right;
    }

    @Override
    public Object visitTernaryOperator(AntlrDemoParser.TernaryOperatorContext ctx) {
        final Object condition = visit(ctx.expr(0));
        if (Boolean.parseBoolean(String.valueOf(condition))) {
            return visit(ctx.expr(1));
        } else {
            return visit(ctx.expr(2));
        }
    }

    @Override
    public Object visitFactorExpr(AntlrDemoParser.FactorExprContext ctx) {
        return visit(ctx.factor());
    }

    @Override
    public Object visitParenExpr(AntlrDemoParser.ParenExprContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Object visitObjFactory(AntlrDemoParser.ObjFactoryContext ctx) {
        if (ctx.INTEGER() != null) {
            return Integer.valueOf(ctx.getText());
        } else if (ctx.BOOLEAN() != null) {
            return Boolean.parseBoolean(ctx.getText());
        } else if (ctx.DOUBLE() != null) {
            return Double.valueOf(ctx.getText());
        }
        throw new RuntimeException("不识别的因子类型");
    }
}

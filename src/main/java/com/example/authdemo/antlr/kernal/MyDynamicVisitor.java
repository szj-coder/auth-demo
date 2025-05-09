package com.example.authdemo.antlr.kernal;

import com.example.authdemo.antlr.gen.AntlrDemoBaseVisitor;
import com.example.authdemo.antlr.gen.AntlrDemoParser;
import com.example.authdemo.antlr.util.BaseTypePromotion;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class MyDynamicVisitor extends AntlrDemoBaseVisitor<Object> {

    private AntlrContext varContext;
    private Object current = null;

    public MyDynamicVisitor() {
        this(new HashMap<>());
    }

    public MyDynamicVisitor(Map<String, Object> map) {
        this.varContext = new AntlrContext(new HashMap<>(Optional.ofNullable(map).orElse(Collections.emptyMap())));
    }

    @Override
    public Object visitScript(AntlrDemoParser.ScriptContext ctx) {
        return withStack(() -> {
            for (AntlrDemoParser.StatementsContext statement : ctx.statements()) {
                current = visit(statement);
            }
            return current;
        });
    }

    @Override
    public Object visitStatements(AntlrDemoParser.StatementsContext ctx) {
        for (ParseTree child : ctx.children) {
            if (child instanceof ParserRuleContext) {
                current = visit(child);
            }
        }
        return current;
    }

    @Override
    public Object visitStatementBlock(AntlrDemoParser.StatementBlockContext ctx) {
        return withStack(() -> {
            current = visit(ctx.statements());
            return current;
        });
    }

    @Override
    public Object visitIf(AntlrDemoParser.IfContext ctx) {
        if (Boolean.TRUE == visit(ctx.expr())) {
            current = visit(ctx.statementBlock());
        }
        return current;
    }

    @Override
    public Object visitIfElse(AntlrDemoParser.IfElseContext ctx) {
        if (Boolean.TRUE == visit(ctx.expr())) {
            current = visit(ctx.statementBlock(0));
        } else {
            current = visit(ctx.statementBlock(1));
        }
        return current;
    }

    @Override
    public Object visitIfElseIf(AntlrDemoParser.IfElseIfContext ctx) {
        if (Boolean.TRUE == visit(ctx.expr())) {
            current = visit(ctx.statementBlock());
        } else {
            current = visit(ctx.ifExpression());
        }
        return current;
    }

    @Override
    public Object visitStatement(AntlrDemoParser.StatementContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Object visitMultOrDiv(AntlrDemoParser.MultOrDivContext ctx) {
        final Object left = visit(ctx.expr(0));
        final Object right = visit(ctx.expr(1));

        if (ctx.DIV() != null) {
            return BaseTypePromotion.div(left, right);
        } else if (ctx.MULT() != null) {
            return BaseTypePromotion.mult(left, right);
        }
        throw new RuntimeException("不识别的操作符");
    }

    @Override
    public Object visitPlusOrMinus(AntlrDemoParser.PlusOrMinusContext ctx) {
        final Object left = visit(ctx.expr(0));
        final Object right = visit(ctx.expr(1));

        if (ctx.PLUS() != null) {
            return BaseTypePromotion.plus(left, right);
        } else if (ctx.MINUS() != null) {
            return BaseTypePromotion.minus(left, right);
        }
        throw new RuntimeException("不识别的操作符");
    }

    @Override
    public Object visitOperatorExpr(AntlrDemoParser.OperatorExprContext ctx) {
        final Object left = visit(ctx.expr(0));
        Supplier<?> right = () -> visit(ctx.expr(1));
        final String opText = ctx.op.getText();
        switch (opText) {
            case "==":
                return BaseTypePromotion.compareTo(left, right.get()) == 0;
            case "!=":
                return BaseTypePromotion.compareTo(left, right.get()) != 0;
            case "&&":
                return BaseTypePromotion.parseBoolean(left) && BaseTypePromotion.parseBoolean(right.get());
            case "||":
                return BaseTypePromotion.parseBoolean(left) || BaseTypePromotion.parseBoolean(right.get());
            case "<":
                return BaseTypePromotion.compareTo(left, right.get()) < 0;
            case ">":
                return BaseTypePromotion.compareTo(left, right.get()) > 0;
            case "<=":
                return BaseTypePromotion.compareTo(left, right.get()) <= 0;
            case ">=":
                return BaseTypePromotion.compareTo(left, right.get()) >= 0;
            case "-":
                return BaseTypePromotion.negative(left);
            default:
                throw new RuntimeException("操作符不支持");
        }
    }

    @Override
    public Object visitLogicalOperator(AntlrDemoParser.LogicalOperatorContext ctx) {
        final Object visit = visit(ctx.expr());
        if (visit == null || visit.getClass() != Boolean.class) {
            throw new RuntimeException("%s 类型不支持取反操作".formatted(visit));
        }
        return Boolean.FALSE == visit;
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
        if (ctx.BOOLEAN() != null) {
            return Boolean.parseBoolean(ctx.getText());
        } else if (ctx.VARIABLE() != null) {
            final TerminalNode variable = ctx.VARIABLE();
            if (!varContext.containsKey(ctx.getText())) {
                throw new RuntimeException("变量:%s 不存在".formatted(ctx.getText()));
            }
            return varContext.getValue(variable.getText());
        } else if (ctx.number() != null) {
            return visit(ctx.number());
        }
        throw new RuntimeException("不识别的因子类型");
    }

    @Override
    public Object visitNumFactory(AntlrDemoParser.NumFactoryContext ctx) {
        if (ctx.INTEGER() != null) {
            return Integer.valueOf(ctx.getText());
        } else if (ctx.DOUBLE() != null) {
            return Double.valueOf(ctx.getText());
        }
        throw new RuntimeException("不识别的数字类型");
    }

    @Override
    public Object visitVarExpression(AntlrDemoParser.VarExpressionContext ctx) {
        final TerminalNode variable = ctx.VARIABLE();
        final Object value = visit(ctx.expr());
        varContext.setValue(variable.getText(), value);
        current = value;
        return value;
    }

    @Override
    public Object visitTerminal(TerminalNode node) {
        return current;
    }

    private <T> T withStack(Supplier<T> supplier) {
        push();
        try {
            return supplier.get();
        } finally {
            pop();
        }
    }

    private void push() {
        varContext = varContext.push();
    }

    private void pop() {
        varContext = varContext.pop();
    }
}

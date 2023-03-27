package com.example.authdemo.antlr.kernal;

import com.example.authdemo.antlr.gen.AntlrDemoBaseVisitor;
import com.example.authdemo.antlr.gen.AntlrDemoParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.*;
import java.util.function.Supplier;

public class MyDynamicVisitor extends AntlrDemoBaseVisitor<Object> {

    private AntlrContext varContext = new AntlrContext();
    private Object current = null;

    public MyDynamicVisitor() {
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
            } else if (integers.contains(left.getClass()) && integers.contains(right.getClass())) {
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
        Supplier<?> right = () -> visit(ctx.expr(1));
        final String opText = ctx.op.getText();
        switch (opText) {
            case "==":
                return left == right.get();
            case "!=":
                return left != right.get();
            case "&&":
                return getBooleanOperationAndCheck(left, opText) && getBooleanOperationAndCheck(right.get(), opText);
            case "||":
                return getBooleanOperationAndCheck(left, opText) || getBooleanOperationAndCheck(right.get(), opText);
            case "<":
//                return getBooleanOperationAndCheck(left, opText) < getBooleanOperationAndCheck(right.get(), opText);
            case ">":
                break;
            case "<=":
                break;
            case ">=":
                break;
            default:
                throw new RuntimeException("操作符不支持");

        }
        return null;
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
        } else if (ctx.VARIABLE() != null) {
            final TerminalNode variable = ctx.VARIABLE();
            if (!varContext.containsKey(ctx.getText())) {
                throw new RuntimeException(String.format("变量:%s 不存在", ctx.getText()));
            }
            return varContext.getValue(variable.getText());
        }
        throw new RuntimeException("不识别的因子类型");
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

    private Comparable<?> getComparableAndCheck(Object obj, String operationName) {
//        if (obj == null) {
//            throw exSup.get();
//        }
//        if (obj instanceof Comparable) {
//            return (Comparable<?>) obj;
//        }
//        throw exSup.get();
        return null;
    }

    private Comparable<?> getComparableAndCheck(Object obj, Supplier<RuntimeException> exSup) {
        if (obj == null) {
            throw exSup.get();
        }
        if (obj instanceof Comparable) {
            return (Comparable<?>) obj;
        }
        throw exSup.get();
    }

    private boolean getBooleanOperationAndCheck(Object obj, String operationName) {
        return getBooleanAndCheck(obj, () -> new RuntimeException(String.format("%s类型不支持%s运算", obj.getClass().getSimpleName(), operationName)));
    }

    private boolean getBooleanAndCheck(Object obj, Supplier<RuntimeException> exSup) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        throw exSup.get();
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

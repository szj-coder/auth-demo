// Generated from java-escape by ANTLR 4.11.1
package com.example.authdemo.antlr.gen;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link AntlrDemoParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface AntlrDemoVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link AntlrDemoParser#script}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScript(AntlrDemoParser.ScriptContext ctx);
	/**
	 * Visit a parse tree produced by {@link AntlrDemoParser#statements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatements(AntlrDemoParser.StatementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link AntlrDemoParser#statementBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementBlock(AntlrDemoParser.StatementBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link AntlrDemoParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(AntlrDemoParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multOrDiv}
	 * labeled alternative in {@link AntlrDemoParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultOrDiv(AntlrDemoParser.MultOrDivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code operatorExpr}
	 * labeled alternative in {@link AntlrDemoParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperatorExpr(AntlrDemoParser.OperatorExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code plusOrMinus}
	 * labeled alternative in {@link AntlrDemoParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlusOrMinus(AntlrDemoParser.PlusOrMinusContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ternaryOperator}
	 * labeled alternative in {@link AntlrDemoParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTernaryOperator(AntlrDemoParser.TernaryOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code factorExpr}
	 * labeled alternative in {@link AntlrDemoParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactorExpr(AntlrDemoParser.FactorExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link AntlrDemoParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenExpr(AntlrDemoParser.ParenExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code objFactory}
	 * labeled alternative in {@link AntlrDemoParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjFactory(AntlrDemoParser.ObjFactoryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code if}
	 * labeled alternative in {@link AntlrDemoParser#ifExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf(AntlrDemoParser.IfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifElse}
	 * labeled alternative in {@link AntlrDemoParser#ifExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfElse(AntlrDemoParser.IfElseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifElseIf}
	 * labeled alternative in {@link AntlrDemoParser#ifExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfElseIf(AntlrDemoParser.IfElseIfContext ctx);
}
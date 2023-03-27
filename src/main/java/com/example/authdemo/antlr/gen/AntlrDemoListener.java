// Generated from java-escape by ANTLR 4.11.1
package com.example.authdemo.antlr.gen;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link AntlrDemoParser}.
 */
public interface AntlrDemoListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link AntlrDemoParser#script}.
	 * @param ctx the parse tree
	 */
	void enterScript(AntlrDemoParser.ScriptContext ctx);
	/**
	 * Exit a parse tree produced by {@link AntlrDemoParser#script}.
	 * @param ctx the parse tree
	 */
	void exitScript(AntlrDemoParser.ScriptContext ctx);
	/**
	 * Enter a parse tree produced by {@link AntlrDemoParser#statements}.
	 * @param ctx the parse tree
	 */
	void enterStatements(AntlrDemoParser.StatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link AntlrDemoParser#statements}.
	 * @param ctx the parse tree
	 */
	void exitStatements(AntlrDemoParser.StatementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link AntlrDemoParser#statementBlock}.
	 * @param ctx the parse tree
	 */
	void enterStatementBlock(AntlrDemoParser.StatementBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link AntlrDemoParser#statementBlock}.
	 * @param ctx the parse tree
	 */
	void exitStatementBlock(AntlrDemoParser.StatementBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link AntlrDemoParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(AntlrDemoParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link AntlrDemoParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(AntlrDemoParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multOrDiv}
	 * labeled alternative in {@link AntlrDemoParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMultOrDiv(AntlrDemoParser.MultOrDivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multOrDiv}
	 * labeled alternative in {@link AntlrDemoParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMultOrDiv(AntlrDemoParser.MultOrDivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicalOperator}
	 * labeled alternative in {@link AntlrDemoParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOperator(AntlrDemoParser.LogicalOperatorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicalOperator}
	 * labeled alternative in {@link AntlrDemoParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOperator(AntlrDemoParser.LogicalOperatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code operatorExpr}
	 * labeled alternative in {@link AntlrDemoParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterOperatorExpr(AntlrDemoParser.OperatorExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code operatorExpr}
	 * labeled alternative in {@link AntlrDemoParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitOperatorExpr(AntlrDemoParser.OperatorExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code plusOrMinus}
	 * labeled alternative in {@link AntlrDemoParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterPlusOrMinus(AntlrDemoParser.PlusOrMinusContext ctx);
	/**
	 * Exit a parse tree produced by the {@code plusOrMinus}
	 * labeled alternative in {@link AntlrDemoParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitPlusOrMinus(AntlrDemoParser.PlusOrMinusContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ternaryOperator}
	 * labeled alternative in {@link AntlrDemoParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterTernaryOperator(AntlrDemoParser.TernaryOperatorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ternaryOperator}
	 * labeled alternative in {@link AntlrDemoParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitTernaryOperator(AntlrDemoParser.TernaryOperatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code factorExpr}
	 * labeled alternative in {@link AntlrDemoParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFactorExpr(AntlrDemoParser.FactorExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code factorExpr}
	 * labeled alternative in {@link AntlrDemoParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFactorExpr(AntlrDemoParser.FactorExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link AntlrDemoParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParenExpr(AntlrDemoParser.ParenExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link AntlrDemoParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParenExpr(AntlrDemoParser.ParenExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code objFactory}
	 * labeled alternative in {@link AntlrDemoParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterObjFactory(AntlrDemoParser.ObjFactoryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code objFactory}
	 * labeled alternative in {@link AntlrDemoParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitObjFactory(AntlrDemoParser.ObjFactoryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code if}
	 * labeled alternative in {@link AntlrDemoParser#ifExpression}.
	 * @param ctx the parse tree
	 */
	void enterIf(AntlrDemoParser.IfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code if}
	 * labeled alternative in {@link AntlrDemoParser#ifExpression}.
	 * @param ctx the parse tree
	 */
	void exitIf(AntlrDemoParser.IfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifElse}
	 * labeled alternative in {@link AntlrDemoParser#ifExpression}.
	 * @param ctx the parse tree
	 */
	void enterIfElse(AntlrDemoParser.IfElseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifElse}
	 * labeled alternative in {@link AntlrDemoParser#ifExpression}.
	 * @param ctx the parse tree
	 */
	void exitIfElse(AntlrDemoParser.IfElseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifElseIf}
	 * labeled alternative in {@link AntlrDemoParser#ifExpression}.
	 * @param ctx the parse tree
	 */
	void enterIfElseIf(AntlrDemoParser.IfElseIfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifElseIf}
	 * labeled alternative in {@link AntlrDemoParser#ifExpression}.
	 * @param ctx the parse tree
	 */
	void exitIfElseIf(AntlrDemoParser.IfElseIfContext ctx);
	/**
	 * Enter a parse tree produced by {@link AntlrDemoParser#varExpression}.
	 * @param ctx the parse tree
	 */
	void enterVarExpression(AntlrDemoParser.VarExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AntlrDemoParser#varExpression}.
	 * @param ctx the parse tree
	 */
	void exitVarExpression(AntlrDemoParser.VarExpressionContext ctx);
}
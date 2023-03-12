// Generated from java-escape by ANTLR 4.11.1
package com.example.authdemo.antlr.gen;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link AntlrDemoParser}.
 */
public interface AntlrDemoListener extends ParseTreeListener {
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
	 * Enter a parse tree produced by {@link AntlrDemoParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(AntlrDemoParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link AntlrDemoParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(AntlrDemoParser.FactorContext ctx);
}
package com.example.authdemo.antlr.kernal;

import com.example.authdemo.antlr.gen.AntlrDemoBaseListener;
import com.example.authdemo.antlr.gen.AntlrDemoParser;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

/**
 * @author szj
 */
@Slf4j
public class CustomIzeListener extends AntlrDemoBaseListener {
    @Getter
    private int result;

    @Override
    public void enterMultOrDiv(AntlrDemoParser.MultOrDivContext ctx) {
        super.enterMultOrDiv(ctx);
    }

    @Override
    public void exitMultOrDiv(AntlrDemoParser.MultOrDivContext ctx) {
        super.exitMultOrDiv(ctx);
    }

    @Override
    public void enterPlusOrMinus(AntlrDemoParser.PlusOrMinusContext ctx) {
        super.enterPlusOrMinus(ctx);
    }

    @Override
    public void exitPlusOrMinus(AntlrDemoParser.PlusOrMinusContext ctx) {
        super.exitPlusOrMinus(ctx);
    }

    @Override
    public void exitFactorExpr(AntlrDemoParser.FactorExprContext ctx) {
        super.exitFactorExpr(ctx);
    }

    @Override
    public void exitParenExpr(AntlrDemoParser.ParenExprContext ctx) {
        result = Integer.parseInt(ctx.getText());
    }

    @Override
    public void enterFactor(AntlrDemoParser.FactorContext ctx) {
        super.enterFactor(ctx);
    }

    @Override
    public void exitFactor(AntlrDemoParser.FactorContext ctx) {
        result = Integer.parseInt(ctx.getText());
    }

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {
        super.enterEveryRule(ctx);
    }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {
        super.exitEveryRule(ctx);
    }

    @Override
    public void visitTerminal(TerminalNode node) {
        super.visitTerminal(node);
    }

    @Override
    public void visitErrorNode(ErrorNode node) {
        super.visitErrorNode(node);
    }
}

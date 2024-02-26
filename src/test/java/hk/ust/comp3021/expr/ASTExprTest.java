package hk.ust.comp3021.expr;

import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ASTExprTest {
    @Tag(TestKind.PUBLIC)
    @Test
    void testCreateASTExpr1() {
        XMLNode exprNode = XMLNodeGen.generateSampleAttributeExpr(false);
        ASTExpr expr = ASTExpr.createASTExpr(exprNode);
        assertInstanceOf(AttributeExpr.class, expr);
    }

    @Tag(TestKind.PUBLIC)
    @Test
    void testNameExprTest() {
        XMLNode nameNode = XMLNodeGen.generateSampleNameExpr();
        NameExpr nameExpr = new NameExpr(nameNode);

        assertEquals(nameExpr.exprType, ASTExpr.ExprType.Name);
        assertNotNull(nameExpr.getCtx());
        assertEquals(nameExpr.getCtx().getOp(), ASTEnumOp.ASTOperator.Ctx_Load);
    }


    @Tag(TestKind.PUBLIC)
    @Test
    void testAttributeExpr() {
        XMLNode attributeNode = XMLNodeGen.generateSampleAttributeExpr(true);
        AttributeExpr attributeExpr = new AttributeExpr(attributeNode);

        assertEquals(attributeExpr.exprType, ASTExpr.ExprType.Attribute);
        assertNotNull(attributeExpr.getValue());
        assertEquals(attributeExpr.getAttr(), "next");
        assertNotNull(attributeExpr.getCtx());
        assertEquals(attributeExpr.getCtx().getOp(), ASTEnumOp.ASTOperator.Ctx_Load);
    }


    @Tag(TestKind.PUBLIC)
    @Test
    void testNameExprGetChildren() {
        XMLNode node = XMLNodeGen.generateSampleNameExpr();
        ASTExpr expr = ASTExpr.createASTExpr(node);

        assertNotNull(expr);
        assertNotNull(expr.getChildren());
        assertEquals(0, expr.getChildren().size());
    }

    @Tag(TestKind.PUBLIC)
    @Test
    void testAttributeExprGetChildren() {
        XMLNode node = XMLNodeGen.generateSampleAttributeExpr(true);
        ASTExpr expr = ASTExpr.createASTExpr(node);

        assertNotNull(expr);
        assertNotNull(expr.getChildren());
        assertEquals(1, expr.getChildren().size());
    }

    @Tag(TestKind.PUBLIC)
    @Test
    void testBoolOpExprGetChildren() {
        XMLNode node = XMLNodeGen.generateSampleBoolOpExpr();
        ASTExpr expr = ASTExpr.createASTExpr(node);

        assertNotNull(expr);
        assertNotNull(expr.getChildren());
        assertEquals(2, expr.getChildren().size());
    }
}
package hk.ust.comp3021.expr;

import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;

public abstract class ASTExpr extends ASTElement {
    public enum ExprType {
        BoolOp, BinOp, UnaryOp, Compare, Call, Constant, Attribute,
        Subscript, Name, List, Tuple
    }

    protected ExprType exprType;

    public ASTExpr(XMLNode node) {
        super(node);
    }

    @Override
    public String getNodeType() {
        return this.exprType.name();
    }

    public ExprType getExprType() {
        return exprType;
    }


    /**
     * Create ASTExpr from the XNL Node based on the tag name
     *
     * @param node: the XML Node from which to generate ASTExpr
     * @return: created ASTExpr
     *
     * You may need to remove the `return null` from the skeleton.
     */
    public static ASTExpr createASTExpr(XMLNode node) {
        switch (node.getTagName()) {
        case "BoolOp":
            return new BoolOpExpr(node);
        case "BinOp":
            return new BinOpExpr(node);
        case "UnaryOp":
            return new UnaryOpExpr(node);
        case "Compare":
            return new CompareExpr(node);
        case "Call":
            return new CallExpr(node);
        case "Constant":
            return new ConstantExpr(node);
        case "Attribute":
            return new AttributeExpr(node);
        case "Subscript":
            return new SubscriptExpr(node);
        case "Name":
            return new NameExpr(node);
        case "List":
            return new ListExpr(node);
        case "Tuple":
            return new TupleExpr(node);
        default:
        }
        return null;
    }

}

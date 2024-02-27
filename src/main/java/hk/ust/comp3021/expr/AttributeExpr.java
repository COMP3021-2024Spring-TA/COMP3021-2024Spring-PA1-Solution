package hk.ust.comp3021.expr;

import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.XMLNode;
import java.util.*;

public class AttributeExpr extends ASTExpr {
    // Attribute(expr value, identifier attr, expr_context ctx)
    private ASTExpr value;
    private String attr;
    private ASTEnumOp ctx;

    public AttributeExpr(XMLNode node) {
        super(node);
        this.exprType = ASTExpr.ExprType.Attribute;
        this.value = ASTExpr.createASTExpr(node.getChildByIdx(0));
        this.attr = node.getAttribute("attr");
        this.ctx = new ASTEnumOp(node.getChildByIdx(1));
    }

    public ASTEnumOp getCtx() {
        return ctx;
    }

    public void setCtx(ASTEnumOp ctx) {
        this.ctx = ctx;
    }

    public ASTExpr getValue() {
        return value;
    }

    public void setValue(ASTExpr value) {
        this.value = value;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public String getAttr() {
        return attr;
    }

    @Override
    public int countChildren() {
        int numChild = 1;
        numChild += value.countChildren();
        return numChild;
    }

    @Override
    public ArrayList<ASTElement> getChildren() {
        ArrayList<ASTElement> children = new ArrayList<>();
        children.add(value);
        return children;
    }

    @Override
    public void printByPos(StringBuilder str) {
        this.fillStartBlanks(str);
        value.printByPos(str);
        str.append(".");
        str.append(attr);
        this.fillEndBlanks(str);
    }

}

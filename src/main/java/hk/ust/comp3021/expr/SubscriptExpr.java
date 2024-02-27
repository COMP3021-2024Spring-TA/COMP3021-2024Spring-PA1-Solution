package hk.ust.comp3021.expr;

import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;
import java.util.*;

public class SubscriptExpr extends ASTExpr {
    // Subscript(expr value, expr slice, expr_context ctx)
    private ASTExpr value;
    private ASTExpr slice;
    private ASTEnumOp ctx;

    public SubscriptExpr(XMLNode node) {
        super(node);
        this.exprType = ASTExpr.ExprType.Subscript;
        this.value = ASTExpr.createASTExpr(node.getChildByIdx(0));
        this.slice = ASTExpr.createASTExpr(node.getChildByIdx(1));
        this.ctx = new ASTEnumOp(node.getChildByIdx(2));
    }

    @Override
    public ArrayList<ASTElement> getChildren() {
        ArrayList<ASTElement> children = new ArrayList<>();
        children.add(value);
        children.add(slice);
        return children;
    }

    @Override
    public int countChildren() {
        int numChild = 1;
        numChild += value.countChildren();
        numChild += slice.countChildren();
        return numChild;
    }

    @Override
    public void printByPos(StringBuilder str) {
        this.fillStartBlanks(str);
        value.printByPos(str);
        str.append("[");
        slice.printByPos(str);
        str.append("]");
        this.fillEndBlanks(str);
    }
}

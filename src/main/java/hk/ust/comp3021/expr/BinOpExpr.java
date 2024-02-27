package hk.ust.comp3021.expr;

import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;
import java.util.*;

public class BinOpExpr extends ASTExpr {
    // BinOp(expr left, operator op, expr right)
    private ASTExpr left;
    private ASTEnumOp op;
    private ASTExpr right;

    public BinOpExpr(XMLNode node) {
        super(node);
        this.exprType = ASTExpr.ExprType.BinOp;
        this.left = ASTExpr.createASTExpr(node.getChildByIdx(0));
        this.op = new ASTEnumOp(node.getChildByIdx(1));
        this.right = ASTExpr.createASTExpr(node.getChildByIdx(2));
    }

    public ASTEnumOp getOp() {
        return op;
    }

    @Override
    public ArrayList<ASTElement> getChildren() {
        ArrayList<ASTElement> children = new ArrayList<>();
        children.add(left);
        children.add(right);
        return children;
    }

    @Override
    public int countChildren() {
        int numChild = 1;
        numChild += left.countChildren();
        numChild += right.countChildren();
        return numChild;
    }

    @Override
    public void printByPos(StringBuilder str) {
        this.fillStartBlanks(str);
        this.left.printByPos(str);
        this.op.printByPos(str);
        this.right.printByPos(str);
        this.fillEndBlanks(str);
    }

}

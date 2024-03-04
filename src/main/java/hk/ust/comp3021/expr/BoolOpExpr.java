package hk.ust.comp3021.expr;

import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;
import java.util.*;

public class BoolOpExpr extends ASTExpr {
    // BoolOp(boolop op, expr* values)
    private ASTEnumOp op;
    private ArrayList<ASTExpr> values = new ArrayList<>();

    public BoolOpExpr(XMLNode node) {
        super(node);
        this.exprType = ASTExpr.ExprType.BoolOp;
        this.op = new ASTEnumOp(node.getChildByIdx(0));
        for (XMLNode valueNode : node.getChildByIdx(1).getChildren()) {
            this.values.add(ASTExpr.createASTExpr(valueNode));
        }
    }

    public ASTEnumOp getOp() {
        return op;
    }

    @Override
    public ArrayList<ASTElement> getChildren() {
        ArrayList<ASTElement> children = new ArrayList<>();
        children.addAll(values);
        return children;
    }
    @Override
    public int countChildren() {
        int numChild = 1;
        for (ASTElement value : values) {
            numChild += value.countChildren();
        }
        return numChild;
    }

    @Override
    public void printByPos(StringBuilder str) {
        this.fillStartBlanks(str);
        boolean write_op = false;
        for (ASTExpr value : values) {
            if (write_op) {
                str.append(" ");
                this.op.printByPos(str);
            } else {
                write_op = true;
            }
            value.printByPos(str);
        }
        this.fillEndBlanks(str);
    }
}

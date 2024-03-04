package hk.ust.comp3021.expr;

import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;
import java.util.*;

public class UnaryOpExpr extends ASTExpr {
    // UnaryOp(unaryop op, expr operand)
    private ASTEnumOp op;
    private ASTExpr operand;

    public UnaryOpExpr(XMLNode node) {
        super(node);
        this.exprType = ASTExpr.ExprType.UnaryOp;
        this.op = new ASTEnumOp(node.getChildByIdx(0));
        this.operand = ASTExpr.createASTExpr(node.getChildByIdx(1));
    }

    public ASTEnumOp getOp() {
        return op;
    }

    @Override
    public ArrayList<ASTElement> getChildren() {
        ArrayList<ASTElement> children = new ArrayList<>();
        children.add(operand);
        return children;
    }

    @Override
    public int countChildren() {
        int numChild = 1;
        numChild += operand.countChildren();
        return numChild;
    }

    @Override
    public void printByPos(StringBuilder str) {
        this.fillStartBlanks(str);
        boolean write_parens = false;
        if (this.operand instanceof BinOpExpr) {
            ASTEnumOp nextOp = ((BinOpExpr) this.operand).getOp();
            if (op.getPrecedence() > nextOp.getPrecedence()) {
                write_parens = true;
            }
        } else if (this.operand instanceof UnaryOpExpr) {
            ASTEnumOp nextOp = ((UnaryOpExpr) this.operand).op;
            if (op.getPrecedence() > nextOp.getPrecedence()) {
                write_parens = true;
            }
        } 
        
        op.printByPos(str);
        if (write_parens) {
            str.append("(");
        }
        operand.printByPos(str);
        if (write_parens) {
            str.append(")");
        }
        this.fillEndBlanks(str);
    }
}

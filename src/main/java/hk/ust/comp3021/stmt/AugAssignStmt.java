package hk.ust.comp3021.stmt;

import hk.ust.comp3021.expr.*;
import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;
import java.util.*;

public class AugAssignStmt extends ASTStmt {
    // AugAssign(expr target, operator op, expr value)
    private ASTExpr target;
    private ASTEnumOp op;
    private ASTExpr value;

    public AugAssignStmt(XMLNode node) {
        super(node);
        this.stmtType = ASTStmt.StmtType.AugAssign;
        this.target = ASTExpr.createASTExpr(node.getChildByIdx(0));
        this.op = new ASTEnumOp(node.getChildByIdx(1));
        this.value = ASTExpr.createASTExpr(node.getChildByIdx(2));
    }

    public ASTEnumOp getOp() {
        return op;
    }

    @Override
    public ArrayList<ASTElement> getChildren() {
        ArrayList<ASTElement> children = new ArrayList<>();
        children.add(target);
        children.add(value);
        return children;
    }

    @Override
    public int countChildren() {
        int numChild = 1;
        numChild += target.countChildren();
        numChild += value.countChildren();
        return numChild;
    }

    @Override
    public void printByPos(StringBuilder str) {
        this.fillStartBlanks(str);
        target.printByPos(str);
        str.append(" ");
        op.printByPos(str);
        str.append("=");
        value.printByPos(str);
        this.fillEndBlanks(str);
    }
}

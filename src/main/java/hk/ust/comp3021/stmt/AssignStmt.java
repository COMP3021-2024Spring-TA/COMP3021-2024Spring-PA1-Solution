package hk.ust.comp3021.stmt;

import hk.ust.comp3021.expr.*;
import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;
import java.util.*;


public class AssignStmt extends ASTStmt {
    // Assign(expr* targets, expr value, string? type_comment)
    private ArrayList<ASTExpr> targets = new ArrayList<>();
    private ASTExpr value;

    public AssignStmt(XMLNode node) {
        super(node);
        this.stmtType = ASTStmt.StmtType.Assign;
        for (XMLNode targetNode : node.getChildByIdx(0).getChildren()) {
            this.targets.add(ASTExpr.createASTExpr(targetNode));
        }
        this.value = ASTExpr.createASTExpr(node.getChildByIdx(1));
    }

    @Override
    public ArrayList<ASTElement> getChildren() {
        ArrayList<ASTElement> children = new ArrayList<>();
        children.addAll(targets);
        children.add(value);
        return children;
    }

    @Override
    public int countChildren() {
        int numChild = 1;
        for (ASTExpr target: targets) {
            numChild += target.countChildren();
        }

        numChild += value.countChildren();
        return numChild;
    }
    @Override
    public void printByPos(StringBuilder str) {
        this.fillStartBlanks(str);
        for (ASTExpr target: targets) {
            target.printByPos(str);
            str.append(" = ");
        }
        value.printByPos(str);
        this.fillEndBlanks(str);
    }
}

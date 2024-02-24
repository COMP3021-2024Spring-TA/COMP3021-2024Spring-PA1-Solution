package hk.ust.comp3021.stmt;

import hk.ust.comp3021.expr.*;
import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;
import java.util.ArrayList;


public class IfStmt extends ASTStmt {
    // If(expr test, stmt* body, stmt* orelse)
    private ASTExpr test;
    private ArrayList<ASTStmt> body = new ArrayList<ASTStmt>();
    private ArrayList<ASTStmt> orelse = new ArrayList<ASTStmt>();

    public IfStmt(XMLNode node) {
        super(node);
        this.stmtType = ASTStmt.StmtType.If;
        this.test = ASTExpr.createASTExpr(node.getChildByIdx(0));

        for (XMLNode bodyNode: node.getChildByIdx(1).getChildren()) {
            body.add(ASTStmt.createASTStmt(bodyNode));
        }
        for (XMLNode orelseNode: node.getChildByIdx(2).getChildren()) {
            orelse.add(ASTStmt.createASTStmt(orelseNode));
        }
    }

    @Override
    public ArrayList<ASTElement> getChildren() {
        ArrayList<ASTElement> children = new ArrayList<>();
        children.add(test);
        children.addAll(body);
        children.addAll(orelse);
        return children;
    }

    @Override
    public int countChildren() {
        int numChild = 1;
        numChild += test.countChildren();
        for (ASTStmt bodyStmt: body) {
            numChild += bodyStmt.countChildren();
        }
        for (ASTStmt orelseStmt: orelse) {
            numChild += orelseStmt.countChildren();
        }
        return numChild;
    }

    @Override
    public void printByPos(StringBuilder str) {

    }

}

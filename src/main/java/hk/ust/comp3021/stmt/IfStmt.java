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
        fillStartBlanks(str);
        int curIndent = countNowColOffset(str);
        str.append("if ");
        test.printByPos(str);
        str.append(":");
        for (ASTStmt bodyStmt: body) {
            bodyStmt.printByPos(str);
        }
        
        IfStmt curOrElse = this;
        while (curOrElse.orelse != null 
                && curOrElse.orelse.size() == 1 
                && curOrElse.orelse.get(0) instanceof IfStmt) {
            curOrElse = (IfStmt) curOrElse.orelse.get(0);
            str.append("\n");
            str.append(" ".repeat(curIndent));
            str.append("elif");
            curOrElse.test.printByPos(str);
            str.append(":");
            for (ASTStmt bodyStmt: curOrElse.body) {
                bodyStmt.printByPos(str);
            }
        }
        if (curOrElse.orelse != null && !curOrElse.orelse.isEmpty()) {
            str.append("\n");
            str.append(" ".repeat(curIndent));
            str.append("else:");
            for (ASTStmt orElseBody: curOrElse.orelse) {
                orElseBody.printByPos(str);
            }
        }
        fillEndBlanks(str);
    }
}

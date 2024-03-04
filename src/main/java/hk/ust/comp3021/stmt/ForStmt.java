package hk.ust.comp3021.stmt;

import hk.ust.comp3021.expr.*;
import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;
import java.util.ArrayList;


public class ForStmt extends ASTStmt {
    //  For(expr target, expr iter, stmt* body, stmt* orelse, ...)
    private ASTExpr target;
    private ASTExpr iter;
    private ArrayList<ASTStmt> body = new ArrayList<>();
    private ArrayList<ASTStmt> orelse = new ArrayList<>();
    public ForStmt(XMLNode node) {
        super(node);
        this.stmtType = ASTStmt.StmtType.For;
        this.target = ASTExpr.createASTExpr(node.getChildByIdx(0));
        this.iter = ASTExpr.createASTExpr(node.getChildByIdx(1));

        for (XMLNode bodyNode : node.getChildByIdx(2).getChildren()) {
            body.add(ASTStmt.createASTStmt(bodyNode));
        }

        for (XMLNode orelseNode : node.getChildByIdx(3).getChildren()) {
            orelse.add(ASTStmt.createASTStmt(orelseNode));
        }

    }

    @Override
    public ArrayList<ASTElement> getChildren() {
        ArrayList<ASTElement> children = new ArrayList<>();
        children.add(target);
        children.add(iter);
        children.addAll(body);
        children.addAll(orelse);
        return children;
    }

    @Override
    public int countChildren() {
        int numChild = 1;
        numChild += target.countChildren();
        numChild += iter.countChildren();

        for (ASTStmt bodyStmt : body) {
            numChild += bodyStmt.countChildren();
        }
        for (ASTStmt elseStmt : orelse) {
            numChild += elseStmt.countChildren();
        }
        return numChild;
    }


    @Override
    public void printByPos(StringBuilder str) {
        fillStartBlanks(str);
        str.append("for");
        target.printByPos(str);
        str.append(" in");
        iter.printByPos(str);
        str.append(":");
        for (ASTStmt bodyStmt : body) {
            bodyStmt.printByPos(str);
        }
        if (!orelse.isEmpty()) {
            str.append("else:");
        }
        for (ASTStmt elseStmt : orelse) {
            elseStmt.printByPos(str);
        }
        fillEndBlanks(str);
    }
}

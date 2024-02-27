package hk.ust.comp3021.stmt;

import hk.ust.comp3021.expr.*;
import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;
import java.util.*;

public class ClassDefStmt extends ASTStmt {
    /*
     * ClassDef(identifier name,
     *         expr* bases,
     *         keyword* keywords,
     *         stmt* body,
     *         expr* decorator_list,...)
     */
    private String name;
    private ArrayList<ASTExpr> bases = new ArrayList<>();
    private ArrayList<ASTKeyWord> keywords = new ArrayList<>();
    private ArrayList<ASTStmt> body = new ArrayList<>();
    private ArrayList<ASTExpr> decoratorList = new ArrayList<>();

    public ClassDefStmt(XMLNode node) {
        super(node);

        this.stmtType = ASTStmt.StmtType.ClassDef;
        this.name = node.getAttribute("name");

        for (XMLNode baseNode : node.getChildByIdx(0).getChildren()) {
            bases.add(ASTExpr.createASTExpr(baseNode));
        }

        for (XMLNode keywordNode : node.getChildByIdx(1).getChildren()) {
            keywords.add(new ASTKeyWord(keywordNode));
        }

        for (XMLNode bodyNode : node.getChildByIdx(2).getChildren()) {
            body.add(ASTStmt.createASTStmt(bodyNode));
        }

        for (XMLNode listNode : node.getChildByIdx(3).getChildren()) {
            decoratorList.add(ASTExpr.createASTExpr(listNode));
        }
    }

    @Override
    public ArrayList<ASTElement> getChildren() {
        ArrayList<ASTElement> children = new ArrayList<>();
        children.addAll(bases);
        children.addAll(keywords);
        children.addAll(body);
        children.addAll(decoratorList);

        return children;
    }

    @Override
    public int countChildren() {
        int numChild = 1;
        for (ASTExpr base : bases) {
            numChild += base.countChildren();
        }
        for (ASTKeyWord keyword : keywords) {
            numChild += keyword.countChildren();
        }
        for (ASTStmt bodyStmt : body) {
            numChild += bodyStmt.countChildren();
        }
        for (ASTExpr list : decoratorList) {
            numChild += list.countChildren();
        }
        return numChild;
    }

    @Override
    public void printByPos(StringBuilder str) {
        this.fillStartBlanks(str);
        for (ASTExpr list : decoratorList) {
            str.append('@');
            list.printByPos(str);
        }
        str.append("class ").append(this.name);

        if (!bases.isEmpty() || !keywords.isEmpty()) {
            str.append("(");
            boolean comma = false;

            for (ASTExpr base : bases) {
                if (comma) {
                    str.append(",");
                } else {
                    comma = true;
                }
                base.printByPos(str);
            }
            for (ASTKeyWord keyword : keywords) {
                if (comma) {
                    str.append(",");
                } else {
                    comma = true;
                }
                keyword.printByPos(str);
            }

        }
        str.append(":");
        for (ASTStmt bodyStmt : body) {
            bodyStmt.printByPos(str);
        }
        this.fillEndBlanks(str);
    }

}

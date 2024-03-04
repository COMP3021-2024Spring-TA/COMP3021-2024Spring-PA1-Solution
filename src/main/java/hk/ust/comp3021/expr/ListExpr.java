package hk.ust.comp3021.expr;

import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;
import java.util.*;

public class ListExpr extends ASTExpr {
    // List(expr* elts, expr_context ctx)
    private ArrayList<ASTExpr> elts = new ArrayList<>();
    private ASTEnumOp ctx;
    public ListExpr(XMLNode node) {
        super(node);
        this.exprType = ASTExpr.ExprType.List;

        for (XMLNode eltNode: node.getChildByIdx(0).getChildren()) {
            this.elts.add(ASTExpr.createASTExpr(eltNode));
        }

        this.ctx = new ASTEnumOp(node.getChildByIdx(1));
    }

    @Override
    public ArrayList<ASTElement> getChildren() {
        ArrayList<ASTElement> children = new ArrayList<>();
        children.addAll(elts);
        return children;
    }

    @Override
    public int countChildren() {
        int numChild = 1;
        for (ASTExpr elt: elts) {
            numChild += elt.countChildren();
        }
        return numChild;
    }

    @Override
    public void printByPos(StringBuilder str) {
        fillStartBlanks(str);
        str.append("[");
        boolean write_comma = false;
        for (ASTExpr elt: elts) {
            if (write_comma) {
                str.append(", ");
            } else {
                write_comma = true;
            }
            elt.printByPos(str);
        }
        str.append("]");
        fillEndBlanks(str);
    }

}

package hk.ust.comp3021.expr;

import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;
import java.util.*;

public class NameExpr extends ASTExpr {
    // Name(identifier id, expr_context ctx)
    private String id;
    private ASTEnumOp ctx;

    public NameExpr(XMLNode node)  {
        super(node);
        this.exprType = ASTExpr.ExprType.Name;
        this.id = node.getAttribute("id");
        this.ctx = new ASTEnumOp(node.getChildByIdx(0));
    }

    public String getId() {
        return id;
    }

    public ASTEnumOp getCtx() {
        return ctx;
    }

    @Override
    public ArrayList<ASTElement> getChildren() {
        ArrayList<ASTElement> children = new ArrayList<>();
        return children;
    }

    @Override
    public int countChildren() {
        int numChild = 1;
        return numChild;
    }
    @Override
    public void printByPos(StringBuilder str) {
        fillStartBlanks(str);
        str.append(this.id);
        fillEndBlanks(str);
    }

}

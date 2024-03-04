package hk.ust.comp3021.misc;

import hk.ust.comp3021.expr.*;
import hk.ust.comp3021.utils.*;
import java.util.*;

public class ASTKeyWord extends ASTElement {
    /*
     * keyword = (identifier? arg, expr value)
     * attributes (int lineno, int colOffset, int? endLineno, int? endColOffset)
     */
    private String arg;
    private ASTExpr value;

    public ASTKeyWord(XMLNode node) {
        super(node);
        if (node.hasAttribute("arg")) {
            this.arg = node.getAttribute("arg");
        }
        this.value = ASTExpr.createASTExpr(node.getChildByIdx(0));
    }

    @Override
    public ArrayList<ASTElement> getChildren() {
        ArrayList<ASTElement> children = new ArrayList<>();
        children.add(value);
        return children;
    }

    @Override
    public int countChildren() {
        int numChild = 1;
        numChild += value.countChildren();
        return numChild;
    }

    @Override
    public String getNodeType() {
        return "keyword";
    }

    @Override
    public void printByPos(StringBuilder str) {
        fillStartBlanks(str);
        if (arg != null) {
            str.append(arg);
            str.append("=");
        } else {
            str.append("**");
        }
        value.printByPos(str);
        fillEndBlanks(str);
    }
}

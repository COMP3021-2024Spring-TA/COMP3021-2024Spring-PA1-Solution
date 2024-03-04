package hk.ust.comp3021.misc;

import hk.ust.comp3021.expr.*;
import hk.ust.comp3021.utils.*;
import java.util.*;

public class ASTArguments extends ASTElement {
    public class ASTArg extends ASTElement {
        /*
         * arg = (identifier arg, expr? annotation, ...)
         *       attributes (int lineno, int colOffset, int? endLineno, int? endColOffset)
         */
        private String arg;
        private ASTExpr annotation;

        public ASTArg(XMLNode node) {
            super(node);
            this.arg = node.getAttribute("arg");
            if (!node.hasAttribute("annotation")) {
                this.annotation = ASTExpr.createASTExpr(node.getChildByIdx(0));
            }
        }

        @Override
        public ArrayList<ASTElement> getChildren() {
            ArrayList<ASTElement> children = new ArrayList<>();
            if (annotation != null) {
                children.add(annotation);
            }
            return children;
        }

        @Override
        public int countChildren() {
            int numChild = 1;
            if (annotation != null) {
                numChild += annotation.countChildren();
            }
            return numChild;
        }

        @Override
        public void printByPos(StringBuilder str) {
            this.fillStartBlanks(str);
            str.append(this.arg);

            if (annotation != null) {
                str.append(":");
                annotation.printByPos(str);
            }
            this.fillEndBlanks(str);
        }

        @Override
        public String getNodeType() {
            return "arg";
        }
    }
    /*
     * arguments = (.., arg* args, ..., expr* defaults)
     */

    private ArrayList<ASTArg> args = new ArrayList<>();
    private ArrayList<ASTExpr> defaults = new ArrayList<>();

    public ASTArguments(XMLNode node) {
        super(node);
        for (XMLNode argNode : node.getChildByIdx(1).getChildren()) {
            this.args.add(new ASTArg(argNode));
        }
        int nextIdx = 2;
        if (!node.hasAttribute("vararg")) {
            nextIdx += 1;
        }
        nextIdx += 2;
        if (!node.hasAttribute("kwarg")) {

            nextIdx += 1;
        }
        for (XMLNode defNode : node.getChildByIdx(nextIdx).getChildren()) {
            this.defaults.add(ASTExpr.createASTExpr(defNode));
        }

    }


    @Override
    public void printByPos(StringBuilder str) {
        List<ASTElement> children = this.getChildren();
        ASTElement.elementSort(children);

        for (int i = 0; i < children.size(); i++) {
            if (i >= 1 && children.get(i - 1) instanceof ASTArg) {
                str.append(",");
            }
            if (children.get(i) instanceof ASTExpr) {
                str.append("=");
            }
            children.get(i).printByPos(str);
        }
    }

    /*
    * Return the number of ASTArg child nodes
    */
    public int getParamNum() {
        int paramNum = 0;
        paramNum += this.args.size();
        return paramNum;
    }

    @Override
    public ArrayList<ASTElement> getChildren() {
        ArrayList<ASTElement> children = new ArrayList<>();
        children.addAll(args);
        children.addAll(defaults);

        return children;
    }

    @Override
    public int countChildren() {
        int numChild = 1;
        for (ASTArg arg: args) {
            numChild += arg.countChildren();
        }
        for (ASTExpr def: defaults) {
            numChild += def.countChildren();
        }
        return numChild;
    }

    @Override
    public String getNodeType() {
        return "arguments";
    }

}

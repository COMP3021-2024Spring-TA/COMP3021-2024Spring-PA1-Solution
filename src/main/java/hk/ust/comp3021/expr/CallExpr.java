package hk.ust.comp3021.expr;

import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;
import java.util.*;

public class CallExpr extends ASTExpr {
    // Call(expr func, expr* args, keyword* keywords)
    private ASTExpr func;
    private ArrayList<ASTExpr> args = new ArrayList<>();
    private ArrayList<ASTKeyWord> keywords = new ArrayList<>();

    public CallExpr(XMLNode node) {
        super(node);
        this.exprType = ASTExpr.ExprType.Call;
        this.func = ASTExpr.createASTExpr(node.getChildByIdx(0));
        for (XMLNode argNode : node.getChildByIdx(1).getChildren()) {
            this.args.add(ASTExpr.createASTExpr(argNode));
        }
        for (XMLNode keywordNode : node.getChildByIdx(2).getChildren()) {
            this.keywords.add(new ASTKeyWord(keywordNode));
        }
    }

    /*
     * Find all paths from func node to node with class type Name, which contain several cases
     * (1) if the path is func -> Attribute (attr: b) -> Name (id: self), then the name is self.b
     * (2) if the path is func -> Attribute (attr: b) -> Attribute (attr: a) -> Name (id: self), then the name is self.a.b
     * (3) if the path is func -> Name (id: bubbleSort), then the name is bubbleSort
     * @return: name of called function
     */
    public String getCalledFuncName() {
        ArrayList<ASTElement> path = null;
        Stack<ArrayList<ASTElement>> stack = new Stack<>();

        ArrayList<ASTElement> firstPath = new ArrayList<>();
        firstPath.add(this.func);

        stack.push(firstPath);

        while (!stack.isEmpty()) {
            ArrayList<ASTElement> curPath = stack.pop();
            ASTElement curNode = curPath.get(curPath.size() - 1);
            if (curNode instanceof NameExpr) {
                path = new ArrayList<>(curPath);
                break;
            } else if (curNode instanceof ConstantExpr) {
                path = new ArrayList<>(curPath);
                break;
            } else if (curNode instanceof CallExpr) {
                path = new ArrayList<>(curPath);
                break;
            } else {
                for (ASTElement childNode : curNode.getChildren()) {
                    ArrayList<ASTElement> newPath = new ArrayList<>(curPath);
                    newPath.add(childNode);
                    stack.push(newPath);
                }
            }
        }
        if (path == null) {
            throw new RuntimeException("There is no path to obtain called func name!");
        }
        int i = path.size() - 1;
        String funcName = "";
        boolean meetName = false;
        for (; i >= 0; i--) {
            if (path.get(i) instanceof NameExpr) {
                NameExpr nameNode = (NameExpr) path.get(i);
                funcName += nameNode.getId();
                meetName = true;
            } else if (path.get(i) instanceof AttributeExpr) {
                AttributeExpr attrNode = (AttributeExpr) path.get(i);
                if (meetName) {
                    funcName += "." + attrNode.getAttr();
                } else {
                    funcName += attrNode.getAttr();
                }
            }
        }
        return funcName;
    }

    @Override
    public ArrayList<ASTElement> getChildren() {
        ArrayList<ASTElement> children = new ArrayList<>();
        children.add(func);
        children.addAll(args);
        children.addAll(keywords);
        return children;
    }

    @Override
    public int countChildren() {
        int numChild = 1;
        numChild += func.countChildren();
        for (ASTExpr arg: args) {
            numChild += arg.countChildren();
        }
        for (ASTKeyWord keyword: keywords) {
            numChild += keyword.countChildren();
        }
        return numChild;
    }

    @Override
    public void printByPos(StringBuilder str) {
        fillStartBlanks(str);
        func.printByPos(str);
        str.append("(");
        boolean write_comma = false;
        for (ASTExpr arg: args) {
            if (write_comma) {
                str.append(",");
            } else {
                write_comma = true;
            }
            arg.printByPos(str);
        }
        for (ASTElement keyword: keywords) {
            if (write_comma) {
                str.append(",");
            } else {
                write_comma = true;
            }
            keyword.printByPos(str);
        }
        str.append(")");
        fillEndBlanks(str);
    }

}

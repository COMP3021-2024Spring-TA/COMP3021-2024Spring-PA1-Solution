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

    /*
     * Find all paths from node Call to node Name
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
        for (; i >= 0; i--) {
            if (path.get(i) instanceof NameExpr) {
                NameExpr nameNode = (NameExpr) path.get(i);
                funcName += nameNode.getId();
            } else if (path.get(i) instanceof AttributeExpr) {
                AttributeExpr attrNode = (AttributeExpr) path.get(i);
                funcName += "." + attrNode.getAttr();
            }
        }
        return funcName;
    }

    @Override
    public void printByPos(StringBuilder str) {
    }

}

package hk.ust.comp3021.utils;

import hk.ust.comp3021.expr.BinOpExpr;
import hk.ust.comp3021.expr.BoolOpExpr;
import hk.ust.comp3021.expr.CompareExpr;
import hk.ust.comp3021.expr.UnaryOpExpr;
import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.stmt.*;
import java.util.*;


public class ASTModule extends ASTElement {
    // Module(stmt* body, ...)
    private ArrayList<ASTStmt> body = new ArrayList<ASTStmt>();
    private String astFile;
    private String astID;

    public ASTModule(XMLNode node, String astFile, String astID) {
        this.astFile = astFile;

        this.astID = astID;

        for (XMLNode bodyNode : node.getChildByIdx(0).getChildren()) {
            this.body.add(ASTStmt.createASTStmt(bodyNode));
        }
    }

    public String getAstFile() {
        return astFile;
    }

    public ArrayList<ASTStmt> getBody() {
        return body;
    }

    public void setAstFile(String astFile) {
        this.astFile = astFile;
    }

    public void setAstID(String astID) {
        this.astID = astID;
    }

    public void setBody(ArrayList<ASTStmt> body) {
        this.body = body;
    }

    public ArrayList<FunctionDefStmt> getAllFunctions() {
        ArrayList<FunctionDefStmt> functionDefs = new ArrayList<FunctionDefStmt>();
        ArrayList<ASTElement> processedChild = new ArrayList<ASTElement>();

        processedChild.add(this);

        while (!processedChild.isEmpty()) {
            ASTElement curChild = processedChild.get(0);
            processedChild.remove(0);
            if (curChild instanceof FunctionDefStmt) {
                functionDefs.add((FunctionDefStmt) curChild);
            }
            for (ASTElement child : curChild.getChildren()) {
                processedChild.add(child);
            }
        }

        return functionDefs;
    }

    public ArrayList<ASTEnumOp> getAllOperators() {
        ArrayList<ASTEnumOp> operators = new ArrayList<ASTEnumOp>();
        ArrayList<ASTElement> processedChild = new ArrayList<ASTElement>();

        processedChild.add(this);

        while (!processedChild.isEmpty()) {
            ASTElement curChild = processedChild.get(0);
            if (curChild instanceof BinOpExpr) {
                operators.add(((BinOpExpr) curChild).getOp());
            } else if (curChild instanceof BoolOpExpr) {
                operators.add(((BoolOpExpr) curChild).getOp());
            } else if (curChild instanceof CompareExpr) {
                operators.addAll(((CompareExpr) curChild).getOps());
            } else if (curChild instanceof UnaryOpExpr) {
                operators.add(((UnaryOpExpr) curChild).getOp());
            } else if (curChild instanceof AugAssignStmt) {
                operators.add(((AugAssignStmt) curChild).getOp());
            }
            for (ASTElement child : curChild.getChildren()) {
                processedChild.add(child);
            }
            processedChild.remove(0);
        }

        return operators;
    }

    public ArrayList<ASTElement> getAllNodes() {
        ArrayList<ASTElement> allNodes = new ArrayList<>();
        ArrayList<ASTElement> processedChild = new ArrayList<ASTElement>();

        processedChild.add(this);
        while (!processedChild.isEmpty()) {
            ASTElement curChild = processedChild.get(0);
            allNodes.add(curChild);

            for (ASTElement child : curChild.getChildren()) {
                processedChild.add(child);
                if (child == null) {
                    curChild.countChildren();
                }
            }
            processedChild.remove(0);
        }
        return allNodes;
    }


    @Override
    public ArrayList<ASTElement> getChildren() {
        ArrayList<ASTElement> children = new ArrayList<>();
        children.addAll(body);
        return children;
    }

    @Override
    public int countChildren() {
        int numChild = 1;
        for (ASTStmt bodyStmt: body) {
            numChild += bodyStmt.countChildren();
        }
        return numChild;
    }

    @Override
    public void printByPos(StringBuilder str) {
        ArrayList<ASTElement> sortedBody = this.getChildren();
        ASTElement.elementSort(sortedBody);

        for (ASTElement child: sortedBody) {
            child.printByPos(str);
        }
    }

    public String getASTID() {
        return astID;
    }

    @Override
    public String getNodeType() {
        return "Module";
    }
}

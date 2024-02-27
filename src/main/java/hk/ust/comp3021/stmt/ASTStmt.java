package hk.ust.comp3021.stmt;

import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;

public abstract class ASTStmt extends ASTElement {
    enum StmtType {
        FunctionDef, ClassDef, Return, Assign, AugAssign, For, While, If,
        Expr, Break, Continue
    }

    protected StmtType stmtType;

    public ASTStmt(int lineno, int colOffset, int endLineno, int endColOffset, StmtType stmtType) {
        super(lineno, colOffset, endLineno, endColOffset);
        this.stmtType = stmtType;
    }

    public ASTStmt(XMLNode node) {
        super(node);
    }

    @Override
    public String getNodeType() {
        return this.stmtType.name();
    }

    /**
     * Create ASTStmt from the XNL Node based on the tag name
     *
     * @param node: the XML Node from which to generate ASTStmt
     * @return: created ASTStmt
     *
     * You may need to remove the `return null` from the skeleton.
     */
    public static ASTStmt createASTStmt(XMLNode node) {
        switch (node.getTagName()) {
        case "Assign":
            return new AssignStmt(node);
        case "AugAssign":
            return new AugAssignStmt(node);
        case "FunctionDef":
            return new FunctionDefStmt(node);
        case "ClassDef":
            return new ClassDefStmt(node);
        case "Return":
            return new ReturnStmt(node);
        case "For":
            return new ForStmt(node);
        case "While":
            return new WhileStmt(node);
        case "If":
            return new IfStmt(node);
        case "Expr":
            return new ExprStmt(node);
        case "Break":
            return new BreakStmt(node);
        case "Continue":
            return new ContinueStmt(node);
        default:
            return null;
        }
    }
}

package hk.ust.comp3021.stmt;

import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;

public abstract class ASTStmt extends ASTElement {
	enum StmtType {
		FunctionDef, ClassDef, Return, Assign, AugAssign, For, While, If,
		Expr, Break, Continue
	}

	protected StmtType stmtType;

	public ASTStmt(int lineno, int col_offset, int end_lineno, int end_col_offset, StmtType stmtType) {
		super(lineno, col_offset, end_lineno, end_col_offset);
		this.stmtType = stmtType;
	}

	public ASTStmt(XMLNode node) {
		super(node);

	}

	@Override
	public String getNodeType() {
		return this.stmtType.name();
	}

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

	/**
	 * Attention: You may need to define more methods to update or access the field
	 * of the class ASTStmt, i.e., getters or setters Feel free to define more
	 * method but remember not (1) removing the fields or methods in our skeleton.
	 * (2) changing the type signature of `public` methods (3) changing the
	 * modifiers of the fields and methods, e.g., changing a modifier from "private"
	 * to "public"
	 */

	public void yourMethod() {

	}

}

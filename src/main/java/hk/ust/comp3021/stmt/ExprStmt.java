package hk.ust.comp3021.stmt;

import java.util.*;

import hk.ust.comp3021.expr.*;
import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;

public class ExprStmt extends ASTStmt {

	// Expr(expr value)

	private ASTExpr value;

	public ExprStmt(XMLNode node) {
		super(node);
		this.stmtType = ASTStmt.StmtType.Expr;
		value = ASTExpr.createASTExpr(node.getChildByIdx(0));
	}
}

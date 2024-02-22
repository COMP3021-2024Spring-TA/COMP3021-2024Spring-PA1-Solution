package hk.ust.comp3021.stmt;

import java.util.*;

import hk.ust.comp3021.expr.ASTExpr;
import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;

public class RaiseStmt extends ASTStmt {

	// Raise(expr? exc, expr? cause)

	private ASTExpr exc;

	private ASTExpr cause;

	public RaiseStmt(XMLNode node) {
		super(node);
		this.stmtType = ASTStmt.StmtType.Raise;
		
		if (!node.hasAttribute("exc")) {
			this.exc = ASTExpr.createASTExpr(node.getChildByIdx(0));
		}

		if (!node.hasAttribute("cause")) {
			if (!node.hasAttribute("exc")) {
				this.cause = ASTExpr.createASTExpr(node.getChildByIdx(1));
			} else {
				this.cause = ASTExpr.createASTExpr(node.getChildByIdx(0));
			}
		}
	}


}

package hk.ust.comp3021.expr;

import java.util.*;

import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;

public class IfExpExpr extends ASTExpr {
	
	// IfExp(expr test, expr body, expr orelse)

	private ASTExpr test;

	private ASTExpr body;

	private ASTExpr orelse;

	public IfExpExpr(XMLNode node) {
		super(node);

		this.exprType = ASTExpr.ExprType.IfExp;
		
		this.test = ASTExpr.createASTExpr(node.getChildByIdx(0));

		this.body = ASTExpr.createASTExpr(node.getChildByIdx(1));

		this.orelse = ASTExpr.createASTExpr(node.getChildByIdx(2));
	}

}

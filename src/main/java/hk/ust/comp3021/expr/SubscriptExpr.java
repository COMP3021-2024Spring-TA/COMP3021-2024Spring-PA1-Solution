package hk.ust.comp3021.expr;

import java.util.*;

import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;

public class SubscriptExpr extends ASTExpr {

	// Subscript(expr value, expr slice, expr_context ctx)
	
	private ASTExpr value;

	private ASTExpr slice;

	private ASTEnumOp.ExprContext ctx;

	public SubscriptExpr(XMLNode node) {
		super(node);
		
		this.exprType = ASTExpr.ExprType.Subscript;
		
		this.value = ASTExpr.createASTExpr(node.getChildByIdx(0));
		this.slice = ASTExpr.createASTExpr(node.getChildByIdx(1));

		this.ctx = ASTEnumOp.parseExprContext(node.getChildByIdx(2));

	}



}

package hk.ust.comp3021.expr;

import java.util.*;

import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.XMLNode;

public class AttributeExpr extends ASTExpr {

	// Attribute(expr value, identifier attr, expr_context ctx)

	private ASTExpr value;

	private String attr;

	private ASTEnumOp.ExprContext ctx;

	public AttributeExpr(XMLNode node) {
		super(node);
		this.exprType = ASTExpr.ExprType.Attribute;

		this.value = ASTExpr.createASTExpr(node.getChildByIdx(0));
		this.attr = node.getAttribute("attr");
		this.ctx = ASTEnumOp.parseExprContext(node.getChildByIdx(1));
	}

}

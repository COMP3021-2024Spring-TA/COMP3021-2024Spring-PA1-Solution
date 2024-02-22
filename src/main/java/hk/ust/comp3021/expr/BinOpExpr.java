package hk.ust.comp3021.expr;

import java.util.ArrayList;

import hk.ust.comp3021.misc.ASTElement;
import hk.ust.comp3021.misc.ASTEnumOp;
import hk.ust.comp3021.utils.XMLNode;

public class BinOpExpr extends ASTExpr {

	// BinOp(expr left, operator op, expr right)

	private ASTExpr left;

	private ASTEnumOp.ASTOperator op;

	private ASTExpr right;

	public BinOpExpr(XMLNode node) {
		super(node);
		this.exprType = ASTExpr.ExprType.BinOp;

		this.left = ASTExpr.createASTExpr(node.getChildByIdx(0));

		this.op = ASTEnumOp.parseASTOperator(node.getChildByIdx(1));

		this.right = ASTExpr.createASTExpr(node.getChildByIdx(2));

	}

}

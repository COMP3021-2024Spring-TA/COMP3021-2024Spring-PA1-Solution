package hk.ust.comp3021.expr;

import java.util.*;

import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;

public class UnaryOpExpr extends ASTExpr {

	// UnaryOp(unaryop op, expr operand)

	private ASTEnumOp.ASTUnaryOp op;

	private ASTExpr operand;

	public UnaryOpExpr(XMLNode node) {
		super(node);
		
		this.exprType = ASTExpr.ExprType.UnaryOp;

		this.op = ASTEnumOp.parseASTUnaryOp(node.getChildByIdx(0));

		this.operand = ASTExpr.createASTExpr(node.getChildByIdx(1));

	}


}

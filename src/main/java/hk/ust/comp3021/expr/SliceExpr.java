package hk.ust.comp3021.expr;

import java.util.*;

import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;

public class SliceExpr extends ASTExpr {

	// Slice(expr? lower, expr? upper, expr? step)

	private ASTExpr lower;

	private ASTExpr upper;

	private ASTExpr step;

	public SliceExpr(XMLNode node) {
		super(node);
		this.exprType = ASTExpr.ExprType.Slice;
		
		int nextIdx = 0;

		if (!node.hasAttribute("lower")) {
			this.lower = ASTExpr.createASTExpr(node.getChildByIdx(nextIdx));
			nextIdx += 1;
		}
		if (!node.hasAttribute("upper")) {
			this.upper = ASTExpr.createASTExpr(node.getChildByIdx(nextIdx));
			nextIdx += 1;
		}
		if (!node.hasAttribute("step")) {
			this.step = ASTExpr.createASTExpr(node.getChildByIdx(nextIdx));
			nextIdx += 1;
		}
	}


}

package hk.ust.comp3021.expr;

import java.util.ArrayList;

import hk.ust.comp3021.misc.ASTElement;
import hk.ust.comp3021.misc.ASTEnumOp;
import hk.ust.comp3021.utils.XMLNode;

public class BoolOpExpr extends ASTExpr {

	// BoolOp(boolop op, expr* values)

	private ASTEnumOp.ASTBoolOp op;

	private ArrayList<ASTExpr> values = new ArrayList<>();

	public BoolOpExpr(XMLNode node) {
		super(node);

		this.exprType = ASTExpr.ExprType.BoolOp;

		this.op = ASTEnumOp.parseASTBoolOp(node.getChildByIdx(0));

		for (XMLNode valueNode : node.getChildByIdx(1).getChildren()) {
			this.values.add(ASTExpr.createASTExpr(valueNode));
		}

	}

}

package hk.ust.comp3021.expr;

import java.util.ArrayList;

import hk.ust.comp3021.misc.ASTElement;
import hk.ust.comp3021.misc.ASTEnumOp;
import hk.ust.comp3021.misc.ASTKeyWord;
import hk.ust.comp3021.utils.XMLNode;

public class CompareExpr extends ASTExpr {

	// Compare(expr left, cmpop* ops, expr* comparators)
	private ASTExpr left;

	private ArrayList<ASTEnumOp.ASTCmpOp> ops = new ArrayList<>();

	private ArrayList<ASTExpr> comparators = new ArrayList<>();

	public CompareExpr(XMLNode node) {
		super(node);

		this.exprType = ASTExpr.ExprType.Compare;

		this.left = ASTExpr.createASTExpr(node.getChildByIdx(0));

		for (XMLNode opNode : node.getChildByIdx(1).getChildren()) {
			this.ops.add(ASTEnumOp.parseASTCmpOp(opNode));
		}

		for (XMLNode comparatorNode : node.getChildByIdx(2).getChildren()) {
			this.comparators.add(ASTExpr.createASTExpr((comparatorNode)));
		}

	}

}

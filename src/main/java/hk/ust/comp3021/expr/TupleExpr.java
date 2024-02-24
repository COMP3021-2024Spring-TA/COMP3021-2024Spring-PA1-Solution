package hk.ust.comp3021.expr;

import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;
import java.util.*;

public class TupleExpr extends ASTExpr {

	//  Tuple(expr* elts, expr_context ctx)
	private ArrayList<ASTExpr> elts = new ArrayList<>();
	private ASTEnumOp ctx;
	public TupleExpr(XMLNode node) {
		super(node);
		this.exprType = ASTExpr.ExprType.Tuple;
		for (XMLNode eltNode : node.getChildByIdx(0).getChildren()) {
			this.elts.add(ASTExpr.createASTExpr(eltNode));
		}
		this.ctx = new ASTEnumOp(node.getChildByIdx(1));
	}

	@Override
	public void printByPos(StringBuilder str) {

	}
	@Override
	public ArrayList<ASTElement> getChildren() {
		ArrayList<ASTElement> children = new ArrayList<>();
		children.addAll(elts);
		return children;
	}

	@Override
	public int countChildren() {
		int numChild = 1;
		for (ASTExpr elt: elts) {
			numChild += elt.countChildren();
		}
		return numChild;
	}
}


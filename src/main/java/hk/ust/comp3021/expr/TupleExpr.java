package hk.ust.comp3021.expr;

import java.util.*;

import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;

public class TupleExpr extends ASTExpr {
	
	//  Tuple(expr* elts, expr_context ctx)

	private ArrayList<ASTExpr> elts = new ArrayList<>();

	private ASTEnumOp.ExprContext ctx;

	public TupleExpr(XMLNode node) {
		super(node);
		
		this.exprType = ASTExpr.ExprType.Tuple;
		
		for (XMLNode eltNode : node.getChildByIdx(0).getChildren()) {
			this.elts.add(ASTExpr.createASTExpr(eltNode));
		}

		this.ctx = ASTEnumOp.parseExprContext(node.getChildByIdx(1));
	}


}

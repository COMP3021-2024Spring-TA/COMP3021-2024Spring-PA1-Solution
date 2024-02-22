package hk.ust.comp3021.expr;

import java.util.ArrayList;

import hk.ust.comp3021.misc.ASTElement;
import hk.ust.comp3021.misc.ASTEnumOp;
import hk.ust.comp3021.utils.XMLNode;

public class ListExpr extends ASTExpr {

	// List(expr* elts, expr_context ctx)
	
	private ArrayList<ASTExpr> elts = new ArrayList<>();
	
	private ASTEnumOp.ExprContext ctx;
	
	public ListExpr(XMLNode node) {
		super(node);
		this.exprType = ASTExpr.ExprType.List;
		
		for (XMLNode eltNode: node.getChildByIdx(0).getChildren()) {
			this.elts.add(ASTExpr.createASTExpr(eltNode));
		}
		
		this.ctx = ASTEnumOp.parseExprContext(node.getChildByIdx(1));
	}
	
	

}

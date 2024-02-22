package hk.ust.comp3021.expr;

import java.util.*;

import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;


public class SetExpr extends ASTExpr {

	// Set(expr* elts)
	
	private ArrayList<ASTExpr> elts = new ArrayList<>();

	public SetExpr(XMLNode node) {
		super(node);
		
		this.exprType = ASTExpr.ExprType.Set;
		
		for (XMLNode eltNode : node.getChildByIdx(0).getChildren()) {
			this.elts.add(ASTExpr.createASTExpr(eltNode));
		}
	}


}

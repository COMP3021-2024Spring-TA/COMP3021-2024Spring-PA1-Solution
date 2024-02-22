package hk.ust.comp3021.expr;

import java.util.ArrayList;

import hk.ust.comp3021.misc.ASTElement;
import hk.ust.comp3021.utils.XMLNode;

public class DictExpr extends ASTExpr {
	
	// Dict(expr* keys, expr* values)
	
	private ArrayList<ASTExpr> keys = new ArrayList<>();
	
	private ArrayList<ASTExpr> values = new ArrayList<>();
	
	public DictExpr(XMLNode node) {
		super(node);

		this.exprType = ASTExpr.ExprType.Dict;
		
		for (XMLNode keyNode : node.getChildByIdx(0).getChildren()) {
			this.keys.add(ASTExpr.createASTExpr(keyNode));
		}
		
		for (XMLNode valueNode : node.getChildByIdx(1).getChildren()) {
			this.values.add(ASTExpr.createASTExpr(valueNode));
		}
	}
	

}

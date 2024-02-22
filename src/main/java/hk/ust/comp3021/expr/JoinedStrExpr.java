package hk.ust.comp3021.expr;

import java.util.ArrayList;

import hk.ust.comp3021.misc.ASTElement;
import hk.ust.comp3021.utils.XMLNode;

public class JoinedStrExpr extends ASTExpr {

	// JoinedStr(expr* values)
	
	private ArrayList<ASTExpr> values = new ArrayList<>();

	public JoinedStrExpr(XMLNode node) {
		super(node);
		
		this.exprType = ASTExpr.ExprType.JoinedStr;
		
		for (XMLNode valueNode : node.getChildByIdx(0).getChildren()) {
			this.values.add(ASTExpr.createASTExpr(valueNode));
		}
	}
	
}

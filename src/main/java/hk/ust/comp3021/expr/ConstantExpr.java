package hk.ust.comp3021.expr;

import java.util.ArrayList;

import hk.ust.comp3021.misc.ASTElement;
import hk.ust.comp3021.utils.XMLNode;

public class ConstantExpr extends ASTExpr {

	// Constant(constant value, string? kind)

	private String value = null;

	private String kind;

	public ConstantExpr(XMLNode node) {
		super(node);

		this.exprType = ASTExpr.ExprType.Constant;
		
		this.value = node.getAttribute("value");

		if (node.hasAttribute("kind")) {
			this.kind = node.getAttribute("kind");
		}
	}

}

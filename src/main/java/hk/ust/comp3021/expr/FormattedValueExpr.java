package hk.ust.comp3021.expr;

import java.util.*;

import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;

public class FormattedValueExpr extends ASTExpr {
	
	// FormattedValue(expr value, int conversion, expr? format_spec)

	private ASTExpr value;

	private int conversion;

	private ASTExpr formatSpec;

	public FormattedValueExpr(XMLNode node) {
		super(node);
		
		this.exprType = ASTExpr.ExprType.FormattedValue;

		this.value = ASTExpr.createASTExpr(node.getChildByIdx(0));

		this.conversion = Integer.parseInt(node.getAttribute("conversion"));
		if (!node.hasAttribute("format_spec")) {
			this.formatSpec = ASTExpr.createASTExpr(node.getChildByIdx(1));
		}
	}


}

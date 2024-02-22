package hk.ust.comp3021.misc;

import java.util.*;

import hk.ust.comp3021.expr.ASTExpr;
import hk.ust.comp3021.utils.*;

public class ASTKeyWord extends ASTElement {

	/*
	 * keyword = (identifier? arg, expr value) 
	 * attributes (int lineno, int col_offset, int? end_lineno, int? end_col_offset)
	 */
	
	private String arg;

	private ASTExpr value;

	public ASTKeyWord(XMLNode node) {
		super(node);

		if (node.hasAttribute("arg")) {
			this.arg = node.getAttribute("arg");
		}

		this.value = ASTExpr.createASTExpr(node.getChildByIdx(0));

	}


}

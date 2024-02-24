package hk.ust.comp3021.misc;

import java.util.*;


import hk.ust.comp3021.expr.*;
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

	@Override
	public ArrayList<ASTElement> getChildren() {
		ArrayList<ASTElement> children = new ArrayList<>();
		children.add(value);
		return children;
	}

	@Override
	public int countChildren() {
		int numChild = 1;
		numChild += value.countChildren();
		return numChild;
	}

	@Override
	public String getNodeType() {
		return "keyword";
	}

	@Override
	public void printByPos(StringBuilder str) {

	}
}

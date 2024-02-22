package hk.ust.comp3021.misc;

import java.util.*;

import hk.ust.comp3021.utils.*;

public abstract class ASTElement {
	private int lineno = -1;
	private int col_offset = -1;
	private int end_lineno = -1;
	private int end_col_offset = -1;

	public ASTElement(int lineno, int col_offset, int end_lineno, int end_col_offset) {
		this.lineno = lineno;
		this.col_offset = col_offset;
		this.end_lineno = end_lineno;
		this.end_col_offset = end_col_offset;
	}

	public ASTElement(XMLNode node) {
		if (node.hasAttribute("lineno")) {
			this.lineno = Integer.parseInt(node.getAttribute("lineno"));
		}
		if (node.hasAttribute("col_offset")) {
			this.col_offset = Integer.parseInt(node.getAttribute("col_offset"));
		}
		if (node.hasAttribute("end_lineno")) {
			this.end_lineno = Integer.parseInt(node.getAttribute("end_lineno"));
		}
		if (node.hasAttribute("end_col_offset")) {
			this.end_col_offset = Integer.parseInt("end_col_offset");
		}
	}

}

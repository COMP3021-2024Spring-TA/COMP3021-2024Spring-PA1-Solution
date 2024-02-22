package hk.ust.comp3021.misc;

import java.util.*;
import hk.ust.comp3021.utils.*;

public class ASTAlias extends ASTElement {

	/*
	 * alias = (identifier name, identifier? asname)
             attributes (int lineno, int col_offset, int? end_lineno, int? end_col_offset)
	 */
	
	private String name;
	
	private String asname;
	
	
	public ASTAlias(XMLNode node) {
		super(node);
		
		this.name = node.getAttribute("name");
		
		if (node.hasAttribute("asname")) {
			this.asname = node.getAttribute("asname");
		}
	}


}

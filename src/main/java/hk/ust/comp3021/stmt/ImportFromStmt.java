package hk.ust.comp3021.stmt;

import java.util.ArrayList;
import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;

public class ImportFromStmt extends ASTStmt {

	// ImportFrom(identifier? module, alias* names, int? level)
	
	private String module;

	private ArrayList<ASTAlias> names = new ArrayList<ASTAlias>();

	private int level;

	public ImportFromStmt(XMLNode node) {
		super(node);
		
		this.stmtType = ASTStmt.StmtType.ImportFrom;
		
		if (node.hasAttribute("module")) {
			this.module = node.getAttribute("module");
		}
		
		for (XMLNode nameNode : node.getChildByIdx(0).getChildren()) {
			this.names.add(new ASTAlias(nameNode));
		}
		if (node.hasAttribute("level")) {
			this.level = Integer.parseInt(node.getAttribute("level"));
		}
	}



}

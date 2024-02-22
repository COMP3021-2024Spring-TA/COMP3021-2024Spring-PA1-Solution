package hk.ust.comp3021.stmt;

import java.util.ArrayList;

import hk.ust.comp3021.misc.ASTAlias;
import hk.ust.comp3021.misc.ASTElement;
import hk.ust.comp3021.utils.XMLNode;

public class ImportStmt extends ASTStmt {

	// Import(alias* names)
	
	private ArrayList<ASTAlias> names = new ArrayList<ASTAlias>();;

	public ImportStmt(XMLNode node) {
		super(node);
		
		this.stmtType = ASTStmt.StmtType.Import;
		
		for (XMLNode nameNode : node.getChildByIdx(0).getChildren()) {
			this.names.add(new ASTAlias(nameNode));
		}
	}


}

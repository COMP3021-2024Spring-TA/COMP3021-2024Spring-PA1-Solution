package hk.ust.comp3021.stmt;

import java.util.*;

import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;

public class BreakStmt extends ASTStmt {

	public BreakStmt(XMLNode node) {
		super(node);
		this.stmtType = ASTStmt.StmtType.Break;
	}
	
}

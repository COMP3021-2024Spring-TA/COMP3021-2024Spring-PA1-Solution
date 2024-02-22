package hk.ust.comp3021.stmt;

import java.util.*;

import hk.ust.comp3021.expr.*;
import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;

public class DeleteStmt extends ASTStmt {

	// Delete(expr* targets)

	private ArrayList<ASTExpr> targets = new ArrayList<>();

	public DeleteStmt(XMLNode node) {
		super(node);
		this.stmtType = ASTStmt.StmtType.Delete;
		for (XMLNode targetNode : node.getChildByIdx(0).getChildren()) {
			targets.add(ASTExpr.createASTExpr(targetNode));
		}

	}


}

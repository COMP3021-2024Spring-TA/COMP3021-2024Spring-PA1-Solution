package hk.ust.comp3021.stmt;

import java.util.*;

import hk.ust.comp3021.expr.*;
import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;

import hk.ust.comp3021.utils.*;
import hk.ust.comp3021.expr.*;
import hk.ust.comp3021.misc.*;

public class AssignStmt extends ASTStmt {
	
	// Assign(expr* targets, expr value, string? type_comment)
	
	private ArrayList<ASTExpr> targets = new ArrayList<>();
	
	private ASTExpr value;
	
	private String typeComment;

	public AssignStmt(XMLNode node) {
		super(node);
		this.stmtType = ASTStmt.StmtType.Assign;
		
		for (XMLNode targetNode : node.getChildByIdx(0).getChildren()) {
			this.targets.add(ASTExpr.createASTExpr(targetNode));
		}

		this.value = ASTExpr.createASTExpr(node.getChildByIdx(1));

		if (node.hasAttribute("type_comment")) {
			this.typeComment = node.getAttribute("type_comment");
		}
	}

}

package hk.ust.comp3021.stmt;

import java.util.*;
import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;
import hk.ust.comp3021.expr.*;

public class AnnAssignStmt extends ASTStmt {

	//  AnnAssign(expr target, expr annotation, expr? value, int simple)
	
	private ASTExpr target;
	
	private ASTExpr annotation;
	
	private ASTExpr value;
	
	private int simple;
	

	public AnnAssignStmt(XMLNode node) {
		super(node);
		this.stmtType = ASTStmt.StmtType.AnnAssign;
		
		this.target = ASTExpr.createASTExpr(node.getChildByIdx(0));
		this.annotation = ASTExpr.createASTExpr(node.getChildByIdx(1));
		if (!node.hasAttribute("value") && node.getNumChildren() == 3) {
			this.value = ASTExpr.createASTExpr(node.getChildByIdx(2));
		}
		
		this.simple = Integer.parseInt(node.getAttribute("simple"));
	}


}

package hk.ust.comp3021.stmt;

import java.util.ArrayList;

import hk.ust.comp3021.expr.*;
import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;

public class ForStmt extends ASTStmt {

	//  For(expr target, expr iter, stmt* body, stmt* orelse, string? type_comment)
	
	private ASTExpr target;
	
	private ASTExpr iter;
	
	private ArrayList<ASTStmt> body = new ArrayList<>();
	
	private ArrayList<ASTStmt> orelse = new ArrayList<>();
	
	private String typeComment;
	
	
	public ForStmt(XMLNode node) {
		super(node);
		this.stmtType = ASTStmt.StmtType.For;
		
		this.target = ASTExpr.createASTExpr(node.getChildByIdx(0));
		
		this.iter = ASTExpr.createASTExpr(node.getChildByIdx(1));
		
		for (XMLNode bodyNode: node.getChildByIdx(2).getChildren()) {
			body.add(ASTStmt.createASTStmt(bodyNode));
		}
		
		for (XMLNode orelseNode: node.getChildByIdx(3).getChildren()) {
			orelse.add(ASTStmt.createASTStmt(orelseNode));
		}
		
		if (node.hasAttribute("type_comment")) {
			this.typeComment = node.getAttribute("type_comment");
		}
	}
	

}

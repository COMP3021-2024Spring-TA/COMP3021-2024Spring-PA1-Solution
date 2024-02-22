package hk.ust.comp3021.stmt;

import java.util.ArrayList;


import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;
import hk.ust.comp3021.expr.*;

public class WithStmt extends ASTStmt {

	class ASTWithItem extends ASTElement {
		
		// withitem = (expr context_expr, expr? optional_vars)
		
		private ASTExpr ctxExpr;
		
		private ASTExpr optionalVars;
		
		public ASTWithItem(XMLNode node) {
			super(node);
			
			this.ctxExpr = ASTExpr.createASTExpr(node.getChildByIdx(0));
			
			if (!node.hasAttribute("optional_vars")) {
				this.optionalVars = ASTExpr.createASTExpr(node.getChildByIdx(1));
			}
			
		}

	}
	
	// With(withitem* items, stmt* body, string? type_comment)
	
	private ArrayList<ASTWithItem> items = new ArrayList<>();
	
	private ArrayList<ASTStmt> body = new ArrayList<>();
	
	private String typeComment;
	
	public WithStmt(int lineno, int col_offset, int end_lineno, int end_col_offset, StmtType stmtType) {
		super(lineno, col_offset, end_lineno, end_col_offset, stmtType);
		// TODO Auto-generated constructor stub
	}

	public WithStmt(XMLNode node) {
		super(node);
		
		this.stmtType = ASTStmt.StmtType.With;
		for (XMLNode itemNode : node.getChildByIdx(0).getChildren()) {
			items.add(new ASTWithItem(itemNode));
		}

		for (XMLNode bodyNode : node.getChildByIdx(1).getChildren()) {
			body.add(ASTStmt.createASTStmt(bodyNode));
		}
		
		typeComment = node.getAttribute("type_comment");
		
	}
}

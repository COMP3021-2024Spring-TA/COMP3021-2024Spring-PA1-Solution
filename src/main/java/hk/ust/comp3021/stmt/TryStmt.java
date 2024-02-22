package hk.ust.comp3021.stmt;

import java.util.*;

import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;
import hk.ust.comp3021.expr.*;

public class TryStmt extends ASTStmt {
	
	class ASTExceptHandler extends ASTElement {
		
		/*
		 * excepthandler = ExceptHandler(expr? type, identifier? name, stmt* body)
                           attributes (int lineno, int col_offset, int? end_lineno, int? end_col_offset)
		 */
		
		private ASTExpr type;
		
		private String name;

		private ArrayList<ASTStmt> body = new ArrayList<ASTStmt>();
		
		public ASTExceptHandler(XMLNode node) {
			super(node);
			
			int nextIdx = 0;
			
			if (!node.hasAttribute("type")) {
				this.type = ASTExpr.createASTExpr(node.getChildByIdx(nextIdx++));
			}
			
			if (node.hasAttribute("name")) {
				this.name = node.getAttribute("name");
			}
			
			for (XMLNode bodyNode: node.getChildByIdx(nextIdx).getChildren()) {
				body.add(ASTStmt.createASTStmt(bodyNode));
			}
			
		}
		

	}


	// Try(stmt* body, excepthandler* handlers, stmt* orelse, stmt* finalbody)
	
	private ArrayList<ASTStmt> body;
	
	private ArrayList<ASTExceptHandler> handlers;
	
	private ArrayList<ASTStmt> orelse;
	
	private ArrayList<ASTStmt> finalbody;

	
	public TryStmt(XMLNode node) {
		super(node);
		
		this.stmtType = ASTStmt.StmtType.Try;
		
		for (XMLNode bodyNode: node.getChildByIdx(0).getChildren()) {
			body.add(ASTStmt.createASTStmt(bodyNode));
		}
		
		for (XMLNode handlerNode: node.getChildByIdx(1).getChildren()) {
			handlers.add(new ASTExceptHandler(handlerNode));
		}
		
		for (XMLNode orelseNode: node.getChildByIdx(2).getChildren()) {
			orelse.add(ASTStmt.createASTStmt(orelseNode));
		}
		
		for (XMLNode finalNode: node.getChildByIdx(3).getChildren()) {
			finalbody.add(ASTStmt.createASTStmt(finalNode));
		}
	}


}

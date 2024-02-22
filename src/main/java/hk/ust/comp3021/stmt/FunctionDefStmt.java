package hk.ust.comp3021.stmt;

import java.util.*;

import hk.ust.comp3021.utils.*;
import hk.ust.comp3021.expr.*;
import hk.ust.comp3021.misc.*;

public class FunctionDefStmt extends ASTStmt {

	/*
	 * FunctionDef(identifier name, arguments args, stmt* body, expr*
	 * decorator_list, expr? returns, string? type_comment, type_param* type_params)
	 */

	private String name;

	private ASTArguments args;

	private ArrayList<ASTStmt> body = new ArrayList<>();

	private ArrayList<ASTExpr> decorator_list = new ArrayList<>();

	private ASTExpr returns;

	private String type_comment;

	public FunctionDefStmt(XMLNode node) {
		super(node);
		
		this.stmtType = ASTStmt.StmtType.FunctionDef;
		
		this.name = node.getAttribute("name");

		this.args = new ASTArguments(node.getChildByIdx(0));

		for (XMLNode bodyNode : node.getChildByIdx(1).getChildren()) {
			body.add(ASTStmt.createASTStmt(bodyNode));
		}

		for (XMLNode listNode : node.getChildByIdx(2).getChildren()) {
			decorator_list.add(ASTExpr.createASTExpr(listNode));
		}

		if (node.getNumChildren() >= 3) {
			this.returns = ASTExpr.createASTExpr(node.getChildByIdx(3));
		}
		
		if (node.hasAttribute("type_comment")) {
			this.type_comment = node.getAttribute("type_comment");
		}
	}

	public ArrayList<ASTElement> getChildrenNodes() {
		return null;
	}

	/**
	 * Attention: You may need to define more methods to update or access the field
	 * of the class `FunctionDefStmt` Feel free to define more method but remember
	 * not (1) removing the fields or methods in our skeleton. (2) changing the type
	 * signature of `public` methods (3) changing the modifiers of the fields and
	 * methods, e.g., changing a modifier from "private" to "public"
	 */
	public void yourMethod() {

	}
}

package hk.ust.comp3021.stmt;

import java.util.*;
import hk.ust.comp3021.utils.*;
import hk.ust.comp3021.expr.*;
import hk.ust.comp3021.misc.*;

public class ClassDefStmt extends ASTStmt {

	/*
	 * ClassDef(identifier name, 
	 * 			expr* bases, 
	 * 			keyword* keywords, 
	 * 			stmt* body, 
	 * 			expr* decorator_list, 
	 * 			type_param* type_params)
	 */

	private String name;

	private ArrayList<ASTExpr> bases = new ArrayList<>();

	private ArrayList<ASTKeyWord> keywords = new ArrayList<>();

	private ArrayList<ASTStmt> body = new ArrayList<>();

	private ArrayList<ASTExpr> decorator_list = new ArrayList<>();

	public ClassDefStmt(XMLNode node) {
		super(node);

		this.stmtType = ASTStmt.StmtType.ClassDef;
		this.name = node.getAttribute("name");

		for (XMLNode baseNode : node.getChildByIdx(0).getChildren()) {
			bases.add(ASTExpr.createASTExpr(baseNode));
		}

		for (XMLNode keywordNode : node.getChildByIdx(1).getChildren()) {
			keywords.add(new ASTKeyWord(keywordNode));
		}

		for (XMLNode bodyNode : node.getChildByIdx(2).getChildren()) {
			body.add(ASTStmt.createASTStmt(bodyNode));
		}

		for (XMLNode listNode : node.getChildByIdx(3).getChildren()) {
			decorator_list.add(ASTExpr.createASTExpr(listNode));
		}
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

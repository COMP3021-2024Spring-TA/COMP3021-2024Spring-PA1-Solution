package hk.ust.comp3021.expr;

import java.util.*;

import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;

public class LambdaExpr extends ASTExpr {
	
    // Lambda(arguments args, expr body)

	private ASTArguments args;

	private ASTExpr body;

	public LambdaExpr(XMLNode node) {
		super(node);

		this.exprType = ASTExpr.ExprType.Lambda;
		
		this.args = new ASTArguments(node.getChildByIdx(0));

		this.body = ASTExpr.createASTExpr(node.getChildByIdx(1));

	}

}

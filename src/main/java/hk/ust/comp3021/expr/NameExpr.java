package hk.ust.comp3021.expr;

import java.util.*;

import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;


public class NameExpr extends ASTExpr {

	// Name(identifier id, expr_context ctx)
	
	private String name;

	private ASTEnumOp.ExprContext ctx;

	public NameExpr(XMLNode node)  {
		super(node);
		this.exprType = ASTExpr.ExprType.Name;
		this.name = node.getAttribute("name");
		this.ctx = ASTEnumOp.parseExprContext(node.getChildByIdx(0));
	}


}

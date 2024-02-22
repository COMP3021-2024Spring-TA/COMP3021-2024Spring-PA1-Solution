package hk.ust.comp3021.stmt;

import java.util.*;
import hk.ust.comp3021.utils.*;
import hk.ust.comp3021.expr.*;
import hk.ust.comp3021.misc.*;

public class AugAssignStmt extends ASTStmt {
	
	// AugAssign(expr target, operator op, expr value)
	
	private ASTExpr target;
	
	private ASTEnumOp.ASTOperator op;
	
	private ASTExpr value;
	
	
	public AugAssignStmt(XMLNode node) {
		super(node);
		
		this.stmtType = ASTStmt.StmtType.AugAssign;
		
		this.target = ASTExpr.createASTExpr(node.getChildByIdx(0));
		
		this.op = ASTEnumOp.parseASTOperator(node.getChildByIdx(1));
		
		this.value = ASTExpr.createASTExpr(node.getChildByIdx(2));
	}
	
}

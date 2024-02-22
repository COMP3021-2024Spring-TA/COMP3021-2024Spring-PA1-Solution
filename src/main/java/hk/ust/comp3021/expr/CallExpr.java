package hk.ust.comp3021.expr;

import java.util.*;

import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;

public class CallExpr extends ASTExpr {

	// Call(expr func, expr* args, keyword* keywords)

	private ASTExpr func;

	private ArrayList<ASTExpr> args = new ArrayList<>();

	private ArrayList<ASTKeyWord> keywords = new ArrayList<>();

	public CallExpr(XMLNode node) {
		super(node);
		this.exprType = ASTExpr.ExprType.Call;

		this.func = ASTExpr.createASTExpr(node.getChildByIdx(0));

		for (XMLNode argNode : node.getChildByIdx(1).getChildren()) {
			this.args.add(ASTExpr.createASTExpr(argNode));
		}

		for (XMLNode keywordNode : node.getChildByIdx(2).getChildren()) {
			this.keywords.add(new ASTKeyWord(keywordNode));
		}

	}

}

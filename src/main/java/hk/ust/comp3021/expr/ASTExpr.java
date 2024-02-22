package hk.ust.comp3021.expr;

import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.stmt.*;
import hk.ust.comp3021.utils.*;

public abstract class ASTExpr extends ASTElement {
	enum ExprType {
		BoolOp, BinOp, UnaryOp, Lambda, IfExp, Dict, Set, Compare, Call, FormattedValue, JoinedStr, Constant, Attribute,
		Subscript, Name, List, Tuple, Slice
	}

	protected ExprType exprType;

	public ASTExpr(XMLNode node) {
		super(node);
	}

	public static ASTExpr createASTExpr(XMLNode node) {
		switch (node.getTagName()) {
		case "BoolOp":
			return new BoolOpExpr(node);
		case "BinOp":
			return new BinOpExpr(node);
		case "UnaryOp":
			return new UnaryOpExpr(node);
		case "Lambda":
			return new LambdaExpr(node);
		case "IfExp":
			return new IfExpExpr(node);
		case "Dict":
			return new DictExpr(node);
		case "Set":
			return new SetExpr(node);
		case "Compare":
			return new CompareExpr(node);
		case "Call":
			return new CallExpr(node);
		case "FormattedValue":
			return new FormattedValueExpr(node);
		case "JoinedStr":
			return new JoinedStrExpr(node);
		case "Constant":
			return new ConstantExpr(node);
		case "Attribute":
			return new AttributeExpr(node);
		case "Subscript":
			return new SubscriptExpr(node);
		case "Name":
			return new NameExpr(node);
		case "List":
			return new ListExpr(node);
		case "Tuple":
			return new TupleExpr(node);
		case "Slice":
			return new SliceExpr(node);
		default:
			return null;

		}

	}

}

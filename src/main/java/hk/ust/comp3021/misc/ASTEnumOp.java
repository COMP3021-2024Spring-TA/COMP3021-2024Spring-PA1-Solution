package hk.ust.comp3021.misc;

import hk.ust.comp3021.utils.*;

public class ASTEnumOp {

	/*
	 * expr_context = Load | Store | Del
	 * 
	 * boolop = And | Or
	 * 
	 * operator = Add | Sub | Mult | MatMult | Div | Mod | Pow | LShift | RShift |
	 * BitOr | BitXor | BitAnd | FloorDiv
	 * 
	 * unaryop = Invert | Not | UAdd | USub
	 * 
	 * cmpop = Eq | NotEq | Lt | LtE | Gt | GtE | Is | IsNot | In | NotIn
	 */

	public enum ExprContext {
		Ctx_Load, Ctx_Store, Ctx_Del,
	}

	public enum ASTBoolOp {
		OP_And, OP_Or,
	}

	public enum ASTOperator {
		OP_Add, OP_Sub, OP_Mult, OP_MatMult, OP_Div, OP_Mod, OP_Pow, OP_LShift, OP_RShift, OP_BitOr, OP_BitXor,
		OP_BitAnd, OP_FloorDiv
	}

	public enum ASTUnaryOp {
		OP_Invert, OP_Not, OP_UAdd, OP_USub,
	}

	public enum ASTCmpOp {
		OP_Eq, OP_NotEq, OP_Lt, OP_LtE, OP_Gt, OP_GtE, OP_Is, OP_IsNot, OP_In, OP_NotIn,
	}

	public static ExprContext parseExprContext(XMLNode node) {
		switch (node.getTagName()) {
		case "Load": {
			return ExprContext.Ctx_Load;
		}
		case "Store": {
			return ExprContext.Ctx_Store;
		}
		case "Del": {
			return ExprContext.Ctx_Del;
		}
		default:
			throw new IllegalArgumentException("Unsupported tag name: " + node.getTagName());
		}
	}

	public static ASTBoolOp parseASTBoolOp(XMLNode node) {
		switch (node.getTagName()) {
		case "And":
			return ASTBoolOp.OP_And;
		case "Or":
			return ASTBoolOp.OP_Or;
		default:
			throw new IllegalArgumentException("Unsupported tag name: " + node.getTagName());
		}
	}

	public static ASTOperator parseASTOperator(XMLNode node) {
		switch (node.getTagName()) {
		case "Add":
			return ASTOperator.OP_Add;
		case "Sub":
			return ASTOperator.OP_Sub;
		case "Mult":
			return ASTOperator.OP_Mult;
		case "MatMult":
			return ASTOperator.OP_MatMult;
		case "Div":
			return ASTOperator.OP_Div;
		case "Mod":
			return ASTOperator.OP_Mod;
		case "Pow":
			return ASTOperator.OP_Pow;
		case "LShift":
			return ASTOperator.OP_LShift;
		case "RShift":
			return ASTOperator.OP_RShift;
		case "BitOr":
			return ASTOperator.OP_BitOr;
		case "BitXor":
			return ASTOperator.OP_BitXor;
		case "BitAnd":
			return ASTOperator.OP_BitAnd;
		case "FloorDiv":
			return ASTOperator.OP_FloorDiv;
		default:
			throw new IllegalArgumentException("Unsupported tag name: " + node.getTagName());
		}
	}

	public static ASTUnaryOp parseASTUnaryOp(XMLNode node) {
		switch (node.getTagName()) {
		case "Invert":
			return ASTUnaryOp.OP_Invert;
		case "Not":
			return ASTUnaryOp.OP_Not;
		case "UAdd":
			return ASTUnaryOp.OP_UAdd;
		case "USub":
			return ASTUnaryOp.OP_USub;
		default:
			throw new IllegalArgumentException("Unsupported tag name: " + node.getTagName());
		}
	}

	public static ASTCmpOp parseASTCmpOp(XMLNode node) {
		switch (node.getTagName()) {
		case "Eq":
			return ASTCmpOp.OP_Eq;
		case "NotEq":
			return ASTCmpOp.OP_NotEq;
		case "Lt":
			return ASTCmpOp.OP_Lt;
		case "LtE":
			return ASTCmpOp.OP_LtE;
		case "Gt":
			return ASTCmpOp.OP_Gt;
		case "GtE":
			return ASTCmpOp.OP_GtE;
		case "Is":
			return ASTCmpOp.OP_Is;
		case "IsNot":
			return ASTCmpOp.OP_IsNot;
		case "In":
			return ASTCmpOp.OP_In;
		case "NotIn":
			return ASTCmpOp.OP_NotIn;
		default:
			throw new IllegalArgumentException("Unsupported tag name: " + node.getTagName());
		}
	}

}

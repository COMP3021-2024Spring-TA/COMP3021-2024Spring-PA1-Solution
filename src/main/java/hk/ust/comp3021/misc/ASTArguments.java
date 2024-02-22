package hk.ust.comp3021.misc;

import java.util.*;
import hk.ust.comp3021.expr.*;
import hk.ust.comp3021.utils.*;

public class ASTArguments extends ASTElement {
	
	public class ASTArg extends ASTElement {
		
		private String arg;
		
		private ASTExpr annotation;
		
		private String typeComment;
	
		/*
		 * arg = (identifier arg, expr? annotation, string? type_comment)
           attributes (int lineno, int col_offset, int? end_lineno, int? end_col_offset)
		 */
		
		public ASTArg(XMLNode node) {
			super(node);
			
			this.arg = node.getAttribute("arg");
			
			if (!node.hasAttribute("annotation")) {
				this.annotation = ASTExpr.createASTExpr(node.getChildByIdx(0));
			}
			
			if (node.hasAttribute("type_comment")) {
				this.typeComment = node.getAttribute("type_comment");
			}
			
		}

		
	}
	/*
	 * arguments = (arg* posonlyargs, arg* args, arg? vararg, arg* kwonlyargs,
                    expr* kw_defaults, arg? kwarg, expr* defaults)
	 */
	
	private ArrayList<ASTArg> posonlyargs = new ArrayList<>();
	
	private ArrayList<ASTArg> args = new ArrayList<>();
	
	private ASTArg vararg;
	
	private ArrayList<ASTArg> kwonlyargs = new ArrayList<>();
	
	private ArrayList<ASTExpr> kwDefaults = new ArrayList<>();
	
	private ASTArg kwarg;
	
	private ArrayList<ASTExpr> defaults = new ArrayList<>();
	
	public ASTArguments(XMLNode node) {
		
		super(node);
		
		for (XMLNode posNode: node.getChildByIdx(0).getChildren()) {
			this.posonlyargs.add(new ASTArg(posNode));
		}
		
		for (XMLNode argNode: node.getChildByIdx(1).getChildren()) {
			this.args.add(new ASTArg(argNode));
		}
		
		int nextIdx = 2;
		
		if (!node.hasAttribute("vararg")) {
			this.vararg = new ASTArg(node.getChildByIdx(nextIdx));
			nextIdx += 1;
		}
		
		for (XMLNode kwOnlyNode: node.getChildByIdx(nextIdx).getChildren()) {
			this.kwonlyargs.add(new ASTArg(kwOnlyNode));
		}
		
		nextIdx += 1;
		
		for (XMLNode kwDefNode: node.getChildByIdx(nextIdx).getChildren()) {
			this.kwDefaults.add(ASTExpr.createASTExpr(kwDefNode));
		}
		
		nextIdx += 1;
		
		if (!node.hasAttribute("kwarg")) {
			this.kwarg = new ASTArg(node.getChildByIdx(nextIdx));
			nextIdx += 1;
		}
		
		for (XMLNode defNode: node.getChildByIdx(nextIdx).getChildren()) {
			this.defaults.add(ASTExpr.createASTExpr(defNode));
		}
	
	}

	
}

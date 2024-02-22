package hk.ust.comp3021.utils;

import java.util.*;
import hk.ust.comp3021.stmt.*;

public class ASTModule {

	// Module(stmt* body, type_ignore* type_ignores)

	private ArrayList<ASTStmt> body = new ArrayList<ASTStmt>();

	private String astFile;

	public ASTModule(XMLNode node, String astFile) {
		this.astFile = astFile;

		for (XMLNode bodyNode : node.getChildByIdx(0).getChildren()) {
			this.body.add(ASTStmt.createASTStmt(bodyNode));
		}
	}

}

package hk.ust.comp3021.utils;

public class ASTUnParser {

	private final String xmlFilePath;

	private boolean isErr;

	private XMLNode rootXMLNode;

	private ASTModule rootASTModule;
	
	public ASTUnParser(String xmlFilePath) {
		this.xmlFilePath = xmlFilePath;
		this.isErr = false;
		this.rootXMLNode = null;
		this.rootASTModule = null;
	}
	
	
	public String getXMLFilePath() {
		return xmlFilePath;
	}
	
	
	public boolean isErr() {
		return isErr;
	}
	
	public ASTModule getASTModule() {
		return rootASTModule;
	}
	
	
	public void unparse() {
		
	}

	
	
}
package hk.ust.comp3021;

import java.util.*;
import java.io.*;
import java.nio.file.Paths;

import hk.ust.comp3021.utils.*;

public class ASTManagerEngine {

	private final String defaultXMLFileDir = "resources/xmldata/";
	private HashMap<String, ASTModule> id2ASTModules;

	public ASTManagerEngine() {
		id2ASTModules = new HashMap<>();
	}

	public void userInterface() {
		System.out.println("----------------------------------------------------------------------");
		System.out.println("ASTManager is running...");

		while (true) {
			System.out.println("----------------------------------------------------------------------");
			System.out.println("Please select the following operations with the corresponding numbers:");
			System.out.println("  0: Given AST ID, parse AST from XML files");
			System.out.println("  1: Print all functions with # arguments greater than user specificied N");
			System.out.println("  2: Find the most commonly used operators in all ASTs");
			System.out.println("  3: Given AST ID, count the number of all nodes type");
			System.out.println("  4: Given AST ID, traverse the ");
			System.out.println("  5: Sort all functions based on their lines of code");
			System.out.println("  6: Recover Python Code from ASTs (Bonus Task)");
			System.out.println("  7: Exit");
			System.out.println("----------------------------------------------------------------------");
			Scanner scan1 = new Scanner(System.in);
			if (scan1.hasNextInt()) {
				int i = scan1.nextInt();
				if (i < 0 || i > 7) {
					System.out.println("You should enter 0~7.");
					continue;
				}

				switch (i) {
				case 0: {
					userInterfaceParseXML();
				}
				case 1: {

				}
				case 2: {

				}
				case 3: {

				}
				case 4: {

				}
				case 5: {

				}
				default: {

				}
				}
				if (i == 7) {
					break;
				}
			} else {
				System.out.println("You should enter integer 0~6.");
			}
		}
	}

	public static int countXMLFiles(String dirPath) {
		int count = 0;
		File directory = new File(dirPath);
		if (directory.isDirectory()) {
			File[] files = directory.listFiles();
			if (files != null) {
				for (File file : files) {
					if (file.isDirectory()) {
						count += countXMLFiles(file.getAbsolutePath()); 
					} else if (file.isFile() && file.getName().toLowerCase().endsWith(".xml")) {
						count++;
					}
				}
			}
		}

		return count;
	}

	public void processXMLParsing(String xmlID) {
		String xmlFileName = Paths.get(defaultXMLFileDir, "python_" + xmlID + ".xml").toString();
		ASTParser parser = new ASTParser(xmlFileName);
		parser.parse();
		if (!parser.isErr()) {
			this.id2ASTModules.put(xmlID, parser.getASTModule());
			System.out.println("AST " + xmlID + " Succeed! The XML file is loaded!");
		} else {
			System.out.println("AST " + xmlID + " Failed! Please check your implementation!");
		}
	}

	public void userInterfaceParseXML() {
		int xmlCount = ASTManagerEngine.countXMLFiles(this.defaultXMLFileDir);
		System.out.println("Please specify the XML file ID to parse (0~" + xmlCount + ") or -1 for all:");
		Scanner scan1 = new Scanner(System.in);
		if (scan1.hasNextLine()) {
			String xmlID = scan1.nextLine();
			if (xmlID != "-1") {
				processXMLParsing(xmlID);
			} else {
				int count = 0;
				File directory = new File(this.defaultXMLFileDir);
				if (directory.isDirectory()) {
					File[] files = directory.listFiles();
					if (files != null) {
						for (File file : files) {
							if (file.isFile() && file.getName().toLowerCase().endsWith(".xml")) {
								processXMLParsing(Integer.toString(count));
								count += 1;
							}
						}
					}
				}
			}
		}
	}
}

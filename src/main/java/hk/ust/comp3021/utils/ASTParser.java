package hk.ust.comp3021.utils;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;


public class ASTParser {
    private final String xmlFileID;

    private boolean isErr;

    private XMLNode rootXMLNode;

    private ASTModule rootASTModule;

    public ASTParser(String xmlFileID) {
        this.xmlFileID = xmlFileID;
        this.isErr = false;
        this.rootXMLNode = null;
        this.rootASTModule = null;
    }

    public boolean isErr() {
        return isErr;
    }

    public ASTModule getASTModule() {
        return rootASTModule;
    }

    public XMLNode getRootXMLNode() {
        return rootXMLNode;
    }

    public void parse() {
        parse2XMLNode();
        rootXMLNode = rootXMLNode.getChildByIdx(0);
        rootASTModule = new ASTModule(rootXMLNode, xmlFileID);
    }

    public void parse2XMLNode() {
        try {
            String line;
            Stack<XMLNode> nodeStack = new Stack<>();

            String xmlFileName = Paths.get("resources/pythonxml/", "python_" + xmlFileID + ".xml").toString();

            BufferedReader reader = new BufferedReader(new FileReader(xmlFileName));

            while ((line = reader.readLine()) != null) {
                if (line.contains("<") && line.contains(">")) {
                    String tagName = extractTagName(line);
                    if (tagName.startsWith("/")) {
                        // Closing tag
                        String closingTag = tagName.substring(1);
                        if (!nodeStack.isEmpty() && nodeStack.peek().getTagName().equals(closingTag)) {
                            nodeStack.pop();
                        }
                    } else if (line.contains("/>")) {
                        // Self-closing tag
                        XMLNode newNode = new XMLNode(tagName);
                        if (!nodeStack.isEmpty()) {
                            // Find the appropriate parent node
                            XMLNode parentNode = nodeStack.peek();
                            parentNode.addChild(newNode);
                        } else {
                            // Root node
                            rootXMLNode = newNode;
                        }
                        parseAttributes(line, newNode);
                    } else {
                        // Opening tag
                        XMLNode newNode = new XMLNode(tagName);
                        if (!nodeStack.isEmpty()) {
                            // Add child to the parent node
                            XMLNode parentNode = nodeStack.peek();
                            parentNode.addChild(newNode);
                        } else {
                            rootXMLNode = newNode;
                        }
                        nodeStack.push(newNode);
                        parseAttributes(line, newNode);
                    }
                }
            }
//            printTree(rootXMLNode, 0);
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
            isErr = true;
        }
    }

    private static String extractTagName(String line) {
        int startIndex = line.indexOf("<") + 1;
        int endIndex = line.indexOf(" ", startIndex);
        if (endIndex == -1) {
            int endIndex1 = line.indexOf("/>", startIndex);
            if (endIndex1 == -1) {
                endIndex = line.indexOf(">", startIndex);
            } else {
                endIndex = line.indexOf("/>", startIndex);
            }
        }
        return line.substring(startIndex, endIndex);
    }

    private static void parseAttributes(String line, XMLNode node) {
        int startIndex = line.indexOf("<" + node.getTagName()) + node.getTagName().length() + 1;
        int endIndex = line.indexOf(">");
        if (line.contains("/>")) {
            endIndex = line.indexOf("/>");
        }
        String attributesString = line.substring(startIndex, endIndex);
        String[] attributePairs = attributesString.split(" ");
        for (String attributePair : attributePairs) {
            String[] attribute = attributePair.split("=");
            if (attribute.length == 2) {
                String attributeName = attribute[0].trim();
                String attributeValue = attribute[1].replaceAll("\"", "").trim();
                node.getAttributes().put(attributeName, attributeValue);
            }
        }
    }

    private static void printTree(XMLNode node, int level) {
        System.out.println(getIndentation(level) + node.getTagName());
        for (Map.Entry<String, String> attribute : node.getAttributes().entrySet()) {
            System.out.println(getIndentation(level + 1) + "@" + attribute.getKey() + ": " + attribute.getValue());
        }
        for (XMLNode child : node.getChildren()) {
            printTree(child, level + 1);
        }
    }

    private static String getIndentation(int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append("\t");
        }
        return sb.toString();
    }

}

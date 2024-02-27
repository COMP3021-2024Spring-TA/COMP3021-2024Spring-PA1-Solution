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
        // parse the XML Tree into rootXMLNode
        parse2XMLNode();
        // obtain the module node as the first child of ast node
        rootXMLNode = rootXMLNode.getChildByIdx(0);
        // create AST Tree and return the root node ASTModule
        rootASTModule = new ASTModule(rootXMLNode, xmlFileID);
    }

    /**
     * 1. Parse the XML Tree inside given XML File whose path is `xmlFilePath`
     * 2. Initialize the rootXMLNode as the root node of XML Tree, the tag Name of rootXMLNode should be ast
     * 3. If any exception throws, please set the field `isErr` to true. Otherwise, `isErr` is false.
     *
     * Hints:
     * For the following XML snippet:
     * <Assign type_comment="None" lineno="4" col_offset="8" end_lineno="4" end_col_offset="19">
     *     <targets>
     *         <Name id="tail" lineno="4" col_offset="8" end_lineno="4" end_col_offset="12">
     *             <Store/>
     *         </Name>
     *     </targets>
     *     <Constant value="None" kind="None" lineno="4" col_offset="15" end_lineno="4" end_col_offset="19"/>
     * </Assign>
     *
     * There are five XML nodes in total. Each XMLNode has two fields, i.e., attributes and children.
     * Attributes are key-value pairs. For instance, for xml node whose tag is Assign, the key-value pairs contains
     * `id: tail`. Children are a list of XMLNode, for instance, for Assign node, it has two children (targets, Constant).
     *
     * Noticed that in each line, there could be a self-closing tag, e.g., <Store/>, or a closing tag, e.g., </Name>, or
     * an opening tag <Name>. Please carefully organize the parent-children relation and initialize the attributes.
     *
     */
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

package hk.ust.comp3021;

import hk.ust.comp3021.utils.ASTParser;
import hk.ust.comp3021.utils.TestKind;
import hk.ust.comp3021.utils.XMLNode;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class XMLParserHiddenTest {

    public static Map<String, String> sortMapByKey(Map<String, String> map) {
        List<Map.Entry<String, String>> entryList = new ArrayList<>(map.entrySet());

        entryList.sort(new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> entry1, Map.Entry<String, String> entry2) {
                return entry1.getKey().compareTo(entry2.getKey());
            }
        });

        Map<String, String> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, String> entry : entryList) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    private static void printTree(XMLNode node, int level, StringBuilder stringBuilder) {

        stringBuilder.append(getIndentation(level)).append("<").append(node.getTagName());

        switch (node.getTagName()) {
            case "Constant" -> {
                for (String attributeName : List.of("value", "kind")) {
                    if (!node.hasAttribute(attributeName)) {
                        stringBuilder.append(" ").append(attributeName).append("=\" \"");
                        continue;
                    }

                    stringBuilder.append(" ").append(attributeName).append("=\"").append(node.getAttribute(attributeName)).append("\"");
                }
            }
            case "arguments" -> {
                for (String attributeName : List.of("vararg", "kwarg")) {
                    if (!node.hasAttribute(attributeName)) {
                        continue;
                    }
                    stringBuilder.append(" ").append(attributeName).append("=\"").append(node.getAttribute(attributeName)).append("\"");
                }
            }
            case "arg" -> {
                for (String attributeName : List.of("arg", "annotation", "type_comment")) {
                    if (!node.hasAttribute(attributeName)) {
                        continue;
                    }
                    stringBuilder.append(" ").append(attributeName).append("=\"").append(node.getAttribute(attributeName)).append("\"");
                }
            }
            default -> {
                for (Map.Entry<String, String> entry : sortMapByKey(node.getAttributes()).entrySet()) {
                    if (!entry.getKey().equals("lineno") && !entry.getKey().equals("col_offset") &&
                            !entry.getKey().equals("end_lineno") && !entry.getKey().equals("end_col_offset")) {
                        stringBuilder.append(" ").append(entry.getKey()).append("=\"").append(entry.getValue()).append("\"");
                    }
                }
            }
        }
        if (node.hasAttribute("lineno")) {
            stringBuilder.append(" lineno=\"").append(node.getAttribute("lineno")).append("\"");
        }
        if (node.hasAttribute("col_offset")) {
            stringBuilder.append(" col_offset=\"").append(node.getAttribute("col_offset")).append("\"");
        }
        if (node.hasAttribute("end_lineno")) {
            stringBuilder.append(" end_lineno=\"").append(node.getAttribute("end_lineno")).append("\"");
        }
        if (node.hasAttribute("end_col_offset")) {
            stringBuilder.append(" end_col_offset=\"").append(node.getAttribute("end_col_offset")).append("\"");
        }
        if (node.getChildren().isEmpty()) {
            stringBuilder.append("/>\n");
        } else {
            stringBuilder.append(">\n");
        }
        for (XMLNode child : node.getChildren()) {
            printTree(child, level + 1, stringBuilder);
        }
        if (!node.getChildren().isEmpty()) {
            stringBuilder.append(getIndentation(level)).append("</").append(node.getTagName()).append(">\n");
        }
    }

    private static String getIndentation(int level) {
        return "  ".repeat(Math.max(0, level));
    }

    public static double compareFileWithStringCommon(String filePath, String compareString) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder fileContent = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                fileContent.append(line).append("\n");
            }

            String fileString = fileContent.toString();

            String[] lines1 = fileString.split("\\r?\\n");
            String[] lines2 = compareString.split("\\r?\\n");

            int count = 0;
            for (String line1 : lines1) {
                for (String line2 : lines2) {
                    if (line1.equals(line2)) {
                        count++;
                        break;  // Found a match, move to the next line in lines1
                    }
                }
            }
            return (double) count / (double) lines2.length;
        } catch (IOException e) {
            return 0.;
        }
    }

    @Tag(TestKind.HIDDEN)
    @Test
    void XMLParserTest() {
        ASTManagerEngine engine = new ASTManagerEngine();
        int xmlFileTot = engine.countXMLFiles(engine.getDefaultXMLFileDir());
        double commonPart = 0.;
        for (int i = 0; i < xmlFileTot; i++) {
            ASTParser parser = new ASTParser(String.valueOf(i));
            parser.parse2XMLNode();
            StringBuilder builder = new StringBuilder();
            printTree(parser.getRootXMLNode(), 0, builder);
            commonPart += compareFileWithStringCommon("resources/pythonxml/"
                    + "python_" + String.valueOf(i) + ".xml", builder.toString());
        }
        System.out.println("XMLParser Common Part " + commonPart);
        assertEquals(837, commonPart);

    }
}
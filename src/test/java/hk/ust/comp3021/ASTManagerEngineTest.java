package hk.ust.comp3021;

import hk.ust.comp3021.utils.ASTParser;
import hk.ust.comp3021.utils.TestKind;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ASTManagerEngineTest {

    @Tag(TestKind.PUBLIC)
    @Test
    void testParse2XMLNode() {
        ASTParser parser = new ASTParser("1");
        parser.parse2XMLNode();
        assertNotNull(parser.getRootXMLNode());
        assertEquals(parser.getRootXMLNode().getTagName(), "ast");
        assertFalse(parser.isErr());
    }

    @Tag(TestKind.PUBLIC)
    @Test
    void testParseASTNode() {
        ASTParser parser = new ASTParser("1");
        parser.parse();
        assertNotNull(parser.getASTModule());
        assertFalse(parser.isErr());
    }

    @Tag(TestKind.PUBLIC)
    @Test
    void testCalculateOp2Nums() {
        ASTManagerEngine engine = new ASTManagerEngine();
        int xmlFileTot = engine.countXMLFiles(engine.getDefaultXMLFileDir());
        for (int i = 0; i < xmlFileTot; i++) {
           engine.processXMLParsing(String.valueOf(i));
        }
        HashMap<String, Integer> op2Num = engine.calculateOp2Nums();
        HashMap<String, Integer> expectedOp2Num = new HashMap<>();

        expectedOp2Num.put("And", 253);
        expectedOp2Num.put("Or", 101);

        expectedOp2Num.put("Add", 1257);
        expectedOp2Num.put("Sub", 862);
        expectedOp2Num.put("Mult", 171);
        expectedOp2Num.put("Div", 18);
        expectedOp2Num.put("Mod", 86);
        expectedOp2Num.put("Pow", 11);
        expectedOp2Num.put("LShift", 16);
        expectedOp2Num.put("RShift", 22);
        expectedOp2Num.put("BitOr", 6);
        expectedOp2Num.put("BitXor", 24);
        expectedOp2Num.put("BitAnd", 43);
        expectedOp2Num.put("FloorDiv", 153);

        expectedOp2Num.put("Invert", 2);
        expectedOp2Num.put("Not", 222);
        expectedOp2Num.put("USub", 265);
        expectedOp2Num.put("Eq", 671);
        expectedOp2Num.put("NotEq", 119);

        expectedOp2Num.put("Lt", 375);
        expectedOp2Num.put("LtE", 156);
        expectedOp2Num.put("Gt", 238);
        expectedOp2Num.put("GtE", 92);
        expectedOp2Num.put("Is", 2);
        expectedOp2Num.put("IsNot", 17);
        expectedOp2Num.put("In", 95);
        expectedOp2Num.put("NotIn", 76);
        assertEquals(expectedOp2Num, op2Num);
    }

    @Tag(TestKind.PUBLIC)
    @Test
    void testMostCommonUsedOp() {
        ASTManagerEngine engine = new ASTManagerEngine();
        int xmlFileTot = engine.countXMLFiles(engine.getDefaultXMLFileDir());
        for (int i = 0; i < xmlFileTot; i++) {
            engine.processXMLParsing(String.valueOf(i));
        }
        HashMap<String, Integer> op2Num = engine.calculateOp2Nums();
        String maxOp = engine.mostCommonUsedOp(op2Num);
        assertEquals(maxOp, "Add");
    }


    @Tag(TestKind.PUBLIC)
    @Test
    void testCalculateNode2Num() {
        ASTManagerEngine engine = new ASTManagerEngine();
        int xmlFileTot = engine.countXMLFiles(engine.getDefaultXMLFileDir());
        for (int i = 0; i < xmlFileTot; i++) {
            engine.processXMLParsing(String.valueOf(i));
        }
        HashMap<String, Integer> node2Num = engine.calculateNode2Nums("0");
        HashMap<String, Integer> expectedNode2Num = new HashMap<>();
        expectedNode2Num.put("Module", 1);
        expectedNode2Num.put("ClassDef", 1);
        expectedNode2Num.put("FunctionDef", 2);
        expectedNode2Num.put("arguments", 2);

        expectedNode2Num.put("arg", 4);
        expectedNode2Num.put("Name", 29);
        expectedNode2Num.put("Assign", 7);
        expectedNode2Num.put("Constant", 1);

        expectedNode2Num.put("While", 2);

        expectedNode2Num.put("BoolOp", 1);
        expectedNode2Num.put("Compare", 2);
        expectedNode2Num.put("Attribute", 13);

        expectedNode2Num.put("Tuple", 2);
        expectedNode2Num.put("Return", 2);

        expectedNode2Num.put("Subscript", 2);

        expectedNode2Num.put("Call", 1);
        expectedNode2Num.put("If", 1);
        assertEquals(expectedNode2Num, node2Num);
    }

    @Tag(TestKind.PUBLIC)
    @Test
    void testTotCalculateNode2Num() {
        ASTManagerEngine engine = new ASTManagerEngine();
        int xmlFileTot = engine.countXMLFiles(engine.getDefaultXMLFileDir());
        for (int i = 0; i < xmlFileTot; i++) {
            engine.processXMLParsing(String.valueOf(i));
        }
        HashMap<String, Integer> expectedNode2Num = new HashMap<>();

        HashMap<String, Integer> totNode2Num = new HashMap<>();
        for (String key : engine.getId2ASTModules().keySet()) {
            HashMap<String, Integer> node2Num = engine.calculateNode2Nums(key);
            for (Map.Entry<String, Integer> entry : node2Num.entrySet()) {
                if (totNode2Num.containsKey(entry.getKey())) {
                    int currentValue = totNode2Num.get(entry.getKey());
                    totNode2Num.put(entry.getKey(), currentValue + entry.getValue());
                } else {
                    totNode2Num.put(entry.getKey(), entry.getValue());
                }
            }
        }

        expectedNode2Num.put("Module", 837);
        expectedNode2Num.put("ClassDef", 647);
        expectedNode2Num.put("FunctionDef", 1126);
        expectedNode2Num.put("arguments", 1126);

        expectedNode2Num.put("arg", 2508);
        expectedNode2Num.put("Name", 22230);
        expectedNode2Num.put("Assign", 3624);
        expectedNode2Num.put("Constant", 5627);

        expectedNode2Num.put("While", 431);

        expectedNode2Num.put("BoolOp", 354);
        expectedNode2Num.put("Compare", 1809);
        expectedNode2Num.put("Attribute", 3480);

        expectedNode2Num.put("Tuple", 466);
        expectedNode2Num.put("Return", 1365);

        expectedNode2Num.put("Subscript", 2425);

        expectedNode2Num.put("Call", 3708);
        expectedNode2Num.put("If", 1505);

        expectedNode2Num.put("AugAssign", 730);
        expectedNode2Num.put("Break", 87);

        expectedNode2Num.put("Continue", 29);

        expectedNode2Num.put("List", 454);
        expectedNode2Num.put("keyword", 66);

        expectedNode2Num.put("For", 658);
        expectedNode2Num.put("BinOp", 1939);
        expectedNode2Num.put("Expr", 1199);
        expectedNode2Num.put("UnaryOp", 489);
        assertEquals(expectedNode2Num, totNode2Num);
    }


}
package hk.ust.comp3021;


import hk.ust.comp3021.utils.ASTParser;
import hk.ust.comp3021.utils.TestKind;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
public class ASTManagerEngineHiddenTest {


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

    @Tag(TestKind.PUBLIC)
    @Test
    void testCalledFuncOnXML26() {
        ASTManagerEngine engine = new ASTManagerEngine();
        engine.processXMLParsing("26");

        HashMap<String, Set<String>> func2CalledFuncs = engine.calculateCalledFunc();
        HashMap<String, Set<String>> expectedMap = new HashMap<>();

        expectedMap.put("26_horspool_2", Set.of("26_len_3", "26_generateBadCharTable_5", "26_bc_table.get_14"));
        expectedMap.put("26_generateBadCharTable_19", Set.of("26_len_20", "26_dict_21", "26_range_23"));
        assertEquals(func2CalledFuncs, expectedMap);
    }

    @Test
    void testCalledFuncOnXML833() {
        ASTManagerEngine engine = new ASTManagerEngine();
        engine.processXMLParsing("833");

        HashMap<String, Set<String>> func2CalledFuncs = engine.calculateCalledFunc();
        HashMap<String, Set<String>> expectedMap = new HashMap<>();

        expectedMap.put("833_printList_24", Set.of("833_print_27", "833_print_32", "833_print_34"));
        expectedMap.put("833_push_17", Set.of("833_Node_19"));
        expectedMap.put("833___init___4", Set.of("833___init___5", "833_super_5"));
        expectedMap.put("833___init___12", Set.of("833___init___13", "833_super_13"));
        expectedMap.put("833_printMiddle_36", Set.of("833_print_46", "833_print_48"));
        assertEquals(func2CalledFuncs, expectedMap);
    }


    @Tag(TestKind.PUBLIC)
    @Test
    void testCalledFuncOnXMLAll() {
        ASTManagerEngine engine = new ASTManagerEngine();
        int xmlFileTot = engine.countXMLFiles(engine.getDefaultXMLFileDir());
        for (int i = 0; i < xmlFileTot; i++) {
            engine.processXMLParsing(String.valueOf(i));
        }

        HashMap<String, Set<String>> func2CalledFuncs = engine.calculateCalledFunc();
        assertEquals(func2CalledFuncs.size(), 1126);

        Set<String> mergedSet = new HashSet<>();
        for (Set<String> set : func2CalledFuncs.values()) {
            mergedSet.addAll(set);
        }
        assertEquals(mergedSet.size(), 2570);
    }


    @Tag(TestKind.PUBLIC)
    @Test
    public void testPrintedInformationAll() {
        ASTManagerEngine engine = new ASTManagerEngine();
        int xmlFileTot = engine.countXMLFiles(engine.getDefaultXMLFileDir());
        for (int i = 0; i < xmlFileTot; i++) {
            engine.processXMLParsing(String.valueOf(i));
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream originalPrintStream = System.out;
        System.setOut(printStream);

        engine.findFuncWithArgGtN(4);
        System.setOut(originalPrintStream);
        String printedOutput = outputStream.toString();
        Set<String> computedOutput = Set.of(printedOutput.trim().split("\\r?\\n"));
        System.out.println(computedOutput.size());
        assertEquals(92, computedOutput.size());
    }

    @Tag(TestKind.PUBLIC)
    @Test
    public void testPrintedInformationAllOn3() {
        ASTManagerEngine engine = new ASTManagerEngine();
        int xmlFileTot = engine.countXMLFiles(engine.getDefaultXMLFileDir());
        for (int i = 0; i < xmlFileTot; i++) {
            engine.processXMLParsing(String.valueOf(i));
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream originalPrintStream = System.out;
        System.setOut(printStream);

        engine.findFuncWithArgGtN(3);
        System.setOut(originalPrintStream);
        String printedOutput = outputStream.toString();
        Set<String> computedOutput = Set.of(printedOutput.trim().split("\\r?\\n"));
        System.out.println(computedOutput.size());
        assertEquals(338, computedOutput.size());
    }

    @Tag(TestKind.PUBLIC)
    @Test
    void testCalculateOp2NumsOn100() {
        ASTManagerEngine engine = new ASTManagerEngine();
        int xmlFileTot = engine.countXMLFiles(engine.getDefaultXMLFileDir());
        for (int i = 0; i < 100; i++) {
            engine.processXMLParsing(String.valueOf(i));
        }
        HashMap<String, Integer> op2Num = engine.calculateOp2Nums();
        HashMap<String, Integer> expectedOp2Num = new HashMap<>();

        expectedOp2Num.put("And", 32);
        expectedOp2Num.put("Or", 10);

        expectedOp2Num.put("Add", 126);
        expectedOp2Num.put("Sub", 77);
        expectedOp2Num.put("Mult", 23);
        expectedOp2Num.put("Div", 1);
        expectedOp2Num.put("Mod", 8);
        expectedOp2Num.put("LShift", 1);
        expectedOp2Num.put("RShift", 1);
        expectedOp2Num.put("BitXor", 3);
        expectedOp2Num.put("BitAnd", 2);
        expectedOp2Num.put("FloorDiv", 9);

        expectedOp2Num.put("Not", 18);
        expectedOp2Num.put("USub", 34);
        expectedOp2Num.put("Eq", 55);
        expectedOp2Num.put("NotEq", 12);

        expectedOp2Num.put("Lt", 35);
        expectedOp2Num.put("LtE", 17);
        expectedOp2Num.put("Gt", 22);
        expectedOp2Num.put("GtE", 7);
        expectedOp2Num.put("IsNot", 4);
        expectedOp2Num.put("In", 9);
        expectedOp2Num.put("NotIn", 10);
        assertEquals(expectedOp2Num, op2Num);
    }


    @Tag(TestKind.PUBLIC)
    @Test
    void testCalledFuncOnXMLAllPrint() {
        ASTManagerEngine engine = new ASTManagerEngine();
        int xmlFileTot = engine.countXMLFiles(engine.getDefaultXMLFileDir());
        for (int i = 0; i < xmlFileTot; i++) {
            engine.processXMLParsing(String.valueOf(i));
        }

        HashMap<String, Set<String>> func2CalledFuncs = engine.calculateCalledFunc();
        assertEquals(func2CalledFuncs.size(), 1126);

        Set<String> mergedSet = new HashSet<>();
        for (var pair : func2CalledFuncs.entrySet()
                .stream()
                .sorted((entry1, entry2) -> entry1.getKey().compareTo(entry2.getKey())).collect(Collectors.toList())) {
            if (pair.getKey().equals("299_isPalindrome_2")) {
                System.out.println(pair.getValue());
            }
            System.out.println(pair.getValue().size());
            mergedSet.addAll(pair.getValue());
        }

        assertEquals(mergedSet.size(), 2570);
    }


}

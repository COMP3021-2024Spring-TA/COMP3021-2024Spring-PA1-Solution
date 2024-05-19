package hk.ust.comp3021;


import hk.ust.comp3021.utils.TestKind;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
public class ASTManagerEngineHiddenTest {

    /*
     * Print all functions with # arguments greater than user-specified N
     */
    @Tag(TestKind.HIDDEN)
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

    @Tag(TestKind.HIDDEN)
    @Test
    public void testPrintedInformationAllOn4() {
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


    /*
     * Find the most commonly used operators in all ASTs
     */

    @Tag(TestKind.HIDDEN)
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

    /*
     * Print all function names and the functions invoked by each function
     */

    @Tag(TestKind.HIDDEN)
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

    @Tag(TestKind.HIDDEN)
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


    @Tag(TestKind.HIDDEN)
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

    /*
     * Given AST ID, count the number of all node types.
     */
    @Tag(TestKind.HIDDEN)
    @Test
    void testTotCalculateNode400() {
        ASTManagerEngine engine = new ASTManagerEngine();
        int xmlFileTot = engine.countXMLFiles(engine.getDefaultXMLFileDir());
        for (int i = 0; i < 400; i++) {
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

        expectedNode2Num.put("Module", 400);
        expectedNode2Num.put("ClassDef", 364);
        expectedNode2Num.put("FunctionDef", 590);
        expectedNode2Num.put("arguments", 590);

        expectedNode2Num.put("arg", 1375);
        expectedNode2Num.put("Name", 11196);
        expectedNode2Num.put("Assign", 1766);
        expectedNode2Num.put("Constant", 2537);

        expectedNode2Num.put("While", 234);

        expectedNode2Num.put("BoolOp", 181);
        expectedNode2Num.put("Compare", 883);
        expectedNode2Num.put("Attribute", 1645);

        expectedNode2Num.put("Tuple", 254);
        expectedNode2Num.put("Return", 769);

        expectedNode2Num.put("Subscript", 1411);

        expectedNode2Num.put("Call", 1476);
        expectedNode2Num.put("If", 756);

        expectedNode2Num.put("AugAssign", 409);
        expectedNode2Num.put("Break", 35);

        expectedNode2Num.put("Continue", 17);

        expectedNode2Num.put("List", 201);

        expectedNode2Num.put("For", 335);
        expectedNode2Num.put("BinOp", 966);
        expectedNode2Num.put("Expr", 379);
        expectedNode2Num.put("UnaryOp", 297);
        assertEquals(expectedNode2Num, totNode2Num);
    }

    @Tag(TestKind.HIDDEN)
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


    /*
     * Sort all functions based on # children nodes
     */
    @Tag(TestKind.HIDDEN)
    @Test
    public void testProcessNodeFreq1() {
        ASTManagerEngine engine = new ASTManagerEngine();
        int xmlFileTot = engine.countXMLFiles(engine.getDefaultXMLFileDir());
        for (int i = 0; i < xmlFileTot; i++) {
            engine.processXMLParsing(String.valueOf(i));
        }

        assertEquals(engine.getId2ASTModules().size(), 837);
        HashMap<String, Integer> funcName2NodeNum = engine.processNodeFreq();
        assertEquals(funcName2NodeNum.size(), 1126);

        Map.Entry<String, Integer> maxEntry = funcName2NodeNum.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElse(null);

        Map.Entry<String, Integer> minEntry = funcName2NodeNum.entrySet()
                .stream()
                .min(Map.Entry.comparingByValue())
                .orElse(null);

        assertEquals(maxEntry.getKey(), "637_maxPoints_2");
        assertEquals(minEntry.getKey(), "737_getBalance_22");
        Optional<String> mediumKey = funcName2NodeNum.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .skip(funcName2NodeNum.size() / 2)
                .limit(1)
                .map(Map.Entry::getKey)
                .findFirst();

        assertEquals(mediumKey.get(), "341_next_6");

    }

    @Tag(TestKind.HIDDEN)
    @Test
    public void testProcessNodeFreq2() {
        ASTManagerEngine engine = new ASTManagerEngine();
        int xmlFileTot = engine.countXMLFiles(engine.getDefaultXMLFileDir());
        for (int i = 0; i < xmlFileTot; i++) {
            engine.processXMLParsing(String.valueOf(i));
        }

        assertEquals(engine.getId2ASTModules().size(), 837);
        HashMap<String, Integer> funcName2NodeNum = engine.processNodeFreq();
        assertEquals(funcName2NodeNum.size(), 1126);

        Map.Entry<String, Integer> maxEntry = funcName2NodeNum.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElse(null);

        Map.Entry<String, Integer> minEntry = funcName2NodeNum.entrySet()
                .stream()
                .min(Map.Entry.comparingByValue())
                .orElse(null);

        double average = funcName2NodeNum.values().stream().mapToInt(Integer::intValue).average().orElse(0.0);
        assertEquals(average, 47.01065719360568);
        assertEquals(funcName2NodeNum.values().stream().mapToDouble(value -> Math.pow(value - average, 2)).sum(), 1095127.8721136767);
    }
    
}

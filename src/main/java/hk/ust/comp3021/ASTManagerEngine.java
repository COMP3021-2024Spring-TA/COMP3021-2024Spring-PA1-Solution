package hk.ust.comp3021;

import hk.ust.comp3021.expr.*;
import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.stmt.*;
import hk.ust.comp3021.utils.*;
import java.io.*;
import java.nio.file.Paths;
import java.util.*;



public class ASTManagerEngine {

    private final String defaultXMLFileDir;
    private final HashMap<String, ASTModule> id2ASTModules;

    public ASTManagerEngine() {
        defaultXMLFileDir = "resources/pythonxml/";
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
            System.out.println("  3: Print all functions names and the functions invoked by each function");
            System.out.println("  4: Given AST ID, count the number of all node types");
            System.out.println("  5: Sort all functions based on # children nodes");
            System.out.println("  6: Given AST ID, recover Python Code (Bonus Task)");
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
                    break;
                }
                case 1: {
                    userInterfaceParamNum();
                    break;
                }
                case 2: {
                    userInterfaceCommonOp();
                    break;
                }
                case 3: {
                    userInterfaceCallFuncs();
                    break;
                }
                case 4: {
                    userInterfaceCountNum();
                    break;
                }
                case 5: {
                    userInterfaceSortByChild();
                    break;
                }
                case 6: {
                    userInterfaceRecoverCode();
                    break;
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

    public int countXMLFiles(String dirPath) {
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

    /*
     * 0
     */
    public void processXMLParsing(String xmlID) {
        String xmlFileName = Paths.get(defaultXMLFileDir, "python_" + xmlID + ".xml").toString();
        ASTParser parser = new ASTParser(xmlFileName, xmlID);
        parser.parse();
        if (!parser.isErr()) {
            this.id2ASTModules.put(xmlID, parser.getASTModule());
            System.out.println("AST " + xmlID + " Succeed! The XML file is loaded!");
        } else {
            System.out.println("AST " + xmlID + " Failed! Please check your implementation!");
        }
    }

    public void userInterfaceParseXML() {
        int xmlCount = countXMLFiles(this.defaultXMLFileDir);
        System.out.println("Please specify the XML file ID to parse (0~" + xmlCount + ") or -1 for all:");
        Scanner scan1 = new Scanner(System.in);
        if (scan1.hasNextLine()) {
            String xmlID = scan1.nextLine();
            if (!xmlID.equals("-1")) {
                processXMLParsing(xmlID);
            } else {
                File directory = new File(this.defaultXMLFileDir);
                if (directory.isDirectory()) {
                    File[] files = directory.listFiles();
                    if (files != null) {
                        for (File file : files) {
                            if (file.isFile() && file.getName().toLowerCase().endsWith(".xml")) {
                                String str = file.getName().toLowerCase();
                                int startIndex = str.indexOf('_') + 1;
                                int endIndex = str.indexOf(".xml");

                                if (endIndex > startIndex) {
                                    processXMLParsing(str.substring(startIndex, endIndex));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /*
     * 1
     */
    public void findFuncWithArgGtN(int paramN) {
        for (String key : id2ASTModules.keySet()) {
            ASTModule module = id2ASTModules.get(key);
            for (FunctionDefStmt func : module.getAllFunctions()) {
                if (func.getParamNum() >= paramN) {
                    System.out.println(module.getASTID() + "_" + func.getName());
                }
            }
        }

    }

    public void userInterfaceParamNum() {
        System.out.println("Please indicate the value of N (recommended range 0~5):");
        Scanner scan2 = new Scanner(System.in);
        if (scan2.hasNextLine()) {
            String paramN = scan2.nextLine();
            try {
                int number = Integer.parseInt(paramN);
                System.out.println("Parsed number: " + number);
                findFuncWithArgGtN(number);
            } catch (NumberFormatException e) {
                System.out.println("Error! Invalid number format");
            }

        }
    }

    /*
     * 2
     */
    public HashMap<String, Integer> calculateOp2Nums() {
        HashMap<String, Integer> op2Num = new HashMap<>();

        for (String key : id2ASTModules.keySet()) {
            ASTModule module = id2ASTModules.get(key);
            for (ASTEnumOp enumOp : module.getAllOperators()) {
                op2Num.put(enumOp.getOperatorName(), op2Num.getOrDefault(enumOp.getOperatorName(), 0) + 1);
            }
        }
        return op2Num;
    }

    public void userInterfaceCommonOp() {
        HashMap<String, Integer> op2Num = calculateOp2Nums();

        String maxOp = null;
        int maxValue = Integer.MIN_VALUE;

        for (Map.Entry<String, Integer> entry : op2Num.entrySet()) {
            String curOp = entry.getKey();
            int curValue = entry.getValue();

            if (curValue > maxValue) {
                maxOp = curOp;
                maxValue = curValue;
            }
        }

        if (maxOp != null) {
            System.out.println("Most common operator is " + maxOp + " with frequency " + maxValue);
        } else {
            System.out.println("Failed to find most common operator!");
        }
    }

    /*
     * 3
     */
    public HashMap<String, Set<String>> calculateCalledFunc() {
        HashMap<String, Set<String>> func2CalledFuncs = new HashMap<String, Set<String>>();
        for (String key : id2ASTModules.keySet()) {
            ASTModule module = id2ASTModules.get(key);
            for (FunctionDefStmt func : module.getAllFunctions()) {
                Set<String> calledFuncNames = new HashSet<String>();
                for (CallExpr call : func.getAllCalledFunc()) {
                    calledFuncNames.add(module.getASTID() + "_" + call.getCalledFuncName());
                }
                func2CalledFuncs.put(module.getASTID() + "_" + func.getName(), calledFuncNames);
            }
        }
        return func2CalledFuncs;
    }

    public void userInterfaceCallFuncs() {
        HashMap<String, Set<String>> func2CalledFuncs = calculateCalledFunc();

        for (Map.Entry<String, Set<String>> entry : func2CalledFuncs.entrySet()) {
            String curFunc = entry.getKey();
            for (String calledFunc : entry.getValue()) {
                System.out.println("Func " + curFunc + " invokes func " + calledFunc);
            }
        }
    }

    /*
     * 4
     */

    public HashMap<String, Integer> calculateNode2Nums(String astID) {
        ASTModule module = id2ASTModules.get(astID);
        HashMap<String, Integer> node2Nums = new HashMap<>();

        for (ASTElement node : module.getAllNodes()) {
            node2Nums.put(node.getNodeType(), node2Nums.getOrDefault(node.getNodeType(), 0) + 1);
        }
        return node2Nums;
    }

    public void userInterfaceCountNum() {
        System.out.println("Please specify the AST ID to count Node (" + id2ASTModules.keySet() + ") or -1 for all:");
        Scanner scan1 = new Scanner(System.in);
        if (scan1.hasNextLine()) {
            String astID = scan1.nextLine();
            if (!astID.equals("-1")) {
                if (id2ASTModules.containsKey(astID)) {
                    HashMap<String, Integer> node2Num = calculateNode2Nums(astID);
                    for (Map.Entry<String, Integer> entry : node2Num.entrySet()) {
                        System.out.print(astID + entry.getKey() + " node with frequency " + entry.getValue());
                    }
                }
            } else {
                HashMap<String, Integer> totNode2Num = new HashMap<>();
                for (String key : id2ASTModules.keySet()) {
                    HashMap<String, Integer> node2Num = calculateNode2Nums(key);
                    for (Map.Entry<String, Integer> entry : node2Num.entrySet()) {
                        if (totNode2Num.containsKey(entry.getKey())) {
                            int currentValue = totNode2Num.get(entry.getKey());
                            totNode2Num.put(entry.getKey(), currentValue + entry.getValue());
                        } else {
                            totNode2Num.put(entry.getKey(), entry.getValue());
                        }
                    }
                }
                for (Map.Entry<String, Integer> entry : totNode2Num.entrySet()) {
                    System.out.print("All " + entry.getKey() + " node with frequency " + entry.getValue() + "\n");
                }
            }
        }
    }

    /*
     * 5
     */

    public HashMap<String, Integer> processNodeFreq() {
        HashMap<String, Integer> funcName2NodeNum = new HashMap<>();

        for (String key : id2ASTModules.keySet()) {
            ASTModule module = id2ASTModules.get(key);
            for (FunctionDefStmt func : module.getAllFunctions()) {
                funcName2NodeNum.put(module.getASTID() + "_" + func.getName(), func.getChildren().size());

            }
        }
        return funcName2NodeNum;

    }

    public List<Map.Entry<String, Integer>> sortHashMapByValue(HashMap<String, Integer> map) {
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(map.entrySet());

        // Manual implementation of bubble sort
        int n = entries.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Map.Entry<String, Integer> entry1 = entries.get(j);
                Map.Entry<String, Integer> entry2 = entries.get(j + 1);
                if (entry1.getValue() > entry2.getValue()) {
                    Collections.swap(entries, j, j + 1);
                }
            }
        }

        return entries;
    }

    public void userInterfaceSortByChild() {
        HashMap<String, Integer> funcName2NodeNum = processNodeFreq();
        List<Map.Entry<String, Integer>> sortedEntries = sortHashMapByValue(funcName2NodeNum);

        for (Map.Entry<String, Integer> entry : sortedEntries) {
            System.out.println("Func " + entry.getKey() + " has complexity " + entry.getValue());
        }
    }

    /*
     * 6
     */
    public void userInterfaceRecoverCode() {
        System.out.println("Please specify the AST ID to recover code (" + id2ASTModules.keySet() + ")");
        Scanner scan1 = new Scanner(System.in);
        if (scan1.hasNextLine()) {
            String astID = scan1.nextLine();
            if (id2ASTModules.containsKey(astID)) {
                ASTModule module = id2ASTModules.get(astID);
                System.out.println("Python Code " + astID);
                StringBuilder stringBuilder = new StringBuilder("");
                module.printByPos(stringBuilder);
                System.out.println(stringBuilder.toString());
            } else {
                System.out.println("Invalid AST ID " + astID + "!");
            }
        }
    }
}

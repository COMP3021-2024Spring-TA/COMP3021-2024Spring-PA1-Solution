package hk.ust.comp3021.misc;

import hk.ust.comp3021.utils.*;
import java.util.*;


public abstract class ASTElement {
    private int lineno = -1;
    private int colOffset = -1;
    private int endLineno = -1;
    private int endColOffset = -1;

    public ASTElement(int lineno, int colOffset, int endLineno, int endColOffset) {
        this.lineno = lineno;
        this.colOffset = colOffset;
        this.endLineno = endLineno;
        this.endColOffset = endColOffset;
    }

    public ASTElement() {

    }

    public int getLineNo() {
        return this.lineno;
    }

    public int getColOffset() {
        return this.colOffset;
    }

    public int getEndLineNo() {
        return this.endLineno;
    }

    public int getEndColOffset() {
        return this.endColOffset;
    }

    public static int countNowLineNo(StringBuilder str) {
        int frequency = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '\n') {
                frequency++;
            }
        }
        return frequency;
    }

    public static int countNowColOffset(StringBuilder str) {
        String splitedStr = str.toString();
        String[] lines = splitedStr.split("\n");
        String lastLine = lines[lines.length - 1];
        return lastLine.length();
    }

    public void fillStartBlanks(StringBuilder str) {
        int curLineNo = countNowLineNo(str) + 1;
        int curColOffset = countNowColOffset(str);

        str.append("\n".repeat(this.getLineNo() - curLineNo));
        if (this.getLineNo() == curLineNo) {
            str.append(" ".repeat(this.getColOffset() - curColOffset));
        } else {
            str.append(" ".repeat(this.getColOffset()));
        }
    }

    public void fillEndBlanks(StringBuilder str) {
        int curLineNo = countNowLineNo(str) + 1;
        int curColOffset = countNowColOffset(str);

        str.append("\n".repeat(this.getEndLineNo() - curLineNo));
        str.append(" ".repeat(this.getEndColOffset() - curColOffset));
    }

    public ASTElement(XMLNode node) {
        if (node.hasAttribute("lineno")) {
            this.lineno = Integer.parseInt(node.getAttribute("lineno"));
        }
        if (node.hasAttribute("colOffset")) {
            this.colOffset = Integer.parseInt(node.getAttribute("colOffset"));
        }
        if (node.hasAttribute("endLineno")) {
            this.endLineno = Integer.parseInt(node.getAttribute("endLineno"));
        }
        if (node.hasAttribute("endColOffset")) {
            this.endColOffset = Integer.parseInt(node.getAttribute("endColOffset"));
        }
    }

    public int compareTo(ASTElement other) {
        if (this.lineno != other.lineno) {
            return Integer.compare(this.lineno, other.lineno);
        } else if (this.colOffset != other.colOffset) {
            return Integer.compare(this.colOffset, other.colOffset);
        } else if (this.endLineno != other.endLineno) {
            return Integer.compare(this.endLineno, other.endLineno);
        } else {
            return Integer.compare(this.endColOffset, other.endColOffset);
        }
    }

    public static void elementSort(List<ASTElement> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                ASTElement obj1 = list.get(j);
                ASTElement obj2 = list.get(j + 1);
                if (obj1.compareTo(obj2) > 0) {
                    Collections.swap(list, j, j + 1);
                }
            }
        }
    }

    public abstract String getNodeType();

    public abstract void printByPos(StringBuilder str);

    public abstract ArrayList<ASTElement> getChildren();

    public abstract int countChildren();
}

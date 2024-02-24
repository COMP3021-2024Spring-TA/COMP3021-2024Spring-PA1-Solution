package hk.ust.comp3021.misc;

import java.util.*;

import hk.ust.comp3021.utils.*;

public abstract class ASTElement {
	private int lineno = -1;
	private int col_offset = -1;
	private int end_lineno = -1;
	private int end_col_offset = -1;

	public ASTElement(int lineno, int col_offset, int end_lineno, int end_col_offset) {
		this.lineno = lineno;
		this.col_offset = col_offset;
		this.end_lineno = end_lineno;
		this.end_col_offset = end_col_offset;
	}

	public ASTElement() {

	}

	public int getLineNo() {
		return this.lineno;
	}

	public int getColOffset() {
		return this.col_offset;
	}

	public int getEndLineNo() {
		return this.end_lineno;
	}

	public int getEndColOffset() {
		return this.end_col_offset;
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
		if (node.hasAttribute("col_offset")) {
			this.col_offset = Integer.parseInt(node.getAttribute("col_offset"));
		}
		if (node.hasAttribute("end_lineno")) {
			this.end_lineno = Integer.parseInt(node.getAttribute("end_lineno"));
		}
		if (node.hasAttribute("end_col_offset")) {
			this.end_col_offset = Integer.parseInt(node.getAttribute("end_col_offset"));
		}
	}

	public int compareTo(ASTElement other) {
		if (this.lineno != other.lineno) {
			return Integer.compare(this.lineno, other.lineno);
		} else if (this.col_offset != other.col_offset) {
			return Integer.compare(this.col_offset, other.col_offset);
		} else if (this.end_lineno != other.end_lineno) {
			return Integer.compare(this.end_lineno, other.end_lineno);
		} else {
			return Integer.compare(this.end_col_offset, other.end_col_offset);
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

package hk.ust.comp3021;

import hk.ust.comp3021.utils.TestKind;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BonusHiddenTest {
    @Tag(TestKind.HIDDEN)
    @Test
    public void testBonusPrintXML1() {
        ASTManagerEngine engine = new ASTManagerEngine();
        engine.processXMLParsing("1");
        StringBuilder stringBuilder = new StringBuilder("");
        engine.getId2ASTModules().get("1").printByPos(stringBuilder);
        String expectedOutput = "class Solution:\n" +
                "    def sectionSort(self, head: ListNode):\n" +
                "        node_i = head\n" +
                "\n" +
                "        while node_i and node_i.next:\n" +
                "\n" +
                "            min_node = node_i\n" +
                "            node_j = node_i.next\n" +
                "            while node_j:\n" +
                "                if node_j.val < min_node.val:\n" +
                "                    min_node = node_j\n" +
                "                node_j = node_j.next\n" +
                "\n" +
                "            if node_i != min_node:\n" +
                "                node_i.val, min_node.val = min_node.val, node_i.val\n" +
                "            node_i = node_i.next\n" +
                "\n" +
                "        return head\n" +
                "\n" +
                "    def sortList(self, head: Optional[ListNode]) -> Optional[ListNode]:\n" +
                "        return self.sectionSort(head)";
        assertEquals(expectedOutput, stringBuilder.toString());
    }

    @Tag(TestKind.HIDDEN)
    @Test
    public void testBonusPrintXML2() {
        ASTManagerEngine engine = new ASTManagerEngine();
        engine.processXMLParsing("2");
        StringBuilder stringBuilder = new StringBuilder("");
        engine.getId2ASTModules().get("2").printByPos(stringBuilder);
        String expectedOutput = "class Solution:\n" +
                "    def insertionSort(self, head: ListNode):\n" +
                "        if not head or not head.next:\n" +
                "            return head\n" +
                "\n" +
                "        dummy_head = ListNode(-1)\n" +
                "        dummy_head.next = head\n" +
                "        sorted_list = head\n" +
                "        cur = head.next\n" +
                "\n" +
                "        while cur:\n" +
                "            if sorted_list.val <= cur.val:\n" +
                "\n" +
                "                sorted_list = sorted_list.next\n" +
                "            else:\n" +
                "                prev = dummy_head\n" +
                "                while prev.next.val <= cur.val:\n" +
                "                    prev = prev.next\n" +
                "\n" +
                "                sorted_list.next = cur.next\n" +
                "                cur.next = prev.next\n" +
                "                prev.next = cur\n" +
                "            cur = sorted_list.next\n" +
                "\n" +
                "        return dummy_head.next\n" +
                "\n" +
                "    def sortList(self, head: Optional[ListNode]) -> Optional[ListNode]:\n" +
                "        return self.insertionSort(head)";
        assertEquals(expectedOutput, stringBuilder.toString());
    }

    @Tag(TestKind.HIDDEN)
    @Test
    public void testBonusPrintXML3() {
        ASTManagerEngine engine = new ASTManagerEngine();
        engine.processXMLParsing("3");
        StringBuilder stringBuilder = new StringBuilder("");
        engine.getId2ASTModules().get("3").printByPos(stringBuilder);
        String expectedOutput = "class Solution:\n" +
                "    def merge(self, left, right):\n" +
                "\n" +
                "        dummy_head = ListNode(-1)\n" +
                "        cur = dummy_head\n" +
                "        while left and right:\n" +
                "            if left.val <= right.val:\n" +
                "                cur.next = left\n" +
                "                left = left.next\n" +
                "            else:\n" +
                "                cur.next = right\n" +
                "                right = right.next\n" +
                "            cur = cur.next\n" +
                "\n" +
                "        if left:\n" +
                "            cur.next = left\n" +
                "        elif right:\n" +
                "            cur.next = right\n" +
                "\n" +
                "        return dummy_head.next\n" +
                "\n" +
                "    def mergeSort(self, head: ListNode):\n" +
                "\n" +
                "        if not head or not head.next:\n" +
                "            return head\n" +
                "\n" +
                "\n" +
                "        slow, fast = head, head.next\n" +
                "        while fast and fast.next:\n" +
                "            slow = slow.next\n" +
                "            fast = fast.next.next\n" +
                "\n" +
                "\n" +
                "        left_head, right_head = head, slow.next\n" +
                "        slow.next = None\n" +
                "\n" +
                "\n" +
                "        return self.merge(self.mergeSort(left_head), self.mergeSort(right_head))\n" +
                "\n" +
                "    def sortList(self, head: Optional[ListNode]) -> Optional[ListNode]:\n" +
                "        return self.mergeSort(head)";
        assertEquals(expectedOutput, stringBuilder.toString());
    }

    @Tag(TestKind.HIDDEN)
    @Test
    public void testBonusPrintXML4() {
        ASTManagerEngine engine = new ASTManagerEngine();
        engine.processXMLParsing("4");
        StringBuilder stringBuilder = new StringBuilder("");
        engine.getId2ASTModules().get("4").printByPos(stringBuilder);
        String expectedOutput = "class Solution:\n" +
                "    def partition(self, left: ListNode, right: ListNode):\n" +
                "\n" +
                "        if left == right or left.next == right:\n" +
                "            return left\n" +
                "\n" +
                "        pivot = left.val\n" +
                "\n" +
                "        node_i, node_j = left, left.next\n" +
                "\n" +
                "        while node_j != right:\n" +
                "\n" +
                "            if node_j.val < pivot:\n" +
                "\n" +
                "                node_i = node_i.next\n" +
                "\n" +
                "                node_i.val, node_j.val = node_j.val, node_i.val\n" +
                "            node_j = node_j.next\n" +
                "\n" +
                "        node_i.val, left.val = left.val, node_i.val\n" +
                "        return node_i\n" +
                "\n" +
                "    def quickSort(self, left: ListNode, right: ListNode):\n" +
                "        if left == right or left.next == right:\n" +
                "            return left\n" +
                "        pi = self.partition(left, right)\n" +
                "        self.quickSort(left, pi)\n" +
                "        self.quickSort(pi.next, right)\n" +
                "        return left\n" +
                "\n" +
                "    def sortList(self, head: Optional[ListNode]) -> Optional[ListNode]:\n" +
                "        if not head or not head.next:\n" +
                "            return head\n" +
                "        return self.quickSort(head, None)";
        assertEquals(expectedOutput, stringBuilder.toString());
    }

    @Tag(TestKind.HIDDEN)
    @Test
    public void testBonusPrintXML11() {
        ASTManagerEngine engine = new ASTManagerEngine();
        engine.processXMLParsing("11");
        StringBuilder stringBuilder = new StringBuilder("");
        engine.getId2ASTModules().get("11").printByPos(stringBuilder);
        String expectedOutput = "class Solution:\n" +
                "    def hasCycle(self, head: ListNode) -> bool:\n" +
                "        if head == None or head.next == None:\n" +
                "            return False\n" +
                "\n" +
                "        slow = head\n" +
                "        fast = head.next\n" +
                "\n" +
                "        while slow != fast:\n" +
                "            if fast == None or fast.next == None:\n" +
                "                return False\n" +
                "            slow = slow.next\n" +
                "            fast = fast.next.next\n" +
                "\n" +
                "        return True";
        assertEquals(expectedOutput, stringBuilder.toString());
    }

    @Tag(TestKind.HIDDEN)
    @Test
    public void testBonusPrintXML6() {
        ASTManagerEngine engine = new ASTManagerEngine();
        engine.processXMLParsing("6");
        StringBuilder stringBuilder = new StringBuilder("");
        engine.getId2ASTModules().get("6").printByPos(stringBuilder);
        String expectedOutput = "class Solution:\n" +
                "    def removeNthFromEnd(self, head: ListNode, n: int) -> ListNode:\n" +
                "        newHead = ListNode(0, head)\n" +
                "        fast = head\n" +
                "        slow = newHead\n" +
                "        while n:\n" +
                "            fast = fast.next\n" +
                "            n -= 1\n" +
                "        while fast:\n" +
                "            fast = fast.next\n" +
                "            slow = slow.next\n" +
                "        slow.next = slow.next.next\n" +
                "        return newHead.next";
        assertEquals(expectedOutput, stringBuilder.toString());
    }

    @Tag(TestKind.HIDDEN)
    @Test
    public void testBonusPrintXML13() {
        ASTManagerEngine engine = new ASTManagerEngine();
        engine.processXMLParsing("13");
        StringBuilder stringBuilder = new StringBuilder("");
        engine.getId2ASTModules().get("13").printByPos(stringBuilder);
        String expectedOutput = "\n" +
                "def create(self, data):\n" +
                "    self.head = ListNode(0)\n" +
                "    cur = self.head\n" +
                "    for i in range(len(data)):\n" +
                "        node = ListNode(data[i])\n" +
                "        cur.next = node\n" +
                "        cur = cur.next";
        assertEquals(expectedOutput, stringBuilder.toString());
    }

    @Tag(TestKind.HIDDEN)
    @Test
    public void testBonusPrintXML8() {
        ASTManagerEngine engine = new ASTManagerEngine();
        engine.processXMLParsing("8");
        StringBuilder stringBuilder = new StringBuilder("");
        engine.getId2ASTModules().get("8").printByPos(stringBuilder);
        String expectedOutput = "class Solution:\n" +
                "    def middleNode(self, head: ListNode) -> ListNode:\n" +
                "        n = 0\n" +
                "        curr = head\n" +
                "        while curr:\n" +
                "            n += 1\n" +
                "            curr = curr.next\n" +
                "        k = 0\n" +
                "        curr = head\n" +
                "        while k < n // 2:\n" +
                "            k += 1\n" +
                "            curr = curr.next\n" +
                "        return curr";
        assertEquals(expectedOutput, stringBuilder.toString());
    }

    @Tag(TestKind.HIDDEN)
    @Test
    public void testBonusPrintXML9() {
        ASTManagerEngine engine = new ASTManagerEngine();
        engine.processXMLParsing("9");
        StringBuilder stringBuilder = new StringBuilder("");
        engine.getId2ASTModules().get("9").printByPos(stringBuilder);
        String expectedOutput = "class Solution:\n" +
                "    def middleNode(self, head: ListNode) -> ListNode:\n" +
                "        fast = head\n" +
                "        slow = head\n" +
                "        while fast and fast.next:\n" +
                "            slow = slow.next\n" +
                "            fast = fast.next.next\n" +
                "        return slow";
        assertEquals(expectedOutput, stringBuilder.toString());
    }

    @Tag(TestKind.HIDDEN)
    @Test
    public void testBonusPrintXML10() {
        ASTManagerEngine engine = new ASTManagerEngine();
        engine.processXMLParsing("10");
        StringBuilder stringBuilder = new StringBuilder("");
        engine.getId2ASTModules().get("10").printByPos(stringBuilder);
        String expectedOutput = "class Solution:\n" +
                "    def hasCycle(self, head: ListNode) -> bool:\n" +
                "        nodeset = set()\n" +
                "\n" +
                "        while head:\n" +
                "            if head in nodeset:\n" +
                "                return True\n" +
                "            nodeset.add(head)\n" +
                "            head = head.next\n" +
                "        return False";
        assertEquals(expectedOutput, stringBuilder.toString());
    }
}


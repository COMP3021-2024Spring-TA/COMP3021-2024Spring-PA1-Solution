class Solution:
    def merge(self, left, right):
        
        dummy_head = ListNode(-1)
        cur = dummy_head
        while left and right:
            if left.val <= right.val:
                cur.next = left
                left = left.next
            else:
                cur.next = right
                right = right.next
            cur = cur.next
        
        if left:
            cur.next = left
        elif right:
            cur.next = right
            
        return dummy_head.next
        
    def mergeSort(self, head: ListNode):
        
        if not head or not head.next:
            return head
        
        
        slow, fast = head, head.next
        while fast and fast.next:
            slow = slow.next 
            fast = fast.next.next 
        
        
        left_head, right_head = head, slow.next 
        slow.next = None
        
        
        return self.merge(self.mergeSort(left_head), self.mergeSort(right_head))

    def sortList(self, head: Optional[ListNode]) -> Optional[ListNode]:
        return self.mergeSort(head)
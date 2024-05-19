class Solution:
    def searchBST(self, root: TreeNode, val: int) -> TreeNode:
        if not root or val == root.val:
            return root
        if val < root.val:
            return self.searchBST(root.left, val)
        else:
            return self.searchBST(root.right, val)
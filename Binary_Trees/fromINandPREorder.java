package Binary_Trees;

import java.util.Map;
import java.util.HashMap;

public class fromINandPREorder {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode buildTree(int[] A, int[] B) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < B.length; i++) {
            map.put(B[i], i);
        }

        TreeNode root = helper(A, 0, A.length - 1, B, 0, B.length - 1, map);

        return root;

    }

    public TreeNode helper(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd,
            Map<Integer, Integer> map) {
        if (preStart > preEnd || inStart > inEnd)
            return null;

        TreeNode root = new TreeNode(preorder[preStart]);

        int inRoot = map.get(root.val);
        int numsLeft = inRoot - inStart;

        root.left = helper(preorder, preStart + 1, preStart + numsLeft, inorder, inStart, inRoot - 1, map);

        root.right = helper(preorder, preStart + numsLeft + 1, preEnd, inorder, inRoot + 1, inEnd, map);

        return root;
    }
}

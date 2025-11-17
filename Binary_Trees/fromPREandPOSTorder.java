package Binary_Trees;

import java.util.HashMap;
import java.util.Map;

public class fromPREandPOSTorder {
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

        for (int i = 0; i < A.length; i++) {
            map.put(A[i], i);
        }

        TreeNode root = helper(A, 0, A.length - 1, B, 0, B.length - 1, map);

        return root;
    }

    public TreeNode helper(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd,
            Map<Integer, Integer> map) {

        if (inStart > inEnd || postEnd < postStart)
            return null;

        TreeNode root = new TreeNode(postorder[postEnd]);
        int inRoot = map.get(root.val);

        int numsLeft = inRoot - inStart;

        root.left = helper(inorder, inStart, inRoot - 1, postorder, postStart, postStart + numsLeft - 1, map);

        root.right = helper(inorder, inRoot + 1, inEnd, postorder, postStart + numsLeft, postEnd - 1, map);

        return root;
    }
}

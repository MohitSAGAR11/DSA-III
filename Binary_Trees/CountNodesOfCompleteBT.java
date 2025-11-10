package Binary_Trees;

public class CountNodesOfCompleteBT {
    public static void main(String[] args) {

    }

    // Complete BT ---> all levels are completely filled except possibly the last
    // level where all nodes must be left aligned.
    // O(N) - time Complexity
    public static int CountNode(TreeNode root) {
        if (root == null)
            return 0;

        return 1 + CountNode(root.left) + CountNode(root.left);
    }

    // time Complexity < O(N)
    public int countNodes(TreeNode root) {
        if (root == null) return 0;

        int leftDepth = getDepth(root, true);
        int rightDepth = getDepth(root, false);
        
        if (leftDepth == rightDepth) {
            // It's a perfect binary tree
            return (1 << leftDepth) - 1;
        }
        // Not perfect, count recursively
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    private int getDepth(TreeNode node, boolean goLeft) {
        int depth = 0;
        while (node != null) {
            depth++;
            node = goLeft ? node.left : node.right;
        }
        return depth;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}

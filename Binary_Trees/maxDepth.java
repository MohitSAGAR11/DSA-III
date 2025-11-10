package Binary_Trees;
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
        this.right = null;
        this.left = null;
    }
}
public class maxDepth {
    public static void main(String[] args) {
        
    }

    // Using postorder for this
    public static int depth(TreeNode root) {
        if (root == null) return 0;

        return 1 + Math.max(depth(root.left), depth(root.right));
    }
}

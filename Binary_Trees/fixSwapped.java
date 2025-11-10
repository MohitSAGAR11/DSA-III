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

public class fixSwapped {
    public static TreeNode first = null;
    public static TreeNode second = null;
    public static TreeNode prev = new TreeNode(Integer.MIN_VALUE);
    public static void main(String[] args) {
        
    }

    public static void fixTree(TreeNode root) {
        inorder(root);
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    public static void inorder(TreeNode root) {
        if (root == null) return;

        inorder(root.left);

        if(prev != null && prev.val > root.val) first = prev;
        second = root;

        inorder(root.right);

    }


}

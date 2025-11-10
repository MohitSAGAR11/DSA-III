package Binary_Trees;

public class MorrisTraversal {
    public static void main(String[] args) {
        // Build a sample tree:
        // 1
        // / \
        // 2 3
        // / \
        // 4 5
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        inorderMorris(root);

    }

    public static void inorderMorris(TreeNode root) {
        TreeNode current = root;

        while (current != null) {

            // Case 1: No left child - visit and go right
            if (current.left == null) {
                System.out.print(current.val + " ");
                current = current.right;
            }
            // Case 2: Has left child - need to find predecessor
            else {
                // Find the rightmost node in left subtree
                TreeNode predecessor = current.left;
                while (predecessor.right != null && predecessor.right != current) {
                    predecessor = predecessor.right;
                }

                // Create thread if not exists
                if (predecessor.right == null) {
                    predecessor.right = current; // Create thread!
                    current = current.left; // Go left
                }
                // Thread exists - we've returned via thread
                else {
                    predecessor.right = null; // Remove thread
                    System.out.print(current.val + " "); // Visit
                    current = current.right; // Go right
                }
            }
        }
    }

    static class TreeNode {
        int val;
        TreeNode right;
        TreeNode left;

        TreeNode(int val) {
            this.val = val;
            this.right = right;
            this.left = left;
        }
    }
}

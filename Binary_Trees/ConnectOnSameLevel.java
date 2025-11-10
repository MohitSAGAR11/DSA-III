package Binary_Trees;

public class ConnectOnSameLevel {
    public static void main(String[] args) {

    }

    public static TreeNode connect(TreeNode root) {
        if (root == null)
            return root;
        TreeNode leftmost = root;
        while (leftmost.left != null) {
            TreeNode curr = leftmost;
            while (curr != null) {
                curr.left.next = curr.right;
                if (curr.next != null) {
                    curr.right.next = curr.next.left;
                }
                curr = curr.next;
            }
            leftmost = leftmost.left;
        }

        return root;
    }


    public static TreeNode Connect(TreeNode root) {
        if (root == null) return root;

        TreeNode leftmost = root;
        while(leftmost.left != null) {
            TreeNode curr = leftmost;
            while(curr != null) {
                curr.left.next = curr.right;
                if (curr.next != null) {
                    curr.right.next = curr.next.left;
                }
                curr = curr.next;
            }
            leftmost = leftmost.left;
        }

        return root;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode next;

        TreeNode(int val) {
            this.val = val;
            this.right = right;
            this.left = left;
            this.next = next;
        }
    }
}

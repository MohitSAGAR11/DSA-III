package Binary_Trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;



public class IterativeTraversals {
    public static void main(String[] args) {
        // Build a sample tree:
        //        1
        //       / \
        //      2   3
        //     / \
        //    4   5
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.println("Inorder:   " + inorder(root));
        System.out.println("Preorder:  " + preorder(root));
        System.out.println("Postorder: " + postorder(root));
    }

    public static List<Integer> inorder(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> st = new Stack<>();
        TreeNode curr = root;

        while (curr != null || !st.isEmpty()) {
            while (curr != null) {
                st.push(curr);
                curr = curr.left;
            }
            curr = st.pop();
            list.add(curr.val);
            curr = curr.right;

        }
        return list;
    }

    public static List<Integer> preorder(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> st = new Stack<>();
        TreeNode curr = root;

        while (curr != null || !st.isEmpty()) {
            while (curr != null) {
                list.add(curr.val);
                st.push(curr);
                curr = curr.left;
            }

            curr = st.pop();
            curr = curr.right;
        }

        return list;
    }

    public static List<Integer> postorder(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null)
            return list;

        Stack<TreeNode> st1 = new Stack<>();
        Stack<TreeNode> st2 = new Stack<>();

        st1.push(root);

        while (!st1.isEmpty()) {
            TreeNode curr = st1.pop();
            st2.push(curr);

            if (curr.left != null)
                st1.push(curr.left);
            if (curr.right != null)
                st1.push(curr.right);
        }

        while (!st2.isEmpty()) {
            list.add(st2.pop().val);
        }

        return list;
    }

    static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
        this.right = null;
        this.left = null;
    }
}

}

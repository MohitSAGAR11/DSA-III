package Binary_Trees;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;    
    TreeNode() {}   
    TreeNode(int val) { 
        this.val = val; 
    }    
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class TreeData {
    int height;
    int diameter;
    TreeData(int height, int diameter) {
        this.height = height;
        this.diameter = diameter;
    }
}

public class Diameter {
    
    public static TreeData diameter(TreeNode root) {
        if (root == null) {
            // Height is -1 for null nodes. Diameter is 0.
            return new TreeData(-1, -1);
        }
        TreeData leftData = diameter(root.left);
        TreeData rightData = diameter(root.right);
        
        // Height of the current node.
        int height = 1 + Math.max(leftData.height, rightData.height);
        
        // Current diameter passing through root: left height + right height + 2.
        int currentDiameter = leftData.height + rightData.height + 2;
        
        // The largest diameter found so far.
        int diameter = Math.max(currentDiameter, Math.max(leftData.diameter, rightData.diameter));
        
        return new TreeData(height, diameter);
    }
    
    public static void main(String[] args) {
        // Construct the binary tree.
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        
        // Compute the diameter.
        TreeData result = diameter(root);
        System.out.println("Diameter of binary tree: " + result.diameter);
    }
}
package Binary_Trees;
class TreeNode {
    int data;
    TreeNode left, right;
    TreeNode(int data) {
        this.data = data;
        left = right = null;
    }
}

public class CheckMirror {
    public static void main(String[] args) {
        
    }

    public boolean isMirror(TreeNode r1 , TreeNode r2) {
        if(r1 == null && r2 == null) return true;

        if(r1 == null || r2 == null) return false;

        if(r1.data != r2.data) return false;

        return isMirror(r1.left , r2.right) && isMirror(r1.right , r2.left);

    }
}

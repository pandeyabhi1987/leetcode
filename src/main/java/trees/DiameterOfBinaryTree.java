package trees;

public class DiameterOfBinaryTree {
    private int maxDiameter = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        calculateHeight(root);
        return maxDiameter;
    }

    public int calculateHeight(TreeNode root) {
        if (root == null) return 0;
        int leftHeight = calculateHeight(root.left);
        int rightHeight = calculateHeight(root.right);

        int currentHeight = leftHeight + rightHeight;

        maxDiameter = Math.max(maxDiameter, currentHeight);
        return maxDiameter;
    }
}

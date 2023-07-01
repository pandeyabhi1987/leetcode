package trees;

public class MaxDepthOfBinaryTree {
    public int maxDepth(TreeNode root) {
        return calculateDepth(root, 0);
    }

    private int calculateDepth(TreeNode root, int depth) {
        if (root == null) {
            return depth;
        }
        return Math.max(calculateDepth(root.right, depth + 1), calculateDepth(root.left, depth + 1));
    }
}

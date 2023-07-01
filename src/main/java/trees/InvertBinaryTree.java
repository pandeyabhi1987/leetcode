package trees;

public class InvertBinaryTree {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        //Swap the left and right children of the current node:
        //Store the left child in a temporary variable.
        //Set the left child to the right child.
        //Set the right child to the temporary variable.
        //Recursively call invertTree on the left and right children.
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

}

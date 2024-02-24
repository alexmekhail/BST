import java.util.LinkedList;
import java.util.Queue;

public class BST {
    private class TreeNode {
        int key;
        TreeNode left;
        TreeNode right;

        // constructor
        public TreeNode(int key) {
            this.key = key;
        }
    }

    private TreeNode root;
    private int size = 0;

    // getter
    public int size() {
        return size;
    }

    // This method returns true if the key exists in the tree
    public boolean hasKey(int key) {
        return hasKey(root, key);
    }

    private boolean hasKey(TreeNode node, int key) {
        if (node == null) {
            return false;
        }
        if (node.key == key) {
            return true;
        }
        return hasKey(node.left, key) || hasKey(node.right, key);
    }

    // This method returns the height of the tree
    public int height() {
        return height(root);
    }

    private int height(TreeNode node) {
        if (node == null || (node.left == null && node.right == null)) {
            return 0;
        }
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    // This is the public method that the user of the class calls to add a key
    public void add(int key) {
        if (hasKey(key)) {
            return;
        }
        root = add(root, key);
        size++;
    }

    private TreeNode add(TreeNode node, int key) {
        if (node == null) {
            return new TreeNode(key);
        }
        if (key < node.key) {
            node.left = add(node.left, key);
        } else {
            node.right = add(node.right, key);
        }
        return node;
    }

    // This method converts the tree into a pre-order string
    public String preOrderString() {
        return preOrderString(root).trim();
    }

    private String preOrderString(TreeNode node) {
        if (node == null) {
            return "";
        }
        String str = node.key + " ";
        str += preOrderString(node.left);
        str += preOrderString(node.right);
        return str;
    }

    // In order to string traversal
    public String inOrderString() {
        return inOrderString(root).trim();
    }

    private String inOrderString(TreeNode node) {
        if (node == null) {
            return "";
        }
        String str = "";
        str += inOrderString(node.left);
        str += node.key + " ";
        str += inOrderString(node.right);
        return str;
    }

    // Level order traversal to string
    public String levelOrderString() {
        if (root == null) {
            return "";
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        StringBuilder sb = new StringBuilder();

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            sb.append(node.key).append(" ");
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return sb.toString().trim();
    }

    // The delete method
    public void delete(int key) {
        root = delete(root, key);
        size--;
    }

  private TreeNode delete(TreeNode node, int key) {
    if (node == null) {
        return null;
    }
    if (key < node.key) {
        node.left = delete(node.left, key);
    } else if (key > node.key) {
        node.right = delete(node.right, key);
    } else {
        // Node to be deleted is found

        // Node with only one child or no child
        if (node.left == null) {
            return node.right;
        } else if (node.right == null) {
            return node.left;
        }

        // Node with two children: Get the in-order successor (smallest in the right subtree)
        node.key = minValue(node.right);

        // Delete the in-order successor from the right subtree
        node.right = delete(node.right, node.key);
    }

    return node;
}

// Finds the minimum value in a tree (BST lecture)
private int minValue(TreeNode node) {
    int minValue = node.key;
    while (node.left != null) {
        minValue = node.left.key;
        node = node.left;
    }
    return minValue;
}


    @Override
    public String toString() {
        return treeToString(root);
    }
    private String treeToString(TreeNode root) {
        if (root == null) {
            return "";
        }
        if (root.left == null && root.right == null) {
            return root.key + "";
        }
        if (root.right == null) {
            return root.key + "(" + treeToString(root.left) + ")";
        }

        return root.key + "(" + treeToString(root.left) + ")(" + treeToString(root.right) + ")";
    }

}
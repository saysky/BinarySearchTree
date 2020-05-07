import java.util.LinkedList;
import java.util.Queue;

/**
 * BinearySearchTree. Represent a binary search tree
 * The student cannot change the public interface
 *
 * @author Your Name
 * @version Date Starts
 */
public class BinarySearchTree<E extends Comparable<E>> {
    TreeNode<E> root; // the root of the tree

    /**
     * constructor create a empty binary search tree by setting root to be null
     */
    public BinarySearchTree() {
    }

    /**
     * search the given data in this binary search tree
     * If the data is found, return a reference to the tree node
     * othewise, return null
     *
     * @param data The target to search
     * @return a TreeNode reference to the node that contains the data
     * if no node contains data, return null
     */
    public TreeNode<E> search(E data) {
        TreeNode<E> current = root;
        while (current != null
                && data.compareTo(current.data) != 0) {
            if (data.compareTo(current.data) > 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return current;
    }

    /**
     * insert given node to this binary search tree. If this tree
     * was empty, the given node becomes the root of this tree.
     *
     * @param newNode the given node to be inserted
     */
    public void insert(TreeNode<E> newNode) {
        TreeNode<E> current = root;
        TreeNode<E> parent = null;
        if (current == null) {
            root = newNode;
        }
        while (true) {
            parent = current;
            if (newNode.data.compareTo(current.data) < 0) {
                current = current.left;
                if (current == null) {
                    parent.left = newNode;
                }
            } else {
                current = current.right;
                if (current == null) {
                    parent.right = newNode;
                }
            }
        }
    }

    /**
     * insert given data to this binary search tree. If this tree
     * was empty, the given node becomes the root of this tree.
     *
     * @param data the given data to be inserted
     */
    public void insert(E data) {
        TreeNode<E> newNode = new TreeNode(data);
        TreeNode<E> current = root;
        TreeNode<E> parent = null;
        if (current == null) {
            root = newNode;
            return;
        }
        while (true) {
            parent = current;
            if (data.compareTo(current.data) < 0) {
                current = current.left;
                if (current == null) {
                    parent.left = newNode;
                    return;
                }
            } else {
                current = current.right;
                if (current == null) {
                    parent.right = newNode;
                    return;
                }
            }
        }
    }

    /**
     * remove the given data from this binary search tree and return
     * true. If the data is not in the tree, return false
     */
    public boolean remove(E data) {
        TreeNode parent = root;
        TreeNode current = root;
        boolean isLeftChild = false;
        while (current.data.compareTo(data) != 0) {
            parent = current;
            if (current.data.compareTo(data) > 0) {
                isLeftChild = true;
                current = current.left;
            } else {
                isLeftChild = false;
                current = current.right;
            }

            if (current == null) {
                return false;
            }
        }

        if (current.left == null && current.right == null) {
            if (current == root) {
                root = null;
            }
            if (isLeftChild == true) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if (current.right == null) {
            if (current == root) {
                root = current.left;
            } else if (isLeftChild) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }

        } else if (current.left == null) {
            if (current == root) {
                root = current.right;
            } else if (isLeftChild) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        } else if (current.left != null && current.right != null) {
            TreeNode successor = getDeleteSuccessor(current);
            if (current == root) {
                root = successor;
            } else if (isLeftChild) {
                parent.left = successor;
            } else {
                parent.right = successor;
            }
            successor.left = current.left;
        }
        return true;
    }


    private TreeNode getDeleteSuccessor(TreeNode deleteNode) {
        TreeNode successor = null;
        TreeNode successorParent = null;
        TreeNode current = deleteNode.right;

        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.left;
        }

        if (successor != deleteNode.right) {
            successorParent.left = successor.right;
            successor.right = deleteNode.right;
        }

        return successor;
    }


    /**
     * return a string representation of the tree
     *
     * @return a String representation of the tree
     */
    @Override
    public String toString() {
        return "(" + toString(root) + ")";
    }

    private String toString(TreeNode<E> treeNode) {
        if (treeNode == null) {
            return "-";
        }
        return treeNode.getData().toString()
                + "(" + toString(treeNode.getLeft())
                + ", " + toString(treeNode.getRight()) + ")";
    }

    /**
     * return true if the tree is empty. False otherwise
     *
     * @return true if the tree is empty; false othewise
     */
    public boolean isEmpty() {
        if (root == null) {
            return true;
        }
        return false;
    }

    /**
     * return the height of the tree. Notice the height is defined as
     * the length of the longest path from nodes to root
     *
     * @return the height of the tree
     */
    public int height() {
        return height(root);
    }

    private int height(TreeNode<E> treeNode) {
        if (treeNode != null) {
            int leftHeight = height(treeNode.left);
            int rightHeight = height(treeNode.right);
            return 1 + (leftHeight > rightHeight ? leftHeight : rightHeight);
        }
        return 0;
    }

    /**
     * return the number of nodes in the tree
     *
     * @return the number of nodes in this tree
     */
    public int size() {
        int count = 0;
        if (root != null) {
            count++;
            count = count + size(root.left);
            count = count + size(root.right);
        }
        return count;
    }

    private int size(TreeNode<E> treeNode) {
        int count = 0;
        if (treeNode != null) {
            Queue<TreeNode> l = new LinkedList();
            l.offer(treeNode);
            while (!l.isEmpty()) {
                treeNode = l.poll();
                count++;
                if (treeNode.left != null) {
                    l.offer(treeNode.left);
                }
                if (treeNode.right != null) {
                    l.offer(treeNode.right);
                }
            }
        }
        return count;
    }
}

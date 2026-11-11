
package universitylab.datastructures;

import universitylab.model.Lab;

class AVLNode {

    Lab data;
    AVLNode left;
    AVLNode right;
    int height;

    AVLNode(Lab data) {
        this.data = data;
        left = null;
        right = null;
        height = 1;
    }
}

public class AVLTree {

    AVLNode root;

    public AVLTree() {
        root = null;
    }

    // Get height of a node
    private int getHeight(AVLNode node) {

        if (node == null) {
            return 0;
        }

        return node.height;
    }

    // Calculate balance factor
    private int getBalance(AVLNode node) {

        if (node == null) {
            return 0;
        }

        return getHeight(node.left) - getHeight(node.right);
    }

    // Update node height
    private void updateHeight(AVLNode node) {

        node.height = 1 + Math.max(
                getHeight(node.left),
                getHeight(node.right)
        );
    }

    // Right Rotation (LL Case)
    private AVLNode rightRotate(AVLNode y) {

        AVLNode x = y.left;
        AVLNode temp = x.right;

        x.right = y;
        y.left = temp;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    // Left Rotation (RR Case)
    private AVLNode leftRotate(AVLNode x) {

        AVLNode y = x.right;
        AVLNode temp = y.left;

        y.left = x;
        x.right = temp;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    // Insert Lab
    public void insertLab(Lab lab) {
        root = insertRecursive(root, lab);
    }

    private AVLNode insertRecursive(AVLNode current, Lab lab) {

        // Normal BST insertion
        if (current == null) {
            return new AVLNode(lab);
        }

        int result = lab.getLabId().compareTo(
                current.data.getLabId()
        );

        if (result < 0) {
            current.left = insertRecursive(current.left, lab);
        }
        else if (result > 0) {
            current.right = insertRecursive(current.right, lab);
        }
        else {
            System.out.println("Duplicate Lab ID!");
            return current;
        }

        // Update height
        updateHeight(current);

        // Check balance factor
        int balance = getBalance(current);

        // LL Case
        if (balance > 1 &&
                lab.getLabId().compareTo(current.left.data.getLabId()) < 0) {

            return rightRotate(current);
        }

        // RR Case
        if (balance < -1 &&
                lab.getLabId().compareTo(current.right.data.getLabId()) > 0) {

            return leftRotate(current);
        }

        // LR Case
        if (balance > 1 &&
                lab.getLabId().compareTo(current.left.data.getLabId()) > 0) {

            current.left = leftRotate(current.left);
            return rightRotate(current);
        }

        // RL Case
        if (balance < -1 &&
                lab.getLabId().compareTo(current.right.data.getLabId()) < 0) {

            current.right = rightRotate(current.right);
            return leftRotate(current);
        }

        return current;
    }

    // Search Lab
    public Lab searchLab(String labId) {
        return searchRecursive(root, labId);
    }

    private Lab searchRecursive(AVLNode current, String labId) {

        if (current == null) {
            return null;
        }

        int result = labId.compareTo(current.data.getLabId());

        if (result < 0) {
            return searchRecursive(current.left, labId);
        }
        else if (result > 0) {
            return searchRecursive(current.right, labId);
        }
        else {
            return current.data;
        }
    }

    // Delete Lab
    public void deleteLab(String labId) {

        if (searchLab(labId) == null) {
            System.out.println("Lab not found!");
            return;
        }

        root = deleteRecursive(root, labId);
    }

    private AVLNode deleteRecursive(AVLNode current, String labId) {

        if (current == null) {
            return null;
        }

        int result = labId.compareTo(current.data.getLabId());

        // Search in left subtree
        if (result < 0) {
            current.left = deleteRecursive(current.left, labId);
        }

        // Search in right subtree
        else if (result > 0) {
            current.right = deleteRecursive(current.right, labId);
        }

        // Lab found
        else {

            // Case 1: No child
            if (current.left == null && current.right == null) {
                return null;
            }

            // Case 2: Only right child
            else if (current.left == null) {
                return current.right;
            }

            // Case 3: Only left child
            else if (current.right == null) {
                return current.left;
            }

            // Case 4: Two children
            else {

                AVLNode successor = findMin(current.right);

                current.data = successor.data;

                current.right = deleteRecursive(
                        current.right,
                        successor.data.getLabId()
                );
            }
        }

        // Update height after deletion
        updateHeight(current);

        // Check balance
        int balance = getBalance(current);

        // LL Case
        if (balance > 1 && getBalance(current.left) >= 0) {
            return rightRotate(current);
        }

        // LR Case
        if (balance > 1 && getBalance(current.left) < 0) {
            current.left = leftRotate(current.left);
            return rightRotate(current);
        }

        // RR Case
        if (balance < -1 && getBalance(current.right) <= 0) {
            return leftRotate(current);
        }

        // RL Case
        if (balance < -1 && getBalance(current.right) > 0) {
            current.right = rightRotate(current.right);
            return leftRotate(current);
        }

        return current;
    }

    // Find Minimum Node
    private AVLNode findMin(AVLNode current) {

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    // Display Labs in Ascending Order
    public void inorder() {
        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(AVLNode curr) {

        if (curr == null) {
            return;
        }

        inorderRec(curr.left);

        System.out.println(curr.data);

        inorderRec(curr.right);
    }

    // Display Labs in Descending Order
    public void displayDescending() {
        descendingRec(root);
    }

    private void descendingRec(AVLNode curr) {

        if (curr == null) {
            return;
        }

        descendingRec(curr.right);

        System.out.println(curr.data);

        descendingRec(curr.left);
    }

    // Count Total Labs
    public int getCount() {
        return countNodes(root);
    }

    private int countNodes(AVLNode current) {

        if (current == null) {
            return 0;
        }

        return 1 + countNodes(current.left)
                + countNodes(current.right);
    }
}

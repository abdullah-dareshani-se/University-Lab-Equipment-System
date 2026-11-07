package universitylab.datastructures;
import com.sun.source.tree.Tree;
import universitylab.model.Lab;

class TreeNode{
    Lab data;
    TreeNode left;
    TreeNode right;

    TreeNode(Lab data){
        this.data = data;
        left = null;
        right = null;
    }
}

public class BinarySearchTree {

    TreeNode root;

    public BinarySearchTree(){
        root = null;
    }

    // Inserting a lab id

    public void insertLab(Lab lab){
        root = insertRecursive(root, lab);
    }

    private TreeNode insertRecursive(TreeNode current, Lab lab){

        if(current == null){
            return new TreeNode(lab);
        }

        int result = lab.getLabId().compareTo(current.data.getLabId());

        if(result < 0){
            current.left = insertRecursive(current.left, lab);
        }
        else if(result > 0){
            current.right = insertRecursive(current.right, lab);
        }
        else{
            System.out.println("Duplicate Lab ID!");
        }

        return current;
    }


    // Searching a Lab id  , o(logn)
    public Lab searchLab(String labId){
        return searchRecursive(root, labId);
    }

    public Lab searchRecursive(TreeNode current, String labId){

        if(current == null){
            return null;
        }

        int result = labId.compareTo(current.data.getLabId());

        if(result < 0){
            return searchRecursive(current.left,labId);
        } else if (result > 0) {
            return searchRecursive(current.right, labId);
        } else {
            return current.data;
        }
    }



    // Delete Lab
    public void deleteLab(String labId) {
        root = deleteRecursive(root, labId);
    }

    // Recursive Delete
    private TreeNode deleteRecursive(TreeNode current, String labId) {

        // Lab not found
        if (current == null) {
            System.out.println("Lab not found!");
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

            // Case 2: Only left child
            else if (current.right == null) {
                return current.left;
            }

            // Case 3: Two children
            else {

                TreeNode successor = findMin(current.right);

                current.data = successor.data;

                current.right = deleteRecursive(current.right, successor.data.getLabId());
            }
        }

        return current;
    }

    // Find Minimum Node
    private TreeNode findMin(TreeNode current) {

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }


    // use ful when administrator wants to see Lab ids in ascending order
    public void inorder() {
        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(TreeNode curr){
        if(curr == null){
            return;
        }

        inorderRec(curr.left);
        System.out.print(curr.data + " ");
        inorderRec(curr.right);
    }

    // for descending for example L80,L70, L60
    public void displayDescending() {
        descendingRec(root);
    }

    private void descendingRec(TreeNode curr) {

        if(curr == null) {
            return;
        }

        descendingRec(curr.right);

        System.out.println(curr.data);

        descendingRec(curr.left);
    }
}

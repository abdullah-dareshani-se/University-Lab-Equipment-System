
package universitylab.datastructures;

import universitylab.model.Student;

public class StudentAVLTree {

    private class AVLNode {
        Student data;
        AVLNode left;
        AVLNode right;
        int height;

        AVLNode(Student data) {
            this.data = data;
            left = null;
            right = null;
            height = 1;
        }
    }

    private AVLNode root;

    public StudentAVLTree() {
        root = null;
    }

    // Get Height
    private int getHeight(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    // Get Balance Factor
    private int getBalance(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    // Update Height
    private void updateHeight(AVLNode node) {
        node.height = 1 + Math.max(
                getHeight(node.left),
                getHeight(node.right)
        );
    }

    // Right Rotation
    private AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode temp = x.right;

        x.right = y;
        y.left = temp;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    // Left Rotation
    private AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode temp = y.left;

        y.left = x;
        x.right = temp;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    // Insert Student
    public void insertStudent(Student student) {
        root = insertRecursive(root, student);
    }

    private AVLNode insertRecursive(AVLNode current, Student student) {

        if (current == null) {
            return new AVLNode(student);
        }

        int comparison = student.getStudentId()
                .compareTo(current.data.getStudentId());

        if (comparison < 0) {
            current.left = insertRecursive(current.left, student);
        }
        else if (comparison > 0) {
            current.right = insertRecursive(current.right, student);
        }
        else {
            return current;
        }

        updateHeight(current);

        int balance = getBalance(current);

        // LL Case
        if (balance > 1 &&
                student.getStudentId().compareTo(
                        current.left.data.getStudentId()) < 0) {
            return rightRotate(current);
        }

        // RR Case
        if (balance < -1 &&
                student.getStudentId().compareTo(
                        current.right.data.getStudentId()) > 0) {
            return leftRotate(current);
        }

        // LR Case
        if (balance > 1 &&
                student.getStudentId().compareTo(
                        current.left.data.getStudentId()) > 0) {
            current.left = leftRotate(current.left);
            return rightRotate(current);
        }

        // RL Case
        if (balance < -1 &&
                student.getStudentId().compareTo(
                        current.right.data.getStudentId()) < 0) {
            current.right = rightRotate(current.right);
            return leftRotate(current);
        }

        return current;
    }

    // Search Student
    public Student searchStudent(String studentId) {

        AVLNode current = root;

        while (current != null) {

            int comparison = studentId.compareTo(
                    current.data.getStudentId());

            if (comparison == 0) {
                return current.data;
            }
            else if (comparison < 0) {
                current = current.left;
            }
            else {
                current = current.right;
            }
        }

        return null;
    }

    // Delete Student
    public void deleteStudent(String studentId) {
        root = deleteRecursive(root, studentId);
    }

    private AVLNode deleteRecursive(AVLNode current, String studentId) {

        if (current == null) {
            return null;
        }

        int comparison = studentId.compareTo(
                current.data.getStudentId());

        if (comparison < 0) {
            current.left = deleteRecursive(current.left, studentId);
        }
        else if (comparison > 0) {
            current.right = deleteRecursive(current.right, studentId);
        }
        else {

            // No child
            if (current.left == null && current.right == null) {
                return null;
            }

            // One child
            if (current.left == null) {
                return current.right;
            }

            if (current.right == null) {
                return current.left;
            }

            // Two children
            AVLNode temp = findMin(current.right);

            current.data = temp.data;

            current.right = deleteRecursive(
                    current.right,
                    temp.data.getStudentId()
            );
        }

        updateHeight(current);

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

    // Find Minimum
    private AVLNode findMin(AVLNode current) {

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    // Display Students in Ascending Order
    public void displayStudents() {
        if (root == null) {
            System.out.println("No students found.");
            return;
        }

        inorder(root);
    }

    private void inorder(AVLNode current) {

        if (current == null) {
            return;
        }

        inorder(current.left);
        System.out.println(current.data);
        inorder(current.right);
    }

    // Count Students
    public int getTotalCount() {
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

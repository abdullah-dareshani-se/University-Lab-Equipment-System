
package universitylab.services;

import universitylab.model.Student;
import universitylab.dao.StudentDAO;
import universitylab.datastructures.StudentAVLTree;

public class StudentManager {

    private StudentAVLTree studentTree;
    private StudentDAO studentDAO;

    public StudentManager() {
        studentTree = new StudentAVLTree();
        studentDAO = new StudentDAO();
    }

    // Register Student
    public void registerStudent(String studentName, String studentId,
                                String dept, String password) {

        if (isStudentExists(studentId)) {
            System.out.println("Student ID already exists!");
            return;
        }

        Student student = new Student(
                studentName, studentId, dept, password
        );

        try {
            studentDAO.insertStudent(student);
            studentTree.insertStudent(student);

            System.out.println("Student registered successfully!");

        } catch (Exception e) {
            System.out.println("Registration failed: " + e.getMessage());
        }
    }

    // Search Student
    public Student searchStudent(String studentId) {
        return studentTree.searchStudent(studentId);
    }

    // Check Student Exists
    public boolean isStudentExists(String studentId) {
        return searchStudent(studentId) != null;
    }

    // Load Students from Database
    public void loadStudents() {

        try {
            Student[] students = studentDAO.getAllStudents();

            for (int i = 0; i < students.length; i++) {

                if (!isStudentExists(students[i].getStudentId())) {
                    studentTree.insertStudent(students[i]);
                }
            }


        } catch (Exception e) {
            System.out.println(
                    "Unable to load students: " + e.getMessage()
            );
        }
    }

    // Display Students
    public void displayStudents() {
        studentTree.displayStudents();
    }

    // Total Students
    public int getTotalCount() {
        return studentTree.getTotalCount();
    }
}

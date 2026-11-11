
package universitylab.services;

import universitylab.model.Student;

// Checks login validation
public class AuthenticationManager {

    private StudentManager studentManager;

    public AuthenticationManager(StudentManager studentManager) {
        this.studentManager = studentManager;
    }

    // Student Login
    public boolean studentLogin(String studentId, String password) {

        Student s = studentManager.searchStudent(studentId);

        if (s == null) {
            System.out.println("Student not found! Register yourself.");
            return false;
        }

        if (s.getPassword().equals(password)) {
            System.out.println("Student login successful!");
            return true;
        }

        System.out.println("Incorrect password!");
        return false;
    }

    // Admin Login
    public boolean adminLogin(String username, String password) {

        if (username.equals("admin") && password.equals("123")) {
            System.out.println("Login successful!");
            return true;
        }

        System.out.println("Invalid username or password!");
        return false;
    }
}

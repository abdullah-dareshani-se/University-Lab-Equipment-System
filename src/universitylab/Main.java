
package universitylab;

import universitylab.dashboards.AdminDashboard;
import universitylab.services.AvailabilityManager;
import universitylab.services.BookingManager;
import universitylab.services.LabManager;
import universitylab.services.StudentManager;
import universitylab.services.AuthenticationManager;
import universitylab.utils.InputHelper;
import universitylab.model.Student;
import universitylab.dashboards.StudentDashboard;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        LabManager labManager = new LabManager();
        labManager.loadLabs();
        BookingManager bookingManager = new BookingManager();
        bookingManager.loadBookings();

        AvailabilityManager availabilityManager =
                new AvailabilityManager(labManager, bookingManager);

        StudentManager studentManager = new StudentManager();

        studentManager.loadStudents();

        AuthenticationManager authManager =
                new AuthenticationManager(studentManager);

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== UNIVERSITY LAB BOOKING SYSTEM =====");
            System.out.println("1. Admin Login");
            System.out.println("2. Student Login");
            System.out.println("3. Student Sign Up");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");

            int choice = InputHelper.readInt(sc);

            switch (choice) {

                case 1:

                    System.out.println("\n===== ADMIN LOGIN =====");

                    System.out.print("Enter Admin ID: ");
                    String adminId = InputHelper.readString(sc);

                    System.out.print("Enter Password: ");
                    String adminPassword = InputHelper.readString(sc);

                    if (authManager.adminLogin(adminId, adminPassword)) {

                        AdminDashboard adminDashboard = new AdminDashboard(
                                sc,
                                labManager,
                                bookingManager,
                                availabilityManager
                        );

                        adminDashboard.showMenu();

                    } else {
                        System.out.println("Invalid Admin ID or Password!");
                    }

                break;

                case 2:
                    System.out.println("\n===== STUDENT LOGIN =====");

                    System.out.print("Enter Student ID: ");
                    String loginId = InputHelper.readString(sc);

                    System.out.print("Enter Password: ");
                    String loginPassword = InputHelper.readString(sc);

                    boolean studentLoggedIn =
                            authManager.studentLogin(loginId, loginPassword);

                    if (studentLoggedIn) {

                        Student loggedInStudent =
                                studentManager.searchStudent(loginId);

                        StudentDashboard dashboard = new StudentDashboard(
                                loggedInStudent,
                                sc,
                                labManager,
                                bookingManager,
                                availabilityManager
                        );

                        dashboard.showMenu();
                    }
                    else {
                        System.out.println("Invalid Student ID or Password!");
                    }

                    break;

                case 3:
                    System.out.println("\n===== STUDENT SIGN UP =====");

                    System.out.print("Enter Student Name: ");
                    String name = InputHelper.readString(sc);

                    System.out.print("Enter Student ID: ");
                    String studentId = InputHelper.readString(sc);

                    System.out.print("Enter Department: ");
                    String dept = InputHelper.readString(sc);

                    System.out.print("Create Password: ");
                    String password = InputHelper.readString(sc);

                    studentManager.registerStudent(
                            name, studentId, dept, password
                    );

                    break;

                case 0:
                    System.out.println("Thank you for using our system!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}

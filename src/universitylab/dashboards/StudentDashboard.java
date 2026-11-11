
package universitylab.dashboards;

import universitylab.model.Booking;
import universitylab.model.Student;
import universitylab.model.Lab;
import universitylab.services.LabManager;
import universitylab.services.BookingManager;
import universitylab.services.AvailabilityManager;
import universitylab.utils.InputHelper;

import java.util.Scanner;

public class StudentDashboard {

    private Student student;
    private Scanner sc;
    private LabManager labManager;
    private BookingManager bookingManager;
    private AvailabilityManager availabilityManager;

    public StudentDashboard(
            Student student,
            Scanner sc,
            LabManager labManager,
            BookingManager bookingManager,
            AvailabilityManager availabilityManager) {

        this.student = student;
        this.sc = sc;
        this.labManager = labManager;
        this.bookingManager = bookingManager;
        this.availabilityManager = availabilityManager;
    }

    public void showMenu() {

        while (true) {

            System.out.println("\n===== STUDENT DASHBOARD =====");
            System.out.println("Welcome, " + student.getStudentName());

            System.out.println("1. Submit Lab Booking Request");
            System.out.println("2. View My Booking Pending Status");
            System.out.println("3. View My Booking History");
            System.out.println("0. Logout");

            System.out.print("Enter your choice: ");

            int choice = InputHelper.readInt(sc);

            switch (choice) {

                case 1:
                    submitBooking();
                    break;

                case 2:
                    bookingManager.displayStudentPendingBookings(
                            student.getStudentId());
                    break;

                case 3:
                    bookingManager.displayStudentHistory(
                            student.getStudentId());
                    break;

                case 0:
                    System.out.println("Logged out successfully!");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }


    private void submitBooking() {

        if (labManager.getTotalLabs() == 0) {
            System.out.println("No labs available!");
            return;
        }

        labManager.displayAllLabs();

        System.out.print("\nEnter Lab ID: ");
        String labId = InputHelper.readString(sc);

        Lab lab = labManager.searchLab(labId);

        if (lab == null) {
            System.out.println("Lab not found!");
            return;
        }

        String bookingDate = InputHelper.readDate(sc);

        System.out.println("Enter Start Time (HH:mm):");
        String startTime = InputHelper.readTime(sc);

        System.out.println("Enter End Time (HH:mm):");
        String endTime = InputHelper.readTime(sc);

        if (startTime.compareTo(endTime) >= 0) {
            System.out.println("Invalid booking time!");
            return;
        }

        System.out.print("Enter Purpose: ");
        String purpose = InputHelper.readString(sc);

        String bookingId = bookingManager.generateBookingId();

        Booking booking = new Booking(
                bookingId,
                student.getStudentId(),
                labId,
                bookingDate,
                startTime,
                endTime,
                "Pending",
                purpose
        );

        boolean saved = bookingManager.submitBooking(booking);

        if (saved) {
            System.out.println("\nBooking submitted successfully!");
            System.out.println("Booking ID: " + bookingId);
            System.out.println("Status: Pending");
        } else {
            System.out.println("\nBooking could not be saved!");
        }
    }

}

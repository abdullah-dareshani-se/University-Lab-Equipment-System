
package universitylab.dashboards;

import universitylab.model.Lab;
import universitylab.model.Booking;
import universitylab.services.LabManager;
import universitylab.services.BookingManager;
import universitylab.services.AvailabilityManager;
import universitylab.utils.InputHelper;

import java.util.Scanner;

public class AdminDashboard {

    private Scanner sc;
    private LabManager labManager;
    private BookingManager bookingManager;
    private AvailabilityManager availabilityManager;

    public AdminDashboard(
            Scanner sc,
            LabManager labManager,
            BookingManager bookingManager,
            AvailabilityManager availabilityManager) {

        this.sc = sc;
        this.labManager = labManager;
        this.bookingManager = bookingManager;
        this.availabilityManager = availabilityManager;
    }

    public void showMenu() {

        while (true) {

            System.out.println("\n===== ADMIN DASHBOARD =====");

            System.out.println("1. Add New Lab");
            System.out.println("2. View All Labs");
            System.out.println("3. Search Lab");
            System.out.println("4. Update Lab");
            System.out.println("5. Delete Lab");
            System.out.println("6. View Pending Requests");
            System.out.println("7. Process Booking Request");
            System.out.println("8. View Booking History");
            System.out.println("0. Logout");

            System.out.print("Enter your choice: ");

            int choice = InputHelper.readInt(sc);

            switch (choice) {

                case 1:
                    addLab();
                    break;

                case 2:
                    viewAllLabs();
                    break;

                case 3:
                    searchLab();
                    break;

                case 4:
                    updateLab();
                    break;

                case 5:
                    deleteLab();
                    break;

                case 6:
                    viewPendingRequests();
                    break;

                case 7:
                    processBooking();
                    break;

                case 8:
                    viewBookingHistory();
                    break;

                case 0:
                    System.out.println("Admin logged out successfully!");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private void addLab() {

        System.out.println("\n--- Add New Lab ---");

        System.out.print("Enter Lab ID: ");
        String labId = InputHelper.readString(sc);

        if (labManager.searchLab(labId) != null) {
            System.out.println("Lab ID already exists!");
            return;
        }

        System.out.print("Enter Lab Name: ");
        String labName = InputHelper.readString(sc);

        System.out.print("Enter Lab Location: ");
        String location = InputHelper.readString(sc);


        Lab lab = new Lab(labId, labName, location);

        labManager.addLab(lab);

    }

    private void viewAllLabs() {

        System.out.println("\n--- All Registered Labs ---");

        if (labManager.getTotalLabs() == 0) {
            System.out.println("No labs registered yet!");
            return;
        }

        labManager.displayAllLabs();
    }

    private void searchLab() {

        System.out.println("\n--- Search Lab ---");

        System.out.print("Enter Lab ID: ");
        String labId = InputHelper.readString(sc);

        Lab lab = labManager.searchLab(labId);

        if (lab == null) {
            System.out.println("Lab not found!");
        } else {
            System.out.println(lab);
        }
    }

    private void updateLab() {

        System.out.println("\n--- Update Lab ---");

        System.out.print("Enter Lab ID: ");
        String labId = InputHelper.readString(sc);

        Lab lab = labManager.searchLab(labId);

        if (lab == null) {
            System.out.println("Lab not found!");
            return;
        }

        System.out.print("Enter New Lab Name: ");
        String labName = InputHelper.readString(sc);

        System.out.print("Enter New Location: ");
        String location = InputHelper.readString(sc);

        labManager.updateLab(labName, labId, location);
    }

    private void deleteLab() {

        System.out.println("\n--- Delete Lab ---");

        System.out.print("Enter Lab ID: ");
        String labId = InputHelper.readString(sc);

        if (labManager.searchLab(labId) == null) {
            System.out.println("Lab not found!");
            return;
        }

        labManager.deleteLab(labId);

        System.out.println("Lab deleted successfully!");
    }

    private void viewPendingRequests() {

        System.out.println("\n--- Pending Booking Requests ---");

        if (bookingManager.getPendingCount() == 0) {
            System.out.println("No pending requests!");
            return;
        }

        bookingManager.displayPendingRequests();
    }


    private void processBooking() {

        Booking booking = bookingManager.peekNextRequest();

        if (booking == null) {
            return;
        }

        System.out.println(booking);

        System.out.println("\n1. Approve Booking");
        System.out.println("2. Reject Booking");
        System.out.print("Enter choice: ");

        int choice = InputHelper.readInt(sc);

        boolean processed = false;

        if (choice == 1) {

            processed = availabilityManager.approveBooking(booking);

        } else if (choice == 2) {
            System.out.print("Enter rejection reason: ");
            String reason = InputHelper.readString(sc);

            processed = availabilityManager.rejectBooking(booking, reason);
        } else {

            System.out.println("Invalid choice!");
            return;
        }

        if (processed) {
            bookingManager.processNextRequest();
        }
    }


    private void viewBookingHistory() {

        System.out.println("\n--- Booking History ---");

        if (bookingManager.getTotalBookings() == 0) {
            System.out.println("No booking history available!");
            return;
        }

        bookingManager.displayBookingHistory();
    }
}

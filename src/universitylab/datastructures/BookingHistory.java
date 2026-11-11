
package universitylab.datastructures;

import universitylab.model.Booking;

// Node Class
class BookingNode {
    Booking data;
    BookingNode next;

    BookingNode(Booking data) {
        this.data = data;
        this.next = null;
    }
}

// Booking History Class
public class BookingHistory {

    private BookingNode head;
    private BookingNode tail;
    private int count;

    public BookingHistory() {
        head = null;
        tail = null;
        count = 0;
    }

    // Add Booking
    public void addBooking(Booking booking) {
        BookingNode newNode = new BookingNode(booking);

        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }

        count++;
    }

    // Display All Bookings
    public void displayHistory() {
        if (head == null) {
            System.out.println("No booking history available.");
            return;
        }

        System.out.println("\n--- Booking History ---");

        BookingNode current = head;

        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }


    public void displayStudentHistory(String studentId) {

        BookingNode curr = head;
        boolean found = false;

        System.out.println("\n--- My Booking History ---");

        while (curr != null) {

            Booking booking = curr.data;

            if (booking.getRequesterId().equals(studentId)) {

                System.out.println("Booking ID: " + booking.getBookingId());
                System.out.println("Lab ID: " + booking.getLabId());
                System.out.println("Date: " + booking.getBookingDate());
                System.out.println("Time: " + booking.getStartTime()
                        + " - " + booking.getEndTime());
                System.out.println("Status: " + booking.getStatus());

                if (booking.getStatus().equalsIgnoreCase("Rejected")) {
                    System.out.println("Rejection Reason: " + booking.getRejectionReason());
                }

                System.out.println("-------------------------");

                found = true;
            }

            curr = curr.next;
        }

        if (!found) {
            System.out.println("No booking history found!");
        }
    }


    // Search Booking
    public Booking searchBooking(String bookingId) {
        BookingNode current = head;

        while (current != null) {
            if (current.data.getBookingId().equals(bookingId)) {
                return current.data;
            }

            current = current.next;
        }

        return null;
    }

    // Get Total Bookings
    public int getTotalBookings() {
        return count;
    }

    // Check Empty
    public boolean isEmpty() {
        return head == null;
    }

    public boolean hasConflict(String labId, String bookingDate,
                               String startTime, String endTime) {

        BookingNode current = head;

        while (current != null) {

            Booking b = current.data;

            if (b.getLabId().equals(labId)
                    && b.getBookingDate().equals(bookingDate)
                    && b.getStatus().equalsIgnoreCase("Confirmed")) {

                if (startTime.compareTo(b.getEndTime()) < 0
                        && b.getStartTime().compareTo(endTime) < 0) {

                    return true;
                }
            }

            current = current.next;
        }

        return false;
    }
}

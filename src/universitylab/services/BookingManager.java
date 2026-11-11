
package universitylab.services;

import universitylab.datastructures.BookingHistory;
import universitylab.datastructures.Queue;
import universitylab.dao.BookingDAO;
import universitylab.model.Booking;

public class BookingManager {

    private Queue pendingQueue;
    private BookingHistory bookingHistory;
    private BookingDAO bookingDAO;
    private int bookingCounter;

    public BookingManager() {

        pendingQueue = new Queue();
        bookingHistory = new BookingHistory();
        bookingDAO = new BookingDAO();

        bookingCounter = bookingDAO.getLastBookingNumber() + 1;
    }

    public String generateBookingId() {

        String id = "B" + String.format("%03d", bookingCounter);
        bookingCounter++;

        return id;
    }

    // Save booking in database and Queue
    public boolean submitBooking(Booking booking) {

        boolean saved = bookingDAO.insertBooking(booking);

        if (saved) {
            pendingQueue.enqueue(booking);
            return true;
        }

        return false;
    }

    // Load bookings from database
    public void loadBookings() {

        Booking[] bookings = bookingDAO.getAllBookings();

        for (int i = 0; i < bookings.length; i++) {

            if (bookings[i].getStatus().equals("Pending")) {
                pendingQueue.enqueue(bookings[i]);
            } else {
                bookingHistory.addBooking(bookings[i]);
            }
        }

    }

    public void displayPendingRequests() {
        pendingQueue.display();
    }

    public void displayStudentPendingBookings(String studentId) {
        pendingQueue.displayStudentPendingBookings(studentId);
    }

    public int getPendingCount() {
        return pendingQueue.getCount();
    }

    public Booking processNextRequest() {
        return pendingQueue.dequeue();
    }

    public Booking peekNextRequest() {
        return pendingQueue.peek();
    }

    public void displayBookingHistory() {
        bookingHistory.displayHistory();
    }

    public void displayStudentHistory(String studentId) {
        bookingHistory.displayStudentHistory(studentId);
    }

    public int getTotalBookings() {
        return bookingHistory.getTotalBookings();
    }

    public Booking searchBooking(String bookingId) {
        return bookingHistory.searchBooking(bookingId);
    }

    // Update database and Booking History
    public boolean completeBooking(Booking booking, String status) {

        if (booking == null) {
            return false;
        }

        boolean updated = bookingDAO.updateBookingStatus(
                booking.getBookingId(), status
        );

        if (updated) {
            booking.setStatus(status);
            bookingHistory.addBooking(booking);
            return true;
        }

        return false;
    }

    public boolean rejectBooking(Booking booking, String reason) {
        if (booking == null) {
            return false;
        }

        boolean updated = bookingDAO.updateRejectionReason(
                booking.getBookingId(), reason
        );

        if (updated) {
            booking.setStatus("Rejected");
            booking.setRejectionReason(reason);
            bookingHistory.addBooking(booking);
            return true;
        }

        return false;
    }

    public boolean hasBookingConflict(
            String labId,
            String bookingDate,
            String startTime,
            String endTime) {

        return bookingHistory.hasConflict(
                labId, bookingDate, startTime, endTime
        );
    }
}


package universitylab.services;

import universitylab.model.Booking;
import universitylab.model.Lab;

public class AvailabilityManager {

    private LabManager labManager;
    private BookingManager bookingManager;

    public AvailabilityManager(
            LabManager labManager,
            BookingManager bookingManager) {

        this.labManager = labManager;
        this.bookingManager = bookingManager;
    }

    public boolean isLabAvailable(
            String labId,
            String bookingDate,
            String startTime,
            String endTime) {

        Lab lab = labManager.searchLab(labId);

        if (lab == null) {
            System.out.println("Lab does not exist!");
            return false;
        }

        if (startTime.compareTo(endTime) >= 0) {
            System.out.println("Invalid booking time!");
            return false;
        }

        boolean conflict = bookingManager.hasBookingConflict(
                labId, bookingDate, startTime, endTime
        );

        if (conflict) {
            System.out.println("Lab is already booked for this time!");
            return false;
        }

        return true;
    }

    public boolean approveBooking(Booking booking) {

        if (booking == null) {
            System.out.println("No booking request found!");
            return false;
        }

        boolean available = isLabAvailable(
                booking.getLabId(),
                booking.getBookingDate(),
                booking.getStartTime(),
                booking.getEndTime()
        );

        String status;

        if (available) {
            status = "Confirmed";
        } else {
            status = "Rejected";
        }

        boolean completed = bookingManager.completeBooking(
                booking, status
        );

        if (completed) {

            if (available) {
                System.out.println("Booking Confirmed Successfully!");
            } else {
                System.out.println("Booking Rejected! No duplicate slots allowed.");
            }

            return true;
        }

        System.out.println("Database update failed! Please try again.");
        return false;
    }

    public boolean rejectBooking(Booking booking, String reason) {
        if (booking == null) {
            System.out.println("No booking request found!");
            return false;
        }

        boolean completed = bookingManager.rejectBooking(booking, reason);

        if (completed) {
            System.out.println("Booking rejected successfully!");
            System.out.println("Reason: " + reason);
            return true;
        }

        System.out.println("Database update failed! Please try again.");
        return false;
    }
}

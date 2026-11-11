
package universitylab.dao;

import universitylab.database.DBConnection;
import universitylab.model.Booking;

import java.sql.*;

public class BookingDAO {

    // Insert new booking
    public boolean insertBooking(Booking booking) {

        String query = "INSERT INTO bookings VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, booking.getBookingId());
            ps.setString(2, booking.getRequesterId());
            ps.setString(3, booking.getLabId());
            ps.setDate(4, Date.valueOf(booking.getBookingDate()));
            ps.setTime(5, Time.valueOf(booking.getStartTime() + ":00"));
            ps.setTime(6, Time.valueOf(booking.getEndTime() + ":00"));
            ps.setString(7, booking.getStatus());
            ps.setString(8, booking.getPurpose());
            ps.setString(9, booking.getRejectionReason());

            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Booking insert error: " + e.getMessage());
            return false;
        }
    }

    // Load all bookings

    public Booking[] getAllBookings() {

        String countQuery = "SELECT COUNT(*) FROM bookings";
        String query = "SELECT * FROM bookings ORDER BY booking_id";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement()) {

            ResultSet countRs = st.executeQuery(countQuery);
            countRs.next();

            int total = countRs.getInt(1);

            Booking[] bookings = new Booking[total];

            ResultSet rs = st.executeQuery(query);

            int i = 0;

            while (rs.next()) {

                Booking booking = new Booking(
                        rs.getString("booking_id"),
                        rs.getString("requester_id"),
                        rs.getString("lab_id"),
                        rs.getDate("booking_date").toString(),
                        rs.getTime("start_time").toString().substring(0, 5),
                        rs.getTime("end_time").toString().substring(0, 5),
                        rs.getString("status"),
                        rs.getString("purpose")
                );

                booking.setRejectionReason(rs.getString("rejection_reason"));
                bookings[i] = booking;
                i++;
            }

            return bookings;

        } catch (Exception e) {
            System.out.println("Booking load error: " + e.getMessage());
            return new Booking[0];
        }
    }

    public boolean updateRejectionReason(String bookingId, String reason) {
        String query = "UPDATE bookings SET status = ?, rejection_reason = ? WHERE booking_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, "Rejected");
            ps.setString(2, reason);
            ps.setString(3, bookingId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Rejection update error: " + e.getMessage());
            return false;
        }
    }


    // Update booking status
    public boolean updateBookingStatus(String bookingId, String status) {

        String query = "UPDATE bookings SET status = ? WHERE booking_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, status);
            ps.setString(2, bookingId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Booking update error: " + e.getMessage());
            return false;
        }
    }

    // Get last booking number
    public int getLastBookingNumber() {

        String query = "SELECT MAX(CAST(SUBSTRING(booking_id, 2) AS UNSIGNED)) FROM bookings";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            System.out.println("Booking ID error: " + e.getMessage());
        }

        return 0;
    }
}

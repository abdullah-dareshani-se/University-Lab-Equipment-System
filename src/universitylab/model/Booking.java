
package universitylab.model;

public class Booking {

    private String bookingId;
    private String requesterId;
    private String requesterType;
    private String labId;
    private String bookingDate;
    private String startTime;
    private String endTime;
    private String status;

    // Constructor
    public Booking(String bookingId, String requesterId,
                   String requesterType, String labId,
                   String bookingDate, String startTime,
                   String endTime, String status) {

        this.bookingId = bookingId;
        this.requesterId = requesterId;
        this.requesterType = requesterType;
        this.labId = labId;
        this.bookingDate = bookingDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    // Getters
    public String getBookingId() {
        return bookingId;
    }

    public String getRequesterId() {
        return requesterId;
    }

    public String getRequesterType() {
        return requesterType;
    }

    public String getLabId() {
        return labId;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getStatus() {
        return status;
    }

    // Setters
    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public void setRequesterId(String requesterId) {
        this.requesterId = requesterId;
    }

    public void setRequesterType(String requesterType) {
        this.requesterType = requesterType;
    }

    public void setLabId(String labId) {
        this.labId = labId;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // toString()
    @Override
    public String toString() {
        return "\nBooking ID: " + bookingId +
                "\nRequester ID: " + requesterId +
                "\nRequester Type: " + requesterType +
                "\nLab ID: " + labId +
                "\nBooking Date: " + bookingDate +
                "\nStart Time: " + startTime +
                "\nEnd Time: " + endTime +
                "\nStatus: " + status;
    }
}

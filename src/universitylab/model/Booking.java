
package universitylab.model;

public class Booking {

    private String bookingId;
    private String requesterId;
    private String labId;
    private String bookingDate;
    private String startTime;
    private String endTime;
    private String status;
    private String purpose;
    private String rejectionReason;

    public Booking(String bookingId, String requesterId,
                   String labId, String bookingDate,
                   String startTime, String endTime,
                   String status, String purpose) {

        this.bookingId = bookingId;
        this.requesterId = requesterId;
        this.labId = labId;
        this.bookingDate = bookingDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.purpose = purpose;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getRequesterId() {
        return requesterId;
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


    public String getPurpose() {
        return purpose;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public void setRequesterId(String requesterId) {
        this.requesterId = requesterId;
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

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    @Override
    public String toString() {
        return "\nBooking ID: " + bookingId +
                "\nRequester ID: " + requesterId +
                "\nLab ID: " + labId +
                "\nBooking Date: " + bookingDate +
                "\nStart Time: " + startTime +
                "\nEnd Time: " + endTime +
                "\nStatus: " + status +
                "\nPurpose: " + purpose +
                (rejectionReason != null
                        ? "\nRejection Reason: " + rejectionReason
                        : "");
    }
}

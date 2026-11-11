package universitylab.datastructures;

import universitylab.model.Booking;

// Node Class
class QueueNode {
    Booking data;
    QueueNode next;

    QueueNode(Booking data) {
        this.data = data;
        this.next = null;
    }
}

// Queue Class
public class Queue {

    QueueNode front;
    QueueNode rear;
    int count;

    public Queue() {
        front = null;
        rear = null;
        count = 0;
    }

    public void enqueue(Booking booking){
        QueueNode b = new QueueNode(booking);

        if(front == null){
            front = rear = b;
        } else{
            rear.next = b;
            rear = b;
        }

        count++;
    }

    public Booking dequeue(){
        if(isEmpty()){
            System.out.println("Pending list is empty, No requests");
            return null;
        }

        Booking b = front.data;
        front = front.next;
        count--;

        if (front == null) {
            rear = null;
        }
        return b;
    }

    public Booking peek(){
        if(isEmpty()){
            System.out.println("Pending list is empty, No requests");
            return null;
        }

        return front.data;
    }

    public boolean isEmpty(){
        return front == null;
    }


    public void display() {
        if (isEmpty()) {
            System.out.println("Pending list is empty, No requests");
            return;
        }

        System.out.println("Pending Booking Requests:");

        QueueNode c = front;

        while (c != null) {
            System.out.println(c.data);
            c = c.next;
        }
    }


    public void displayStudentPendingBookings(String studentId) {

        QueueNode curr = front;
        boolean found = false;

        System.out.println("\n--- My Pending Bookings ---");

        while (curr != null) {

            Booking booking = curr.data;

            if (booking.getRequesterId().equals(studentId)) {

                System.out.println("Booking ID: " + booking.getBookingId());
                System.out.println("Lab ID: " + booking.getLabId());
                System.out.println("Date: " + booking.getBookingDate());
                System.out.println("Time: " + booking.getStartTime()
                        + " - " + booking.getEndTime());
                System.out.println("Status: " + booking.getStatus());
                System.out.println("-------------------------");

                found = true;
            }

            curr = curr.next;
        }

        if (!found) {
            System.out.println("No pending bookings found!");
        }
    }


    public int getCount(){
        return count;
    }
}




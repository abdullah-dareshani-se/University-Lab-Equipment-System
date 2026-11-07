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


    public void display(){
        if(isEmpty()){
            System.out.println("Pending list is empty, No requests");
            return;
        }

        QueueNode  c = front;
        while(c!=null){
            System.out.println("Pending Booking Requests:");
            System.out.println(c.data + " ");
            c = c.next;
        }
    }

    public int getCount(){
        return count;
    }
}




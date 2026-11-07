package universitylab.datastructures;
import universitylab.model.Lab;

class Node{
    Lab data;
    Node next;

    Node (Lab data){
        this.data = data;
        this.next = null;
    }
}

public class LinkedList {

    Node head, tail;
    int count;

    public LinkedList(){
        head = null;
        tail = null;
        count = 0;
    }

    public void addLab(Lab lab){
        Node n = new Node(lab);

        if(head == null){
            head = tail = n;
        }
            else {
                tail.next = n;
                tail = n;
            }

        count++;

    }

    public void deleteLab(String labId) {

        Node current = head;
        Node previous = null;
        while (current != null &&
                !current.data.getLabId().equals(labId)) {

            previous = current;
            current = current.next;
        }

        if (current == null) {
            System.out.println("Lab not found!");
            return;
        }


        if (previous == null) {
            head = current.next;
        }

        else {
            previous.next = current.next;
        }
        if (current == tail) {
            tail = previous;
        }
        count--;

        System.out.println("Lab deleted successfully!");
    }

    public void searchLab(String labId) {

        if (head == null) {
            System.out.println("List is empty");
            return;
        }

        Node c = head;

        while (c != null) {

            if (c.data.getLabId().equals(labId)) {
                System.out.println("Lab Found: " + c.data);
                return;
            }

            c = c.next;
        }

        System.out.println("Lab not found!");
    }

    public void display() {

        if (head == null) {
            System.out.println("No labs available!");
            return;
        }

        Node current = head;

        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }


    public boolean isEmpty(){
        return head == null;
    }

    public int getCount(){
        return count;
    }

}

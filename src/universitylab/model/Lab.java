package universitylab.model;

public class Lab {

    private String labId;
    private String labName;
    private String location;
    private int capacity;

    public Lab(String labId, String labName, String location, int capacity){
        this.labId = labId;
        this.labName = labName;
        this.location = location;
        this.capacity = capacity;
    }

    public void setLabId(String labId){
        this.labId = labId;
    }

    public void setLabName(String labName){
        this.labName = labName;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public void setCapacity(int capacity){
        this.capacity = capacity;
    }

    public String getLabId(){
        return labId;
    }

    public String getLabName(){
        return labName;
    }
    public String getLocation(){
        return location;
    }

    public int getCapacity(){
        return capacity;
    }

    public String toString(){
        return "\n Lab Id : " + labId +
                "\n Lab Name : " + labName +
                "\n Lab Location : " + location +
                "\n Lab Capacity : " + capacity;
    }
}




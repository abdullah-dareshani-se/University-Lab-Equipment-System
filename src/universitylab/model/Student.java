package universitylab.model;

public class Student {

    private String studentName;
    private String studentId;
    private String dept;

    public Student(String studentName, String studentId, String dept){
        this.studentName = studentName;
        this.studentId = studentId;
        this.dept = dept;
    }

    public void setStudentName(String studentName){
        this.studentName = studentName;
    }

    public String getStudentNameName(){
        return studentName;
    }

    public void setStudentId(String studentId){
        this.studentId = studentId;
    }

    public String getStudentId(){
        return studentId;
    }

    public void setStudentDept(String dept){
        this.dept = dept;
    }

    public String getStudentNameDept(){
        return dept;
    }

    public String toString(){
        return "\n Student Id : " + studentId +
                "\n Student Name : " + studentName +
                "\n Department : " + dept;
    }
}

package universitylab.model;

public class Student {
    
    private String studentName;
    private String studentId;
    private String dept;
    private String password;

    public Student(String studentName, String studentId, String dept, String password){
        this.studentName = studentName;
        this.studentId = studentId;
        this.dept = dept;
        this.password = password;
    }

    public void setStudentName(String studentName){
        this.studentName = studentName;
    }

    public String getStudentName(){
        return studentName;
    }

    public void setStudentId(String studentId){
        this.studentId = studentId;
    }

    public String getStudentId(){
        return studentId;
    }

    public void setDept(String dept){
        this.dept = dept;
    }

    public String getDept(){
        return dept;
    }

    public void setPassword(String password) { this.password = password ; }

    public String getPassword() { return password; }

    @Override
    public String toString(){
        return "\n Student Id : " + studentId +
                "\n Student Name : " + studentName +
                "\n Department : " + dept;
    }
}

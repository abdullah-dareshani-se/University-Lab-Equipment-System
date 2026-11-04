package universitylab.model;

public class Teacher {
    private String teacherName;
    private String teacherId;
    private String dept;

    public Teacher(String teacherId, String teacherName, String dept){
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.dept = dept;
    }

    public void setTeacherId(String teacherId){
        this.teacherId = teacherId;
    }

    public String getTeacherId(){
        return teacherId;
    }

    public void setTeacherName(String teacherName){
        this.teacherName = teacherName;
    }

    public String getTeacherName(){
        return teacherName;
    }

    public void setDept(String dept){
        this.dept = dept;
    }

    public String getDept(){
        return dept;
    }

    @Override
    public String toString(){
        return "\n Teacher Name : " + teacherName +
        "\n Teacher Id : "  + teacherId +
        "\n Teacher department : " + dept;
    }



}

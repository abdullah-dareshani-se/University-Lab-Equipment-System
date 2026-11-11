
package universitylab.dao;

import universitylab.model.Student;
import universitylab.database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentDAO {

    public void insertStudent(Student student) throws Exception {

        String query = "INSERT INTO students VALUES (?, ?, ?, ?)";

        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, student.getStudentId());
        ps.setString(2, student.getStudentName());
        ps.setString(3, student.getDept());
        ps.setString(4, student.getPassword());

        ps.executeUpdate();

        ps.close();
        con.close();
    }

    public Student[] getAllStudents() throws Exception {

        Connection con = DBConnection.getConnection();

        String countQuery = "SELECT COUNT(*) FROM students";
        PreparedStatement countStatement =
                con.prepareStatement(countQuery);

        ResultSet countResult = countStatement.executeQuery();

        countResult.next();
        int total = countResult.getInt(1);

        Student[] students = new Student[total];

        String query = "SELECT * FROM students";
        PreparedStatement ps = con.prepareStatement(query);

        ResultSet rs = ps.executeQuery();

        int i = 0;

        while (rs.next()) {

            students[i] = new Student(
                    rs.getString("student_name"),
                    rs.getString("student_id"),
                    rs.getString("department"),
                    rs.getString("password")
            );

            i++;
        }

        rs.close();
        ps.close();
        countResult.close();
        countStatement.close();
        con.close();

        return students;
    }
}

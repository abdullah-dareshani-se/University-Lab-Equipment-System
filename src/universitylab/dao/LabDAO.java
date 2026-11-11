
package universitylab.dao;

import universitylab.model.Lab;
import universitylab.database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LabDAO {

    // Insert Lab
    public void insertLab(Lab lab) throws Exception {

        String query = "INSERT INTO labs VALUES (?, ?, ?)";

        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, lab.getLabId());
        ps.setString(2, lab.getLabName());
        ps.setString(3, lab.getLocation());

        ps.executeUpdate();

        ps.close();
        con.close();
    }

    // Get All Labs
    public Lab[] getAllLabs() throws Exception {

        Connection con = DBConnection.getConnection();

        String countQuery = "SELECT COUNT(*) FROM labs";
        PreparedStatement countStatement =
                con.prepareStatement(countQuery);

        ResultSet countResult = countStatement.executeQuery();
        countResult.next();

        int total = countResult.getInt(1);

        Lab[] labs = new Lab[total];

        String query = "SELECT * FROM labs";
        PreparedStatement ps = con.prepareStatement(query);

        ResultSet rs = ps.executeQuery();

        int i = 0;

        while (rs.next()) {

            labs[i] = new Lab(
                    rs.getString("lab_id"),
                    rs.getString("lab_name"),
                    rs.getString("location")
            );

            i++;
        }

        rs.close();
        ps.close();
        countResult.close();
        countStatement.close();
        con.close();

        return labs;
    }

    // Update Lab
    public void updateLab(Lab lab) throws Exception {

        String query = "UPDATE labs SET lab_name = ?, location = ? WHERE lab_id = ?";

        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, lab.getLabName());
        ps.setString(2, lab.getLocation());
        ps.setString(3, lab.getLabId());

        ps.executeUpdate();

        ps.close();
        con.close();
    }

    // Delete Lab
    public void deleteLab(String labId) throws Exception {

        String query = "DELETE FROM labs WHERE lab_id = ?";

        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, labId);

        ps.executeUpdate();

        ps.close();
        con.close();
    }
}

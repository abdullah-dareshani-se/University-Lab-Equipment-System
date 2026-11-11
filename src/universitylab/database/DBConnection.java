
package universitylab.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/university_lab_booking";

    private static final String USER = "root";

    private static final String PASSWORD = "Password";

    public static Connection getConnection()
            throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        return DriverManager.getConnection(
                URL, USER, PASSWORD
        );
    }
}

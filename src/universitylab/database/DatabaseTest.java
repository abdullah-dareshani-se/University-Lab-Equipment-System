
package universitylab.database;

import java.sql.Connection;

public class DatabaseTest {

    public static void main(String[] args) {

        try {
            Connection connection = DBConnection.getConnection();

            if (connection != null) {
                System.out.println("Database Connected Successfully!");

                connection.close();
            }

        } catch (Exception e) {
            System.out.println("Database Connection Failed!");
            System.out.println(e.getMessage());
        }
    }
}

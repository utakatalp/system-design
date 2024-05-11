import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static javax.management.remote.JMXConnectorFactory.connect;

public class DatabaseConnection {
    private String url = "jdbc:postgresql://host:5432/postgres";
    private String user = "postgres";
    private String password = "1234";

    public Connection connect2() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

}

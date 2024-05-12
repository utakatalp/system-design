import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectionAttempt {

    private String username="root";
    private String password="adem4849";
    private String url ="jdbc:mysql//localhost:3306/database";
    private static String driver = "com.mysql.jdbc.Driver";

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    protected Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;

    }
}

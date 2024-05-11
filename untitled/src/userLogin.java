import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class userLogin {
    User user;
    public User authenticateUser(String username, String password)
    {
        String SQL = "SELECT * FROM users WHERE apartmentNumber = ? AND password = ?";
        try(Connection conn = new DatabaseConnection().connect2();
            PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setString(1,username);
            pstmt.setString(2,password);

            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                user = new User(rs.getString("apartmentNumber"),rs.getString("name"), rs.getString("password"), rs.getFloat("balance"));
                return user;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}

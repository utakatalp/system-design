import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class adminLogin {
    Admin admin;
    public Admin authenticateUser(String username, String password)
    {
        String SQL = "SELECT * FROM admins WHERE username = ? AND password = ?";
        try(Connection conn = new DatabaseConnection().connect2();
            PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setString(1,username);
            pstmt.setString(2,password);

            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                admin = new Admin(rs.getString("username"),rs.getString("password"));
                return admin;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class userSignup {


    public boolean registerUser(String aprtNumber,String name, String password){
        String SQL = "INSERT INTO users(apartmentNumber,name, password) VALUES(?, ?, ?)";
        try (Connection conn = new DatabaseConnection().connect2();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, aprtNumber);
            pstmt.setString(2, name);
            pstmt.setString(3, password);



            int affectedRows = pstmt.executeUpdate();

            if(affectedRows > 0){
                System.out.println("A new record was inserted succesfully");
            }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}

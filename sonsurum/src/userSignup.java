import java.sql.*;
import java.time.LocalDate;
import java.time.Month;

public class userSignup {

    Connection conn;
    public boolean registerUser(String aprtNumber,String name, String password){
        int generateID=-1;
        String SQL = "INSERT INTO users(apartmentNumber,name, password, balance) VALUES(?, ?, ?, 0)";
        try (Connection conn = new DatabaseConnection().connect2();
             PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, aprtNumber);
            pstmt.setString(2, name);
            pstmt.setString(3, password);

            int affectedRows = pstmt.executeUpdate();

            if(affectedRows > 0) {
                System.out.println("A new record was inserted succesfully");
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        generateID = generatedKeys.getInt(1);
                    } else {
                        System.out.println("No ID obtained.");
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        LocalDate currentDate = LocalDate.now();  // Şu anki tarihi al
        Month currentMonth = currentDate.getMonth();  // Ay bilgisini al

        SQL ="SELECT * FROM dueLog WHERE month = ?";
        try(Connection conn = new DatabaseConnection().connect2();
            PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setInt(1,currentMonth.getValue());

            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                createDue due = new createDue(generateID, rs.getFloat("due"), currentMonth.getValue());
                due.createDebtRecord();
                return true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        return false;
    }
}

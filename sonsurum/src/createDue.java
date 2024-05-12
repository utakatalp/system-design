import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class createDue {
    private int userID;
    private double fee;
    private int month;
    public createDue(int userID, double fee, int month) throws SQLException {
        this.userID=userID;
        this.fee=fee;
        this.month=month;
    }
    public void createDebtRecord() {
        String SQL = "INSERT INTO debts(userid, fee, month) VALUES(?, ?, ?)";
        try (Connection conn = new DatabaseConnection().connect2();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setInt(1, this.userID);
            pstmt.setDouble(2, this.fee);
            pstmt.setInt(3, this.month);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
//                System.out.println("Debt record inserted successfully.");

            }

        } catch (SQLException e) {
            System.out.println("SQL error occurred: " + e.getMessage());

        }
    }





}


import java.sql.*;

public class setDebt {
    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    private double fee;
    public void setDebtforAllUsers() throws SQLException {
        String SQL = "SELECT * FROM users";
        try(Connection conn = new DatabaseConnection().connect2();
            PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("userid");
                createDue due = new createDue(id,fee,7);
                due.createDebtRecord();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        SQL = "INSERT INTO dueLog(due, month) VALUES(?, ?)";
        try(Connection conn = new DatabaseConnection().connect2();
            PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setDouble(1,fee);
            pstmt.setInt(2,7);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("The Debts are updated for all residents.");

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

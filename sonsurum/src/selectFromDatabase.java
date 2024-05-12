import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class selectFromDatabase {
    private int ID;
    public selectFromDatabase(int ID)
    {

        this.ID=ID;
    }
    public ArrayList<Debt> debtListFunc(){
        String SQL = "SELECT * FROM debts WHERE userid = ?";
        ArrayList<Debt> debts = new ArrayList<>();
        try(Connection conn = new DatabaseConnection().connect2();
            PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setInt(1,ID);

            ResultSet rs= pstmt.executeQuery();
            while(rs.next())
            {
                int monthLine = rs.getInt("month");
                double monthFee = rs.getFloat("fee");
                int debtID = rs.getInt("debtid");
                Debt debt = new Debt(monthFee,debtID,monthLine);
                debts.add(debt);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return debts;
    }
}

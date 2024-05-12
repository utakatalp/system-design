import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class scanningAllDebtsUI extends JFrame{
    private JList debtsList;
    private JPanel mainPanel;
    private JScrollPane scrollpane;
    private DefaultListModel<String> listModel;

    public scanningAllDebtsUI(){
        setContentPane(mainPanel);
        setTitle("Simple GUI App");
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,300);
        setLocationRelativeTo(null);
        setVisible(true);

        debtsList.setModel(scanDebtByID());
        scrollpane.setViewportView(debtsList);

    }
    private DefaultListModel<String> scanDebtByID()
    {
        listModel = new DefaultListModel<>();
        String SQL = "SELECT * FROM users";
        try(Connection conn = new DatabaseConnection().connect2();
            PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("userid");
                String SQL2 = "SELECT * FROM debts WHERE userid = ?";
                try(PreparedStatement pstmtDebts = conn.prepareStatement(SQL2)){
                    pstmtDebts.setInt(1,id);
                    ResultSet rsDebts = pstmtDebts.executeQuery();
                    while(rsDebts.next())
                    {
                        listModel.addElement(rs.getString("name") + "'in " + rsDebts.getInt("month") +". ay için borcu: " +rsDebts.getFloat("fee") +" TL" );
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listModel;
    }

}

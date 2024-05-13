import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class dispComplaintsUI extends JFrame{
    private JPanel mainPanel;
    private JList complaintList;
    private ArrayList<String> complaints = new ArrayList<>();
    private DefaultListModel<String> listModel;

    public dispComplaintsUI() throws SQLException {
        setContentPane(mainPanel);
        setTitle("Toplu Konut Yönetim Sistemi");
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 900);
        setLocationRelativeTo(null);
        setVisible(true);
        showAllComplaints();
    }

    public void showAllComplaints() throws SQLException {
        listModel = new DefaultListModel<>();

        Connection conn = new DatabaseConnection().connect2();

        Statement stmt = null;
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ResultSet rs1;
        try {
            rs1 = stmt.executeQuery("SELECT * FROM complaints");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        while(rs1.next())
        {
            listModel.addElement(rs1.getString("complaint"));
        }
        complaintList.setModel(listModel);
    }
}

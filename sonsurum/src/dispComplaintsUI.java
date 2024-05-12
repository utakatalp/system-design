import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class dispComplaintsUI extends JFrame{
    private JPanel mainPanel;
    private JList complaintList;
    private ArrayList<String> complaints = new ArrayList<>();
    private DefaultListModel<String> listModel;

    public dispComplaintsUI() {
        setContentPane(mainPanel);
        setTitle("Toplu Konut Yönetim Sistemi");
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 900);
        setLocationRelativeTo(null);
        setVisible(true);
        showAllComplaints();
    }

    public void showAllComplaints() {
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
            rs1 = stmt.executeQuery("SELECT COUNT(*) FROM complaints");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        int k = 0;

        try {
            if (rs1.next()) {
                k = rs1.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String SQL = "SELECT * FROM complaints WHERE messageid = ?";
        for (int i = 1; i < k+1; i++) {
            try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
                pstmt.setInt(1, i);

                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    listModel.addElement(rs.getString("complaint"));
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        complaintList.setModel(listModel);
    }
}

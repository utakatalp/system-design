import javax.swing.*;
import java.sql.*;

public class dispSuggestionsUI extends JFrame{
    private JList sugggestionsList;
    private JPanel mainPanel;
    private DefaultListModel<String> listModel1;
    public dispSuggestionsUI(){
        setContentPane(mainPanel);
        setTitle("Toplu Konut Yönetim Sistemi");
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,900);
        setLocationRelativeTo(null);
        setVisible(true);
        listModel1 = listingSuggestions();
        sugggestionsList.setModel(listModel1);

    }
    private DefaultListModel<String> listingSuggestions(){
        DefaultListModel<String> listModel = new DefaultListModel<>();
        Connection conn = new DatabaseConnection().connect2();

        Statement stmt = null;
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ResultSet rs1;
        try {
            rs1 = stmt.executeQuery("SELECT COUNT(*) FROM suggestions");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        int boundList = 0;

        try {
            if (rs1.next()) {
                boundList = rs1.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String SQL = "SELECT * FROM suggestions WHERE messageid = ?";
        for (int i = 1; i <= boundList; i++) {
            try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {

                pstmt.setInt(1, i);

                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    listModel.addElement(rs.getString("suggestion"));

                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return listModel;
    }
}


import javax.swing.*;
import java.sql.*;

public class dispSuggestionsUI extends JFrame{
    private JList sugggestionsList;
    private JPanel mainPanel;
    private DefaultListModel<String> listModel1;
    public dispSuggestionsUI() throws SQLException {
        setContentPane(mainPanel);
        setTitle("Toplu Konut Yönetim Sistemi");
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,900);
        setLocationRelativeTo(null);
        setVisible(true);
        listModel1 = listingSuggestions();
        sugggestionsList.setModel(listModel1);

    }
    private DefaultListModel<String> listingSuggestions() throws SQLException {
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
            rs1 = stmt.executeQuery("SELECT * FROM suggestions");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        while (rs1.next())
        {
            listModel.addElement(rs1.getString("suggestion"));
        }
        return listModel;
    }
}


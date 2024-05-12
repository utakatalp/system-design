import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class updateDueUI extends JFrame{
    private JPanel mainPanel;
    private JTextField dueField;
    private JButton güncelleButton;
    updateDueUI(){
        setContentPane(mainPanel);
        setTitle("Toplu Konut Yönetim Sistemi");
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,300);
        setLocationRelativeTo(null);
        setVisible(true);
        güncelleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    setDebtforAllUsers(Double.parseDouble(dueField.getText()));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    public void setDebtforAllUsers(double fee) throws SQLException {
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

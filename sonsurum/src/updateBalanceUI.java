import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class updateBalanceUI extends JFrame{
    private JButton balanceButton;
    private JPanel mainPanel;
    private JTextField balanceField;
    private User user;
    public updateBalanceUI(User user){
        this.user=user;
        setContentPane(mainPanel);
        setTitle("Simple GUI App");
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,300);
        setLocationRelativeTo(null);
        setVisible(true);
        balanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBalance(Double.parseDouble(balanceField.getText()));
            }
        });
    }

    public void addBalance(double amount) {
        String SQL = "UPDATE users SET balance = balance + ? WHERE userid = ?";
        user.setBalance(user.getBalance() + amount);
        try (Connection conn = new DatabaseConnection().connect2();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setDouble(1, amount);
            pstmt.setInt(2, user.getID());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Bakiye başarıyla güncellendi.");
                JOptionPane.showMessageDialog(this,"Bakiye başarıyla yüklendi.","Bakiye",JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                System.out.println("Bakiye güncellenirken bir hata oluştu veya kullanıcı bulunamadı.");
                JOptionPane.showMessageDialog(this,"Bakiye yüklenirken bir hata oluştu veya kullanıcı bulunamadı.","Bakiye",JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            System.out.println("SQL Hatası: " + ex.getMessage());
        }
    }
}

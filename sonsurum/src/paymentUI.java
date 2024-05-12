import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.cert.Extension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class paymentUI extends JFrame{

    private JComboBox dueComboBox;
    private JPanel mainPanel;
    private JButton payButton;
    private JLabel balanceLabel;
    private selectFromDatabase sfd;
    private Debt selectedDebt;
    private User user;
    public paymentUI(User user) throws SQLException {
        this.user=user;
        setContentPane(mainPanel);
        setTitle("Simple GUI App");
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,300);
        setLocationRelativeTo(null);
        setVisible(true);
        balanceLabel.setText("Bakiye: " + Double.toString(user.getBalance())+ " TL");
        addingItemsToComboBox();

        dueComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedDebt=null;
                if(!(dueComboBox.getSelectedItem().equals("Aidat seçin.")))
                {
                    selectedDebt = (Debt) dueComboBox.getSelectedItem();
                }

            }
        });
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(selectedDebt != null)
                {
                    if(user.getBalance()>=selectedDebt.getFee())
                    {
                        payingViaComboBox();
                    }
                    else {
                        JOptionPane.showMessageDialog(paymentUI.this,
                                "Yetersiz bakiye.",
                                "Ödeme",
                                JOptionPane.ERROR_MESSAGE);
                        dispose();
                    }
                }
            }
        });
    }
    public void payingViaComboBox(){
        user.setBalance(user.getBalance()-selectedDebt.getFee());
        updateBalance();
        deleteDebtfromDB();
        dispose();
        JOptionPane.showMessageDialog(paymentUI.this,
                "Ödeme başarıyla yapıldı.",
                "Ödeme",
                JOptionPane.INFORMATION_MESSAGE);

        dispose();
        try {
            new paymentUI(user);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } // Giriş başarılıysa pencereyi kapat
    }
    public void updateBalance() {
        String SQL = "UPDATE users SET balance = ? WHERE userid = ?";
        try (Connection conn = new DatabaseConnection().connect2();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setDouble(1,user.getBalance());
            pstmt.setInt(2,user.getID());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("An existing user's balance was updated successfully.");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void addingItemsToComboBox(){
        sfd = new selectFromDatabase(user.getID());
        ArrayList<Debt> debts = sfd.debtListFunc();
        dueComboBox.addItem("Aidat seçin.");
        for(Debt debt : debts)
        {
            dueComboBox.addItem(debt);
        }
    }
    public void deleteDebtfromDB(){
        String SQL = "DELETE FROM debts WHERE debtid = ?";
        try (Connection conn = new DatabaseConnection().connect2();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setInt(1, selectedDebt.getId());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("A debt was deleted successfully.");
            } else {
                System.out.println("No debt was found with the given user ID.");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

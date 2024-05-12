import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class removeResidentUI extends JFrame{
    private JTextField apartmentNumber;
    private JButton removeButton;
    private JPanel mainPanel;
    //asdds
    public removeResidentUI(){
        setContentPane(mainPanel);
        setTitle("Toplu Konut Yönetim Sistemi");
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,300);
        setLocationRelativeTo(null);
        setVisible(true);
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeResident();
            }
        });
    }

    public void removeResident() {
        Connection conn = new DatabaseConnection().connect2();

        String sql = "DELETE FROM users WHERE apartmentnumber = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1,apartmentNumber.getText());

            try {
                int affectedRowCount = pstmt.executeUpdate();
                if (affectedRowCount > 0) {
                    System.out.println("Site sakini başarıyla silindi.");
                    JOptionPane.showMessageDialog(this,"Kişi başarıyla silindi.","Yönetim",JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    System.out.println("Site sakini silinemedi.");
                }
            }
            catch (SQLException e2){
                System.out.println("Kişinin hala borcu var.");
                JOptionPane.showMessageDialog(this,"Kişinin hala borcu olduğu için silinemiyor.","Yönetim",JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }



        } catch (SQLException e2) {
            e2.printStackTrace();
        }
    }
}

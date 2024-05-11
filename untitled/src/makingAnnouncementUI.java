import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class makingAnnouncementUI extends JFrame{
    private JPanel mainPanel;
    private JTextField anounceField;
    private JButton makeAnnouncementButton;
    makingAnnouncementUI(){
        setContentPane(mainPanel);
        setTitle("Simple GUI App");
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,300);
        setLocationRelativeTo(null);
        setVisible(true);
        makeAnnouncementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection conn = new DatabaseConnection().connect2();

                String sql = "INSERT INTO announcements(announcement) VALUES(?)";

                try {
                    PreparedStatement pstmt = conn.prepareStatement(sql);

                    pstmt.setString(1,anounceField.getText());

                    int etkilenenSatirSayisi = pstmt.executeUpdate();

                    if (etkilenenSatirSayisi > 0) {
                        System.out.println("Duyuru başarıyla eklendi.");
                    } else {
                        System.out.println("Duyuru eklenemedi.");
                    }

                } catch (SQLException e2) {
                    e2.printStackTrace();
                }

            }
        });
    }
}

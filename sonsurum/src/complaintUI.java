import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class complaintUI extends JFrame{
    private JTextField complaintTextField;
    private JButton sendButton;
    private JLabel complaintLabel;
    private JPanel mainPanel;

    public complaintUI(){
        setContentPane(mainPanel);
        setTitle("Simple GUI App");
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,300);
        setLocationRelativeTo(null);
        setVisible(true);

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Connection conn = new DatabaseConnection().connect2();

                String sql = "INSERT INTO complaints(complaint) VALUES(?)";

                try {
                    PreparedStatement pstmt = conn.prepareStatement(sql);

                    pstmt.setString(1,complaintTextField.getText());

                    int etkilenenSatirSayisi = pstmt.executeUpdate();

                    if (etkilenenSatirSayisi > 0) {
                        System.out.println("Şikayet başarıyla eklendi.");
                    } else {
                        System.out.println("Şikayet eklenemedi.");
                    }

                } catch (SQLException e2) {
                    e2.printStackTrace();
                }

            }
        });

    }
}

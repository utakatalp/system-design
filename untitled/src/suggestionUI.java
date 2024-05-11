import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class suggestionUI extends JFrame{
    private JPanel mainPanel;
    private JTextField suggestionTextField;
    private JButton sendButton;
    suggestionUI(){
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

                String sql = "INSERT INTO suggestions(suggestion) VALUES(?)";

                try {
                    PreparedStatement pstmt = conn.prepareStatement(sql);

                    pstmt.setString(1,suggestionTextField.getText());

                    int etkilenenSatirSayisi = pstmt.executeUpdate();

                    if (etkilenenSatirSayisi > 0) {
                        System.out.println("Öneri başarıyla eklendi.");
                    } else {
                        System.out.println("Öneri eklenemedi.");
                    }

                } catch (SQLException e2) {
                    e2.printStackTrace();
                }

            }
        });
    }
}

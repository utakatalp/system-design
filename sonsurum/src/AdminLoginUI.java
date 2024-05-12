import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class AdminLoginUI extends JFrame{
    private JTextField usernameField;
    private JPasswordField pwField;
    private JButton girişYapButton;
    private JPanel mainPanel;

    AdminLoginUI(){
        setContentPane(mainPanel);
        setTitle("Toplu Konut Yönetim Sistemi");
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,300);
        setLocationRelativeTo(null);
        setVisible(true);

        girişYapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Admin admin = new adminLogin().authenticateUser(usernameField.getText(), Arrays.toString(pwField.getPassword()));
                if (admin != null) {
                    JOptionPane.showMessageDialog(AdminLoginUI.this,
                            "Hoşgeldiniz, " + admin.getUsername(),
                            "Giriş Başarılı",
                            JOptionPane.INFORMATION_MESSAGE);
                    new AdministratorUI();
                    dispose(); // Giriş başarılıysa pencereyi kapat
                } else {
                    JOptionPane.showMessageDialog(AdminLoginUI.this,
                            "Giriş bilgileri hatalı. Lütfen tekrar deneyin.",
                            "Giriş Başarısız",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }
}

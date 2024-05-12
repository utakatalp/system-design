import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class ResidentLoginUI extends JFrame {
    private JLabel js1;
    private JTextField textField1;
    private User user;

    public JLabel getJs1() {
        return js1;
    }

    public void setJs1(JLabel js1) {
        this.js1 = js1;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public void setTextField1(JTextField textField1) {
        this.textField1 = textField1;
    }

    public JPanel getMainPanel() {
        return MainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        MainPanel = mainPanel;
    }

    public JPanel getPanel2() {
        return Panel2;
    }

    public void setPanel2(JPanel panel2) {
        Panel2 = panel2;
    }

    public JButton getGirişYapButton() {
        return loginButton;
    }

    public void setGirişYapButton(JButton girişYapButton) {
        this.loginButton = girişYapButton;
    }

    public JButton getBasButton() {
        return basButton;
    }

    public void setBasButton(JButton basButton) {
        this.basButton = basButton;
    }

    private JButton basButton;
    private JPanel MainPanel;
    private Registration registration;
    private JPanel Panel2;

    private JButton loginButton;
    private JPasswordField passwordField1;

    public ResidentLoginUI(){
        setContentPane(MainPanel);
        setTitle("Toplu Konut Yönetim Sistemi");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,300);
        setLocationRelativeTo(null);
        setVisible(true);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayUserLoginScreen();
            }
        });
    }
    public void displayUserLoginScreen() {
        User user = new userLogin().authenticateUser(textField1.getText(), Arrays.toString(passwordField1.getPassword()));
        if (user != null) {
            JOptionPane.showMessageDialog(ResidentLoginUI.this,
                    "Hoşgeldiniz, " + user.getName(),
                    "Giriş Başarılı",
                    JOptionPane.INFORMATION_MESSAGE);
            new ApartmentResidentUI(user);
            dispose(); // Giriş başarılıysa pencereyi kapat
        } else {
            JOptionPane.showMessageDialog(ResidentLoginUI.this,
                    "Giriş bilgileri hatalı. Lütfen tekrar deneyin.",
                    "Giriş Başarısız",
                    JOptionPane.ERROR_MESSAGE);
        }
        dispose();
    }
    public static void main(String[] args) {
        new ResidentLoginUI();
            }
}
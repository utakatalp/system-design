import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class Registration extends JFrame{
    private JTextField userName;
    private JTextField name;
    private JPasswordField password;
    private JPanel registration;
    private JButton registrationButton;

    public Container getMainPanel() {
        return registration;
    }

    public interface UserReadyListener {
        void onUserReady(User user);
    }
    private UserReadyListener listener;
    private User user;
    public Registration(){
        setContentPane(registration);
        setTitle("Toplu Konut Yönetim Sistemi");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,500);
        setLocationRelativeTo(null);
        setVisible(true);
        registrationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(usernameExists(userName.getText()))
                {
                    userSignup asd = new userSignup();
                    asd.registerUser(userName.getText(),name.getText(), Arrays.toString(password.getPassword()));
                    JOptionPane.showMessageDialog(Registration.this,"Kayıt başarılı.","Kayıt",JOptionPane.INFORMATION_MESSAGE);

                }
                else {
                    JOptionPane.showMessageDialog(Registration.this,"Bu kullanıcı adı zaten kullanılıyor.","Kayıt",JOptionPane.INFORMATION_MESSAGE);
                }

                dispose();
            }
        });
    }
    public boolean usernameExists(String username) {
        String SQL = "SELECT username FROM users WHERE username = ?";
        try (Connection conn = new DatabaseConnection().connect2();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return true; // Kullanıcı adı zaten var
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false; // Kullanıcı adı kullanılabilir

    }
    public User getUser()
    {
        return user;
    }

}


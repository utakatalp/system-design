import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        setTitle("Simple GUI App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,500);
        setLocationRelativeTo(null);
        setVisible(true);
        registrationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userSignup asd = new userSignup();
                asd.registerUser(userName.getText(),name.getText(), Arrays.toString(password.getPassword()));
                dispose();
            }
        });
    }
    public User getUser() {
        return user;
    }
}


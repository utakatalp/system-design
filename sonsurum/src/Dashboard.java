import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame{
    private JPanel mainPanel;
    private JButton yöneticiButton;
    private JButton kullanıcıButton;
    Dashboard(){
        setContentPane(mainPanel);
        setTitle("Simple GUI App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,300);
        setLocationRelativeTo(null);
        setVisible(true);
        yöneticiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminLoginUI();
                dispose();
            }
        });
        kullanıcıButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ResidentLoginUI();
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        new Dashboard();
    }
}

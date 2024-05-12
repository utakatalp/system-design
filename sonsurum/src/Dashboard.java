import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//asdadsasdsddas
public class Dashboard extends JFrame{
    private JPanel mainPanel;
    private JButton adminButton;
    private JButton residentButton;
    Dashboard(){
        setContentPane(mainPanel);
        setTitle("Toplu Konut Yönetim Sistemi");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,300);
        setLocationRelativeTo(null);
        setVisible(true);
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminLoginUI();
                dispose();
            }
        });
        residentButton.addActionListener(new ActionListener() {
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

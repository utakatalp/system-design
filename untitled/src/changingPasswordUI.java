import javax.swing.*;

public class changingPasswordUI extends JFrame{
    private JPanel mainPanel;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JPasswordField passwordField3;
    private JButton button1;
    changingPasswordUI()
    {
        setContentPane(mainPanel);
        setTitle("Simple GUI App");
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,300);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

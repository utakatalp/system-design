import javax.swing.*;

public class updateDueUI extends JFrame{
    private JPanel mainPanel;
    private JTextField textField1;
    private JButton güncelleButton;
    updateDueUI(){
        setContentPane(mainPanel);
        setTitle("Simple GUI App");
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,300);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

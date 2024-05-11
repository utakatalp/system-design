import javax.swing.*;

public class displayDebtUI extends JFrame{
    private JPanel mainPanel;
    private JLabel totalDebtAmount;

    displayDebtUI(){
        setContentPane(mainPanel);
        setTitle("Simple GUI App");
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,300);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

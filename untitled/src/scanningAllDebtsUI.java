import javax.swing.*;

public class scanningAllDebtsUI extends JFrame{
    private JList debtsList;
    private JPanel mainPanel;

    scanningAllDebtsUI(){
        setContentPane(mainPanel);
        setTitle("Simple GUI App");
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,300);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

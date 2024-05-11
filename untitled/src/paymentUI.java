import javax.swing.*;

public class paymentUI extends JFrame{
    private JLabel sumofDebts;
    private JComboBox comboBox1;
    private JPanel mainPanel;
    private JButton payButton;

    public paymentUI()
    {
        comboBox1.addItem("Aralik Ayi Borcu: 200");
        comboBox1.addItem("Ocak Ayi Borcu: 300");
        setContentPane(mainPanel);
        setTitle("Simple GUI App");
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,300);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

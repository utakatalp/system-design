import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class updateDueUI extends JFrame{
    private JPanel mainPanel;
    private JTextField dueField;
    private JButton güncelleButton;
    updateDueUI(){
        setContentPane(mainPanel);
        setTitle("Toplu Konut Yönetim Sistemi");
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,300);
        setLocationRelativeTo(null);
        setVisible(true);
        güncelleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDebt asd = new setDebt();
                asd.setFee(Double.parseDouble(dueField.getText()));
                try {
                    asd.setDebtforAllUsers();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
    }
}

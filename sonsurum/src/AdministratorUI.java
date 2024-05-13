import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AdministratorUI extends JFrame{
    private JPanel mainPanel;
    private JButton announcementButton;
    private JButton dispDebtsButton;
    private JButton updateDueButton;
    private JButton dischargeResidentButton;
    private JButton logoutButton;
    private JButton enrollUserButton;
    private JButton dispComplaintsButton;
    private JButton dispSuggestionsButton;

    AdministratorUI(){

        setContentPane(mainPanel);
        setTitle("Toplu Konut Yönetim Sistemi");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,400);
        setLocationRelativeTo(null);
        setVisible(true);

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Dashboard();
                //System.exit(0);
            }
        });
        announcementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new makingAnnouncementUI();
            }
        });
        dispDebtsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new scanningAllDebtsUI();
            }
        });
        updateDueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new updateDueUI();
            }
        });

        dischargeResidentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new removeResidentUI();
            }
        });
        enrollUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Registration();

            }
        });
        dispComplaintsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new dispComplaintsUI();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        dispSuggestionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new dispSuggestionsUI();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

    }
}

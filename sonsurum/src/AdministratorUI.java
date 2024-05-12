import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        setTitle("Simple GUI App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,400);
        setLocationRelativeTo(null);
        setVisible(true);

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
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
                dispose();
            }
        });
        dispComplaintsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new dispComplaintsUI();
            }
        });
        dispSuggestionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new dispSuggestionsUI();

            }
        });

    }
}

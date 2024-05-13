import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ApartmentResidentUI extends JFrame{
    private JButton aidatOdeButton;

    private JPanel mainPanel;

    public void setUsername(String username) {
        this.username.setText(username);
    }

    private JLabel username;
    private JButton complaintButton;
    private JButton changePassword;
    private JButton suggestionButton;
    private JButton logoutButton;
    private JPanel announcementPanel;
    private JTabbedPane tabbedPane1;
    private JPanel innerPanel;
    private JList<String> announceList;
    private JScrollPane scrollpane;
    private JLabel welcomeLabel;
    private JButton updateBalanceButton;
    private JPanel panel;
    private ResidentLoginUI main2;
    private DefaultListModel<String> listModel;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ApartmentResidentUI(User user) throws SQLException {

        setContentPane(mainPanel);
        setTitle("Toplu Konut Yönetim Sistemi");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,300);
        setLocationRelativeTo(null);
        setVisible(true);
        listModel = new DefaultListModel<>();
        welcomeLabel.setText("Hoşgeldiniz: "+user.getName());
        showAnnouncements();

        aidatOdeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new paymentUI(user);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        changePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new changingPasswordUI(user);
            }
        });
        complaintButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new complaintUI();
            }
        });
        suggestionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new suggestionUI();
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Dashboard();
                //System.exit(0);
            }
        });
        updateBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new updateBalanceUI(user);
            }
        });
    }
    private void showAnnouncements() throws SQLException {
        Connection conn = new DatabaseConnection().connect2();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ResultSet rs1;
        try {
            rs1 = stmt.executeQuery("SELECT * FROM announcements");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        while(rs1.next())
        {
            listModel.addElement(rs1.getString("announcement"));
        }

        announceList.setModel(listModel);
        scrollpane.setViewportView(announceList);
    }







}

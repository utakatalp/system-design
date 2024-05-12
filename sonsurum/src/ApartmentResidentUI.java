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

    public ApartmentResidentUI(User user) {

        setContentPane(mainPanel);
        setTitle("Simple GUI App");
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
                System.exit(0);
            }
        });
        updateBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new updateBalanceUI(user);
            }
        });
    }
    private void showAnnouncements()
    {
        Connection conn = new DatabaseConnection().connect2();

        Statement stmt = null;
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ResultSet rs1;
        try {
            rs1 = stmt.executeQuery("SELECT COUNT(*) FROM announcements");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        int boundList = 0;

        try {
            if (rs1.next()) {
                boundList = rs1.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String SQL = "SELECT * FROM announcements WHERE messageid = ?";
        for (int i = 1; i <= boundList; i++) {
            try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {

                pstmt.setInt(1, i);

                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    listModel.addElement(rs.getString("announcement"));

                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        announceList.setModel(listModel);
        scrollpane.setViewportView(announceList);
    }







}

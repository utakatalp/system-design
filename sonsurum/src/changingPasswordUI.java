import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class changingPasswordUI extends JFrame{
    private JPanel mainPanel;
    private JPasswordField passwordFieldNew;
    private JPasswordField passwordFieldNew2;
    private JPasswordField passwordFieldOld;
    private JButton button1;
    private JLabel oldPw;
    private JLabel newPw1;
    private JLabel newPw2;
    private User user;
    public changingPasswordUI(User user)
    {
        this.user = user;
        setContentPane(mainPanel);
        setTitle("Toplu Konut Yönetim Sistemi");
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,300);
        setLocationRelativeTo(null);
        setVisible(true);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePassword();
            }
        });
    }
    public void updatePassword() {
         // Veritabanından mevcut şifreyi çeker.



        //String passwordString = new String(passwordArray); // char[]'i String'e dönüştürür.
        int i=0;
        if(user.getPassword().equals(Arrays.toString(passwordFieldOld.getPassword()))) {
            // Kullanıcı şifresini güncelleme
            if(Arrays.equals(passwordFieldNew.getPassword(), passwordFieldNew2.getPassword())) {


                String SQL = "UPDATE users SET password = ? WHERE userid = ?";
                try (Connection conn = new DatabaseConnection().connect2();
                     PreparedStatement pstmt = conn.prepareStatement(SQL)) {

                    pstmt.setString(1, Arrays.toString(passwordFieldNew.getPassword())); // Yeni şifre, güvenlik açısından bu şekilde alınmalı
                    pstmt.setInt(2, user.getID());

                    int affectedRows = pstmt.executeUpdate();
                    if (affectedRows > 0) {
                        System.out.println("An existing user's password was updated successfully.");
                    } else {
                        System.out.println("No update was made. Check the user ID and data integrity.");
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            else {
                System.out.println("Şifreler eşleşmiyor.");
            }
        } else {
            System.out.println("Current password is incorrect.");
        }
    }
    public String getPwFromDB(){
        String pw=null;
        String SQL = "SELECT * FROM users WHERE userid = ?";
        try (Connection conn = new DatabaseConnection().connect2();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setInt(1,user.getID());
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                pw = rs.getString("password");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return pw;
    }

}

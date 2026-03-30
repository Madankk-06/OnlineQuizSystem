import java.awt.*;
import java.util.List;
import javax.swing.*;

public class LoginUI {

    public LoginUI(List<User> users) {

        Color bgColor = new Color(18, 18, 18);
        Color panelColor = new Color(30, 30, 30);
        Color accent = new Color(0, 120, 215);
        Color textColor = Color.WHITE;

        JFrame frame = new JFrame("Online Quiz System");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(panelColor);
        panel.setLayout(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        JLabel title = new JLabel("Login", JLabel.CENTER);
        title.setForeground(textColor);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));

        JTextField userField = new JTextField();
        userField.setBorder(BorderFactory.createTitledBorder("Username"));

        JPasswordField passField = new JPasswordField();
        passField.setBorder(BorderFactory.createTitledBorder("Password"));

        JButton loginBtn = new JButton("Login");
        loginBtn.setBackground(accent);
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFocusPainted(false);

        panel.add(title);
        panel.add(userField);
        panel.add(passField);
        panel.add(loginBtn);

        frame.add(panel);
        frame.getContentPane().setBackground(bgColor);

        loginBtn.addActionListener(e -> {
            try {
                String username = userField.getText();
                String password = new String(passField.getPassword());

                if (AuthService.login(users, username, password)) {
                    JOptionPane.showMessageDialog(frame, "Login Successful");
                    frame.dispose();
                    new QuizUI(username);
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid Login");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
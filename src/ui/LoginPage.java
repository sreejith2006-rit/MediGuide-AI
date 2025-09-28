package ui;

import javax.swing.*;
import java.awt.*;

public class LoginPage extends JFrame {

    public LoginPage() {
        setTitle("Login - MediGuide AI");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Full screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Background panel with gradient
        JPanel bgPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setPaint(new GradientPaint(0, 0, new Color(0x123456), 0, getHeight(), new Color(0x123456)));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        // Login box
        JPanel loginBox = new JPanel();
        loginBox.setPreferredSize(new Dimension(500, 400));
        loginBox.setBackground(Color.LIGHT_GRAY);
        loginBox.setLayout(new BoxLayout(loginBox, BoxLayout.Y_AXIS));
        loginBox.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        JLabel title = new JLabel("Login Screen");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField emailField = new JTextField();
        emailField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        emailField.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        // Button panel for centering
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setOpaque(false);

        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(e -> {
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Please enter both Email and Password.",
                        "Login Error",
                        JOptionPane.ERROR_MESSAGE
                );
            } else {
                dispose(); // Close login page
                new DoctorDashboard(); // Launch dashboard
            }
        });



        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        loginButton.setBackground(new Color(0, 123, 255));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setPreferredSize(new Dimension(150, 40));

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> {
            dispose(); // Close login page
            new RegisterPage(); // Launch register screen
        });
        registerButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        registerButton.setBackground(Color.GRAY);
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setPreferredSize(new Dimension(150, 40));

        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        // Add components to login box
        loginBox.add(title);
        loginBox.add(Box.createRigidArea(new Dimension(0, 30)));
        loginBox.add(emailLabel);
        loginBox.add(emailField);
        loginBox.add(Box.createRigidArea(new Dimension(0, 15)));
        loginBox.add(passwordLabel);
        loginBox.add(passwordField);
        loginBox.add(Box.createRigidArea(new Dimension(0, 30)));
        loginBox.add(buttonPanel);

        bgPanel.add(loginBox);
        add(bgPanel);
        setVisible(true);
    }
}





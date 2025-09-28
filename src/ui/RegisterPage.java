package ui;

import javax.swing.*;
import java.awt.*;

public class RegisterPage extends JFrame {

    public RegisterPage() {
        setTitle("Register - MediGuide AI");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Full screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Gradient background panel
        JPanel bgPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setPaint(new GradientPaint(0, 0, new Color(0x123456), 0, getHeight(), new Color(0x123456)));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        // Form box
        JPanel formBox = new JPanel();
        formBox.setPreferredSize(new Dimension(500, 500));
        formBox.setBackground(Color.LIGHT_GRAY);
        formBox.setLayout(new BoxLayout(formBox, BoxLayout.Y_AXIS));
        formBox.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        JLabel title = new JLabel("Register Screen");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Input fields
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JPasswordField confirmPasswordField = new JPasswordField();

        Font labelFont = new Font("Segoe UI", Font.BOLD, 18);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 18);

        formBox.add(title);
        formBox.add(Box.createRigidArea(new Dimension(0, 30)));

        formBox.add(createLabeledField("Full Name:", nameField, labelFont, fieldFont));
        formBox.add(Box.createRigidArea(new Dimension(0, 15)));
        formBox.add(createLabeledField("Email:", emailField, labelFont, fieldFont));
        formBox.add(Box.createRigidArea(new Dimension(0, 15)));
        formBox.add(createLabeledField("Password:", passwordField, labelFont, fieldFont));
        formBox.add(Box.createRigidArea(new Dimension(0, 15)));
        formBox.add(createLabeledField("Confirm Password:", confirmPasswordField, labelFont, fieldFont));
        formBox.add(Box.createRigidArea(new Dimension(0, 30)));

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setOpaque(false);

        JButton registerButton = new JButton("Register");
        JButton backButton = new JButton("Back to Login");

        styleButton(registerButton, new Color(0, 123, 255));
        styleButton(backButton, Color.GRAY);

        // Back button logic
        backButton.addActionListener(e -> {

            new LoginPage();
        });

        buttonPanel.add(registerButton);

// Register button logic
        registerButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            String confirmPassword = new String(confirmPasswordField.getPassword()).trim();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "All fields are required. Please fill in all details.",
                        "Registration Error",
                        JOptionPane.ERROR_MESSAGE
                );
            } else if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(
                        this,
                        "Passwords do not match. Please try again.",
                        "Registration Error",
                        JOptionPane.ERROR_MESSAGE
                );
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "✅ Registration successful! Please login.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );
                dispose();
                new LoginPage(); // Go back to login after successful registration
            }
        });


        buttonPanel.add(backButton);
        formBox.add(buttonPanel);

        bgPanel.add(formBox);
        add(bgPanel);
        setVisible(true);
    }

    private JPanel createLabeledField(String labelText, JComponent field, Font labelFont, Font fieldFont) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);

        JLabel label = new JLabel(labelText);
        label.setFont(labelFont);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        field.setFont(fieldFont);

        panel.add(label);
        panel.add(field);
        return panel;
    }

    private void styleButton(JButton button, Color bgColor) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 18));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(180, 40));
    }
}


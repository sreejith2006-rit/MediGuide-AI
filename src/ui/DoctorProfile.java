package ui;

import javax.swing.*;
import java.awt.*;

public class DoctorProfile extends JFrame {

    public DoctorProfile() {
        setTitle("Doctor Profile - MediGuide AI");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Left navigation panel
        JPanel navPanel = new JPanel();
        navPanel.setBackground(new Color(0x1A2B3C));
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));
        navPanel.setPreferredSize(new Dimension(200, getHeight()));
        navPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        Font navFont = new Font("Segoe UI", Font.BOLD, 16);
        String[] navItems = {"Home", "Patients", "Profile", "AI Analysis"};

        for (String item : navItems) {
            JButton navButton = new JButton(item);
            navButton.setFont(navFont);
            navButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            navButton.setBackground(item.equals("Profile") ? new Color(0x00BFFF) : new Color(0x123456));
            navButton.setMaximumSize(new Dimension(180, 40));
            navButton.setForeground(Color.WHITE);
            navButton.setFocusPainted(false);

            navButton.addActionListener(e -> {
                String label = ((JButton) e.getSource()).getText();
                switch (label) {
                    case "Home":
                        dispose();
                        new DoctorDashboard(); // Return to dashboard
                        break;
                    case "Patients":
                        dispose();
                         new SearchPatients(); // Uncomment when implemented
                        break;
                    case "Profile":
                        // Already on profile, do nothing or refresh
                        break;
                    case "AI Analysis":
                        dispose();
                        new AIAnalysis(); // Uncomment when implemented
                        break;
                }
            });

            navPanel.add(navButton);
            navPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        }

        // Main profile panel
        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.Y_AXIS));
        profilePanel.setBackground(new Color(0x123456));
        profilePanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        // Profile image
        ImageIcon profileIcon = new ImageIcon(getClass().getResource("/ui/doctor-profile.png"));
        Image scaledImage = profileIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        JLabel profilePic = new JLabel(new ImageIcon(scaledImage));
        profilePic.setAlignmentX(Component.CENTER_ALIGNMENT);
        profilePic.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        profilePic.setPreferredSize(new Dimension(150, 150));

        // Profile details
        Font labelFont = new Font("Segoe UI", Font.BOLD, 20);
        Font valueFont = new Font("Segoe UI", Font.PLAIN, 20);

        profilePanel.add(profilePic);
        profilePanel.add(Box.createRigidArea(new Dimension(0, 30)));
        profilePanel.add(createProfileRow("Name:", "Dr. John Doe", labelFont, valueFont));
        profilePanel.add(createProfileRow("Specialization:", "Cardiologist", labelFont, valueFont));
        profilePanel.add(createProfileRow("Email:", "johndoe@example.com", labelFont, valueFont));
        profilePanel.add(createProfileRow("Phone:", "+91 9876543210", labelFont, valueFont));
        profilePanel.add(createProfileRow("Hospital:", "City Hospital", labelFont, valueFont));
        profilePanel.add(Box.createRigidArea(new Dimension(0, 40)));

        // Bottom buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(0x123456));

        JButton editBtn = new JButton("Edit Profile");
        JButton changePwdBtn = new JButton("Change Password");

        styleProfileButton(editBtn);
        styleProfileButton(changePwdBtn);

        buttonPanel.add(editBtn);
        buttonPanel.add(changePwdBtn);
        profilePanel.add(buttonPanel);

        add(navPanel, BorderLayout.WEST);
        add(profilePanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private JPanel createProfileRow(String labelText, String valueText, Font labelFont, Font valueFont) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row.setBackground(new Color(0x123456));

        JLabel label = new JLabel(labelText);
        label.setFont(labelFont);
        label.setForeground(Color.WHITE);

        JLabel value = new JLabel(valueText);
        value.setFont(valueFont);
        value.setForeground(Color.WHITE);

        row.add(label);
        row.add(Box.createRigidArea(new Dimension(20, 0)));
        row.add(value);
        return row;
    }

    private void styleProfileButton(JButton button) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setBackground(new Color(0x2C3E50));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(180, 40));
    }
}


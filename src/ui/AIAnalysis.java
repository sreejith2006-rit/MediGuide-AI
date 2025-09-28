package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class AIAnalysis extends JFrame {

    private JTextArea resultArea;

    public AIAnalysis() {
        setTitle("AI Analysis - MediGuide AI");
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
            navButton.setBackground(item.equals("AI Analysis") ? new Color(0x00BFFF) : new Color(0x123456));
            navButton.setMaximumSize(new Dimension(180, 40));
            navButton.setForeground(Color.WHITE);
            navButton.setFocusPainted(false);

            navButton.addActionListener(e -> {
                String label = ((JButton) e.getSource()).getText();
                switch (label) {
                    case "Home":
                        dispose();
                        new DoctorDashboard();
                        break;
                    case "Profile":
                        dispose();
                        new DoctorProfile();
                        break;
                    case "Patients":
                         new SearchPatients(); // implement when ready
                        break;
                    case "AI Analysis":
                        // Already here
                        break;
                }
            });

            navPanel.add(navButton);
            navPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        }

        // Main content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(new Color(0x123456));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        JLabel title = new JLabel("AI Analysis");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(title);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Patient ID
        JLabel idLabel = new JLabel("Enter Patient ID:");
        idLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        idLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        idLabel.setForeground(Color.WHITE);
        JTextField idField = new JTextField();
        idField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        idField.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        contentPanel.add(idLabel);
        contentPanel.add(idField);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Symptoms
        JLabel symptomsLabel = new JLabel("Patient Symptoms:");
        symptomsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        symptomsLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        symptomsLabel.setForeground(Color.WHITE);
        JTextArea symptomsArea = new JTextArea(5, 30);
        symptomsArea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        symptomsArea.setLineWrap(true);
        symptomsArea.setWrapStyleWord(true);
        JScrollPane symptomsScroll = new JScrollPane(symptomsArea);
        symptomsScroll.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

        contentPanel.add(symptomsLabel);
        contentPanel.add(symptomsScroll);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(0x123456));

        JButton uploadBtn = new JButton("Upload Report");
        JButton analyzeBtn = new JButton("Analyze Symptoms");

        styleButton(uploadBtn);
        styleButton(analyzeBtn);

        buttonPanel.add(uploadBtn);
        buttonPanel.add(analyzeBtn);
        contentPanel.add(buttonPanel);

        // Result area
        JLabel resultLabel = new JLabel("Analysis Result:");
        resultLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        resultLabel.setForeground(Color.WHITE);
        resultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        resultArea = new JTextArea(6, 40);
        resultArea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        resultArea.setEditable(false);
        JScrollPane resultScroll = new JScrollPane(resultArea);
        resultScroll.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));

        contentPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        contentPanel.add(resultLabel);
        contentPanel.add(resultScroll);

        // Button actions
        analyzeBtn.addActionListener((ActionEvent e) -> {
            resultArea.setText("Result will be analyzed based on entered symptoms.");
        });

        uploadBtn.addActionListener((ActionEvent e) -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                resultArea.setText("Report \"" + selectedFile.getName() + "\" should be analyzed.");
            }
        });

        add(navPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setBackground(Color.LIGHT_GRAY);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(180, 40));
    }
}

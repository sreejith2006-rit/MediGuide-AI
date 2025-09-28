package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DoctorDashboard extends JFrame {

    public DoctorDashboard() {
        setTitle("Doctor Dashboard - MediGuide AI");
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
            navButton.setBackground(item.equals("Home") ? new Color(0x00BFFF) : new Color(0x123456));
            navButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            navButton.setMaximumSize(new Dimension(180, 40));
            navButton.setForeground(Color.WHITE);
            navButton.setFocusPainted(false);

            navButton.addActionListener(e -> {
                String label = ((JButton) e.getSource()).getText();
                switch (label) {
                    case "Profile":
                        new DoctorProfile(); // Opens profile window
                        break;
                    case "Patients":
                         new SearchPatients(); // Add this class if needed
                        break;
                    case "AI Analysis":
                        new AIAnalysis(); // Add this class if needed
                        break;
                    case "Home":
                        // Optional: refresh or show home content
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
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel greeting = new JLabel("Good Morning, Dr. John Doe");
        greeting.setFont(new Font("Segoe UI", Font.BOLD, 28));
        greeting.setAlignmentX(Component.CENTER_ALIGNMENT);
        greeting.setForeground(Color.WHITE);
        contentPanel.add(greeting);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Summary boxes
        JPanel summaryPanel = new JPanel(new GridLayout(1, 4, 20, 0));
        summaryPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
        summaryPanel.setBackground(new Color(0x123456));

        String[] summaries = {"Total Patients", "Appointments Today", "Pending Reports", "AI Analyses"};
        String[] values = {"120", "5", "3", "7"};

        for (int i = 0; i < summaries.length; i++) {
            JPanel box = new JPanel();
            box.setBackground(Color.WHITE);
            box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
            box.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(0x123456), 2),
                    BorderFactory.createEmptyBorder(20, 20, 20, 20)
            ));

            JLabel valueLabel = new JLabel(values[i], SwingConstants.CENTER);
            valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
            valueLabel.setForeground(Color.BLACK);
            valueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel textLabel = new JLabel(summaries[i], SwingConstants.CENTER);
            textLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
            textLabel.setForeground(new Color(0x123456));
            textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            box.add(valueLabel);
            box.add(Box.createRigidArea(new Dimension(0, 10)));
            box.add(textLabel);
            summaryPanel.add(box);
        }

        contentPanel.add(summaryPanel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Patient table
        JLabel tableTitle = new JLabel("Recent Patients");
        tableTitle.setForeground(Color.WHITE);
        tableTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        tableTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(tableTitle);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        String[] columns = {"Name", "Age", "Sex", "Diagnosis"};
        Object[][] data = {
                {"John Doe", 25, "Male", "Fever"},
                {"Alice Smith", 30, "Female", "Cold"},
                {"Bob Brown", 45, "Male", "Flu"},
                {"Michael Scott", 35, "Male", "Headache"}
        };

        DefaultTableModel model = new DefaultTableModel(data, columns);
        JTable table = new JTable(model);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        table.setRowHeight(28);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800, 300));
        contentPanel.add(scrollPane);

        // Bottom buttons
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        bottomPanel.setBackground(new Color(0x123456));

        JButton addPatientBtn = new JButton("Add Patient");
        JButton aiAnalysisBtn = new JButton("AI Analysis");

        styleBottomButton(addPatientBtn);
        styleBottomButton(aiAnalysisBtn);

        bottomPanel.add(addPatientBtn);
        bottomPanel.add(aiAnalysisBtn);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        contentPanel.add(bottomPanel);

        add(navPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void styleBottomButton(JButton button) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setBackground(new Color(0x2C3E50));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(150, 40));
    }
}





package ui;

import javax.swing.*;
import java.awt.*;
import java.util.function.BiConsumer;

public class SearchPatients extends JFrame {

    public SearchPatients() {
        setTitle("Medical Assistant - Search Patients");
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
            navButton.setMaximumSize(new Dimension(180, 40));
            navButton.setBackground(item.equals("Patients") ? new Color(0x00BFFF) : new Color(0x123456));
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
                    case "AI Analysis":
                        dispose();
                        new AIAnalysis();
                        break;
                    case "Patients":
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

        JLabel searchLabel = new JLabel("Search Patients:");
        searchLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        searchLabel.setForeground(Color.WHITE);

        JTextField searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(400, 35));
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        JButton submitBtn = new JButton("Submit");
        submitBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        submitBtn.setBackground(Color.LIGHT_GRAY);
        submitBtn.setForeground(Color.BLACK);
        submitBtn.setFocusPainted(false);
        submitBtn.setPreferredSize(new Dimension(120, 35));

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
        searchPanel.setBackground(new Color(0x123456));
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(submitBtn);

        JTextArea resultArea = new JTextArea("Details will be fetched from the database.");
        resultArea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        resultArea.setEditable(false);
        resultArea.setBackground(Color.WHITE);
        JScrollPane resultScroll = new JScrollPane(resultArea);
        resultScroll.setPreferredSize(new Dimension(800, 200));

        JButton addPatientBtn = new JButton("Add Patient");
        addPatientBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        addPatientBtn.setBackground(Color.LIGHT_GRAY);
        addPatientBtn.setForeground(Color.BLACK);
        addPatientBtn.setFocusPainted(false);
        addPatientBtn.setPreferredSize(new Dimension(150, 40));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(0x123456));
        bottomPanel.add(addPatientBtn);

        submitBtn.addActionListener(e -> resultArea.setText("Details will be fetched from the database."));
        addPatientBtn.addActionListener(e -> showAddPatientDialog());

        contentPanel.add(searchPanel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        contentPanel.add(resultScroll);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        contentPanel.add(bottomPanel);

        add(navPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void showAddPatientDialog() {
        JDialog dialog = new JDialog(this, "Add Patient", true);
        dialog.setSize(550, 450);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());


// Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0x2C3E50));
        JLabel headerLabel = new JLabel("➕ Add Patient Details");
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
        dialog.add(headerPanel, BorderLayout.NORTH);

// Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        Font labelFont = new Font("Segoe UI", Font.BOLD, 15);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 15);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 10, 12, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

// Utility for adding row
        BiConsumer<String, JTextField> addRow = (label, field) -> {
            JLabel jLabel = new JLabel(label);
            jLabel.setFont(labelFont);
            field.setFont(fieldFont);
            field.setPreferredSize(new Dimension(200, 28));
            formPanel.add(jLabel, gbc);
            gbc.gridx = 1;
            formPanel.add(field, gbc);
            gbc.gridy++;
            gbc.gridx = 0;
        };

        JTextField nameField = new JTextField();
        JTextField addressField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField symptomsField = new JTextField();
        JTextField diseaseField = new JTextField();

        addRow.accept("Patient Name:", nameField);
        addRow.accept("Patient Address:", addressField);
        addRow.accept("Patient Age:", ageField);
        addRow.accept("Symptoms:", symptomsField);
        addRow.accept("Disease:", diseaseField);

        dialog.add(formPanel, BorderLayout.CENTER);

// Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton enterBtn = new JButton("✔ Enter");
        JButton cancelBtn = new JButton("✖ Cancel");

        enterBtn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        enterBtn.setBackground(new Color(0x27AE60));
        enterBtn.setForeground(Color.WHITE);
        enterBtn.setFocusPainted(false);
        enterBtn.setPreferredSize(new Dimension(120, 40));

        cancelBtn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        cancelBtn.setBackground(new Color(0xE74C3C));
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.setFocusPainted(false);
        cancelBtn.setPreferredSize(new Dimension(120, 40));

// Action listeners
        enterBtn.addActionListener(e -> {
            try {
                int age = Integer.parseInt(ageField.getText().trim());
                if (nameField.getText().trim().isEmpty() || addressField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Please fill all required fields.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(dialog, "✅ Patient record has been saved successfully!");
                    dialog.dispose();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Age must be a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelBtn.addActionListener(e -> dialog.dispose());

        buttonPanel.add(enterBtn);
        buttonPanel.add(cancelBtn);

        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setVisible(true);


    }




    private JPanel createLabeledField(String labelText, Font labelFont, Font fieldFont) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);

        JLabel label = new JLabel(labelText);
        label.setFont(labelFont);
        JTextField field = new JTextField();
        field.setFont(fieldFont);
        field.setPreferredSize(new Dimension(300, 30));

        panel.add(label, BorderLayout.WEST);
        panel.add(field, BorderLayout.CENTER);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        return panel;
    }
}



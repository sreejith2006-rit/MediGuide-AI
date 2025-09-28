
package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class WelcomePage extends JFrame {

    private final JProgressBar bar = new JProgressBar(0, 100);

    public WelcomePage() {
        setTitle("MediGuide AI");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Custom gradient background panel (navy -> teal)
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(10, 25, 47),    // Navy
                        0, getHeight(), new Color(36, 123, 160) // Teal-blue
                );
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        backgroundPanel.setLayout(new GridBagLayout()); // center everything

        // Container with vertical layout
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);

        // Title
        JLabel appName = new JLabel("MediGuide AI", SwingConstants.CENTER);
        appName.setFont(new Font("SansSerif", Font.BOLD, 90));
        appName.setForeground(new Color(240, 248, 255));
        appName.setAlignmentX(Component.CENTER_ALIGNMENT);
        appName.setBorder(BorderFactory.createEmptyBorder(40, 0, 15, 0));

        // Description
        JLabel description = new JLabel(
                "<html><div style='text-align: center;'>Welcome to MediGuide AI.<br>" +
                        "This clinical management system helps doctors classify disease severity<br>" +
                        "and supports informed diagnosis using AI.</div></html>",
                SwingConstants.CENTER
        );
        description.setFont(new Font("SansSerif", Font.PLAIN, 26));
        description.setForeground(new Color(200, 220, 240));
        description.setAlignmentX(Component.CENTER_ALIGNMENT);
        description.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));

        // Button
        JButton getStartedButton = new JButton("Get Started");
        getStartedButton.setFont(new Font("SansSerif", Font.BOLD, 22));
        getStartedButton.setBackground(new Color(0, 123, 255));
        getStartedButton.setForeground(Color.WHITE);
        getStartedButton.setFocusPainted(false);
        getStartedButton.setBorder(BorderFactory.createEmptyBorder(15, 40, 15, 40));
        getStartedButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        getStartedButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Hover effect
        getStartedButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                getStartedButton.setBackground(new Color(0, 105, 217));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                getStartedButton.setBackground(new Color(0, 123, 255));
            }
        });

        // Progress bar styling
        bar.setStringPainted(true);
        bar.setFont(new Font("SansSerif", Font.BOLD, 18));
        bar.setForeground(new Color(0, 180, 100));
        bar.setBackground(Color.WHITE);
        bar.setPreferredSize(new Dimension(400, 30));
        bar.setAlignmentX(Component.CENTER_ALIGNMENT);
        bar.setVisible(false); // hidden until used

        // Add components
        contentPanel.add(appName);
        contentPanel.add(description);
        contentPanel.add(getStartedButton);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20))); // small gap
        contentPanel.add(bar);

        backgroundPanel.add(contentPanel, new GridBagConstraints());
        setContentPane(backgroundPanel);

        // Button action: show + fill progress bar
        getStartedButton.addActionListener((ActionEvent e) -> {
            getStartedButton.setEnabled(false);
            bar.setVisible(true);
            fillProgressBar();
        });
    }

    // Animate progress bar without freezing UI
    private void fillProgressBar() {
        new Thread(() -> {
            for (int c = 0; c <= 100; c++) {
                final int value = c;
                SwingUtilities.invokeLater(() -> bar.setValue(value));
                try {
                    Thread.sleep(40); // smooth speed
                } catch (InterruptedException ignored) {
                }
            }
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(this, "Application Ready!");
            });
        }).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new WelcomePage().setVisible(true));
    }
}











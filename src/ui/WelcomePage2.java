package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePage2 extends JFrame {

    private Image bgImage;

    WelcomePage2() {
        setTitle("MediGuide AI");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load background image
        ImageIcon imgIcon = new ImageIcon("heart-stethoscope-png-24.jpg");
        bgImage = imgIcon.getImage();

        // Main container with background image
        JPanel container = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (bgImage != null) {
                    g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        container.setOpaque(true);
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        // Header
        JPanel header = new JPanel(new GridBagLayout());
        header.setPreferredSize(new Dimension(1000, 250));
        header.setOpaque(false);
        JLabel titleLabel = new JLabel("MediGuide AI");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 90));
        header.add(titleLabel);

        // Description
        JPanel describer = new JPanel(new GridBagLayout());
        describer.setPreferredSize(new Dimension(1000, 200));
        describer.setOpaque(false);
        JLabel description = new JLabel(
                "<html><div style='text-align: center;'>Welcome to MediGuide AI.<br>" +
                        "This clinical management system helps doctors classify disease severity<br>" +
                        "and supports informed diagnosis using AI.</div></html>",
                SwingConstants.CENTER
        );
        description.setFont(new Font("Segoe UI", Font.PLAIN, 28));
        describer.add(description);

        // Button
        JPanel bHolder = new JPanel(new GridBagLayout());
        bHolder.setPreferredSize(new Dimension(1000, 100));
        bHolder.setOpaque(false);
        JButton getStartedButton = new JButton("Get Started");
        getStartedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==getStartedButton){

                    new LoginPage();

                }
            }
        });
        getStartedButton.setFont(new Font("Segoe UI", Font.BOLD, 28));
        getStartedButton.setBackground(new Color(0, 123, 255));
        getStartedButton.setForeground(Color.WHITE);
        getStartedButton.setFocusPainted(false);
        getStartedButton.setBorder(BorderFactory.createEmptyBorder(15, 50, 15, 50));
        bHolder.add(getStartedButton);

        // Add header, description, button to container
        container.add(header);
        container.add(describer);
        container.add(Box.createRigidArea(new Dimension(0, 30)));
        container.add(bHolder);

        // Footer
        JPanel footer = new JPanel(new BorderLayout());
        footer.setBackground(new Color(0x123456));
        footer.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel footerContent = new JPanel();
        footerContent.setLayout(new BoxLayout(footerContent, BoxLayout.Y_AXIS));
        footerContent.setBackground(new Color(0x123456));

        JLabel slogan = new JLabel(
                "<html><div style='text-align: center;'>"
                        + "MediGuide AI empowers healthcare professionals with intelligent insights.<br>"
                        + "Classify disease severity accurately, streamline clinical decisions,<br>"
                        + "and leverage AI-driven support for faster, informed diagnosis.<br>"
                        + "Better care starts with smarter guidance."
                        + "</div></html>",
                SwingConstants.CENTER
        );
        slogan.setFont(new Font("Segoe UI", Font.BOLD, 22));
        slogan.setForeground(Color.WHITE);
        slogan.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel copyright = new JLabel(
                "© 2025 MediGuide AI. All rights reserved.",
                SwingConstants.CENTER
        );
        copyright.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        copyright.setAlignmentX(Component.CENTER_ALIGNMENT);
        copyright.setForeground(Color.WHITE);

        footerContent.add(slogan);
        footerContent.add(Box.createRigidArea(new Dimension(0, 12)));
        footerContent.add(copyright);

        footer.add(footerContent, BorderLayout.CENTER);

        // Add container to frame center, footer at bottom
        this.setLayout(new BorderLayout());
        this.add(container, BorderLayout.CENTER);
        this.add(footer, BorderLayout.SOUTH);

        // Pack or set size
        // setSize(1200, 800); // Optionally set a size if not maximized

        setVisible(true);
    }


    public static void main(String[] args) {
         new WelcomePage2(); // Better threading for Swing
    }
}








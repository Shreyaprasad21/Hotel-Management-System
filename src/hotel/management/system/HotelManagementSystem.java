package hotel.management.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class HotelManagementSystem extends JFrame implements ActionListener {

    private JLabel backgroundLabel;
    private JButton nextButton;

    public HotelManagementSystem() {
        // Set frame properties
        setTitle("Welcome to 7-Star Hotel Management System");
        setSize(1004, 575);
        setLayout(null);
        setLocation(280, 145);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Background Image
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icons/first.jpg"));
        Image scaledImage = icon.getImage().getScaledInstance(1004, 565, Image.SCALE_SMOOTH);
        backgroundLabel = new JLabel(new ImageIcon(scaledImage));
        backgroundLabel.setBounds(0, 0, 1004, 565);

        // Next Button with Custom Styling
        nextButton = new JButton("Enter");
        nextButton.setBounds(850, 450, 120, 45);
        nextButton.setBackground(new Color(255, 215, 0)); // Gold color for luxury feel
        nextButton.setForeground(Color.BLACK);
        nextButton.setFont(new Font("Serif", Font.BOLD, 18));
        nextButton.setFocusPainted(false);
        nextButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        
        // Button Hover Effect
        nextButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                nextButton.setBackground(new Color(255, 223, 90)); // Light gold on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                nextButton.setBackground(new Color(255, 215, 0)); // Original gold color
            }
        });

        // Add Action Listener
        nextButton.addActionListener(this);

        // Add components to the frame
        backgroundLabel.add(nextButton);
        add(backgroundLabel);

        // Set visibility
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        new Login().setVisible(true);
        this.dispose(); // Close current window
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HotelManagementSystem window = new HotelManagementSystem();
            window.setVisible(true);
        });
    }
}

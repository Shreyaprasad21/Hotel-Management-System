package hotel.management.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    
    private JLabel usernameLabel, passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, cancelButton;

    public Login() {
        super("Hotel Management System - Login");

        setLayout(null);
        setSize(600, 350);
        setLocation(530, 270);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Username Label
        usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(40, 40, 100, 30);
        usernameLabel.setFont(new Font("Serif", Font.BOLD, 16));
        add(usernameLabel);
        
        // Password Label
        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(40, 100, 100, 30);
        passwordLabel.setFont(new Font("Serif", Font.BOLD, 16));
        add(passwordLabel);
        
        // Username TextField
        usernameField = new JTextField();
        usernameField.setBounds(150, 40, 200, 30);
        usernameField.setFont(new Font("Serif", Font.PLAIN, 14));
        add(usernameField);
        
        // Password Field
        passwordField = new JPasswordField();
        passwordField.setBounds(150, 100, 200, 30);
        passwordField.setFont(new Font("Serif", Font.PLAIN, 14));
        add(passwordField);

        // Image/Icon
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image scaledImage = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
        imageLabel.setBounds(400, 50, 150, 150);
        add(imageLabel);

        // Login Button with Hover Effect
        loginButton = new JButton("Login");
        loginButton.setBounds(40, 180, 140, 40);
        loginButton.setFont(new Font("Serif", Font.BOLD, 16));
        loginButton.setBackground(new Color(32, 178, 170)); // Sea green for luxury
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBorder(BorderFactory.createEmptyBorder());
        loginButton.addActionListener(this);
        add(loginButton);
        
        // Login Button Hover Effect
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(32, 160, 150)); // Lighter on hover
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(32, 178, 170));
            }
        });

        // Cancel Button with Hover Effect
        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(200, 180, 140, 40);
        cancelButton.setFont(new Font("Serif", Font.BOLD, 16));
        cancelButton.setBackground(Color.RED.darker());
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusPainted(false);
        cancelButton.setBorder(BorderFactory.createEmptyBorder());
        cancelButton.addActionListener(this);
        add(cancelButton);
        
        // Cancel Button Hover Effect
        cancelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cancelButton.setBackground(Color.RED.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cancelButton.setBackground(Color.RED.darker());
            }
        });

        // Set background color
        getContentPane().setBackground(new Color(255, 255, 245)); // Light beige

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == loginButton) {
            try {
                conn c1 = new conn();
                String u = usernameField.getText();
                String v = new String(passwordField.getPassword());

                String query = "SELECT * FROM login WHERE username='" + u + "' AND password='" + v + "'";
                ResultSet rs = c1.s.executeQuery(query);
                
                if (rs.next()) { 
                    new Dashboard().setVisible(true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid username or password. Please try again.", 
                            "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == cancelButton) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Login());
    }
}

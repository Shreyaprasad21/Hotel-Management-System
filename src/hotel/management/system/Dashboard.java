package hotel.management.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Dashboard extends JFrame {

    public static void main(String[] args) {
        new Dashboard().setVisible(true);
    }

    public Dashboard() {
        super("HOTEL MANAGEMENT SYSTEM");
        
        // Set layout and size
        setLayout(null);
        setSize(1950, 1090);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Background Image
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icons/third.jpg"));
        Image image = imageIcon.getImage().getScaledInstance(1950, 1000, Image.SCALE_SMOOTH);
        JLabel backgroundImageLabel = new JLabel(new ImageIcon(image));
        backgroundImageLabel.setBounds(0, 0, 1950, 1000);
        add(backgroundImageLabel);

        // Title Label with a border and shadow effect
        JLabel titleLabel = new JLabel("GRAND OASIS SUITES", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 50));
        titleLabel.setBounds(0, 50, 1950, 100);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(0, 0, 0, 150)); // Semi-transparent background
        backgroundImageLabel.add(titleLabel);

        // Menu Bar
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(0, 0, 0)); // Black background for luxury
        menuBar.setBorderPainted(false);
        setJMenuBar(menuBar);

        // Hotel Management Menu
        JMenu managementMenu = new JMenu("HOTEL MANAGEMENT");
        managementMenu.setForeground(Color.WHITE);
        managementMenu.setFont(new Font("SansSerif", Font.BOLD, 18));
        managementMenu.setBackground(new Color(0, 0, 0)); // Black background for menu
        menuBar.add(managementMenu);

        // Reception Menu Item
        JMenuItem receptionMenuItem = new JMenuItem("RECEPTION");
        styleMenuItem(receptionMenuItem);
        managementMenu.add(receptionMenuItem);
        
        receptionMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                new Reception();
            }
        });

        // Admin Menu
        JMenu adminMenu = new JMenu("ADMIN");
        adminMenu.setForeground(Color.WHITE);
        adminMenu.setFont(new Font("SansSerif", Font.BOLD, 18));
        adminMenu.setBackground(new Color(0, 0, 0)); // Black background for menu
        menuBar.add(adminMenu);

        // Add Employee Menu Item
        JMenuItem addEmployeeMenuItem = new JMenuItem("ADD EMPLOYEE");
        styleMenuItem(addEmployeeMenuItem);
        adminMenu.add(addEmployeeMenuItem);

        addEmployeeMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    new AddEmployee().setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Add Rooms Menu Item
        JMenuItem addRoomsMenuItem = new JMenuItem("ADD ROOMS");
        styleMenuItem(addRoomsMenuItem);
        adminMenu.add(addRoomsMenuItem);

        addRoomsMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    new AddRoom().setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Adding Hover Effect for Menu Items
        addHoverEffect(adminMenu);
        addHoverEffect(managementMenu);

        // Footer Label
        JLabel footerLabel = new JLabel("Welcome to the Grand Oasis Suites", SwingConstants.CENTER);
        footerLabel.setForeground(Color.WHITE);
        footerLabel.setFont(new Font("SansSerif", Font.ITALIC, 18));
        footerLabel.setBounds(0, 950, 1950, 50);
        footerLabel.setOpaque(true);
        footerLabel.setBackground(new Color(0, 0, 0, 150)); // Semi-transparent background
        backgroundImageLabel.add(footerLabel);
    }

    private void styleMenuItem(JMenuItem menuItem) {
        menuItem.setFont(new Font("SansSerif", Font.PLAIN, 16));
        menuItem.setBackground(new Color(32, 178, 170));
        menuItem.setForeground(Color.WHITE);
    }

    private void addHoverEffect(JMenu menu) {
        for (Component menuElement : menu.getMenuComponents()) {
            if (menuElement instanceof JMenuItem) {
                menuElement.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        menuElement.setBackground(new Color(32, 160, 150)); // Lighter color on hover
                    }
                    public void mouseExited(java.awt.event.MouseEvent evt) {
                        menuElement.setBackground(new Color(32, 178, 170));
                    }
                });
            }
        }
    }
}

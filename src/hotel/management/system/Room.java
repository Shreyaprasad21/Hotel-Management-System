package hotel.management.system;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import net.proteanit.sql.DbUtils;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Room extends JFrame {
    Connection conn = null;
    private JPanel contentPane;
    private JTable table;
    private JLabel lblAvailability;
    private JLabel lblCleanStatus;
    private JLabel lblPrice;
    private JLabel lblBedType;
    private JLabel lblRoomNumber;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Room frame = new Room();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Room() throws SQLException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 200, 1100, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        // Background Image
        ImageIcon backgroundImage = new ImageIcon(ClassLoader.getSystemResource("icons/eight.jpg"));
        Image scaledBackgroundImage = backgroundImage.getImage().getScaledInstance(600, 600, Image.SCALE_SMOOTH);
        JLabel backgroundLabel = new JLabel(new ImageIcon(scaledBackgroundImage));
        backgroundLabel.setBounds(500, 0, 600, 600);
        contentPane.add(backgroundLabel);
        
        // Table
        table = new JTable();
        table.setBorder(new LineBorder(new Color(0, 128, 128), 2));
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.setBackground(new Color(255, 255, 255));
        table.setForeground(new Color(0, 0, 0));
        table.setBounds(0, 40, 500, 400);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 60, 480, 400);
        contentPane.add(scrollPane);
        
        // Load Data Button
        JButton btnLoadData = new JButton("Load Data");
        btnLoadData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    conn c = new conn();
                    String displayCustomersql = "SELECT * FROM Room";
                    ResultSet rs = c.s.executeQuery(displayCustomersql);
                    table.setModel(DbUtils.resultSetToTableModel(rs));
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnLoadData.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnLoadData.setBackground(new Color(32, 178, 170));
        btnLoadData.setForeground(Color.WHITE);
        btnLoadData.setBounds(100, 480, 120, 30);
        contentPane.add(btnLoadData);
        
        // Back Button
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Reception().setVisible(true);
                setVisible(false);
            }
        });
        btnBack.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnBack.setBackground(new Color(32, 178, 170));
        btnBack.setForeground(Color.WHITE);
        btnBack.setBounds(290, 480, 120, 30);
        contentPane.add(btnBack);
        
        // Column Labels
        lblRoomNumber = new JLabel("Room Number");
        lblRoomNumber.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblRoomNumber.setBounds(10, 30, 120, 20);
        contentPane.add(lblRoomNumber);
        
        lblAvailability = new JLabel("Availability");
        lblAvailability.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblAvailability.setBounds(150, 30, 100, 20);
        contentPane.add(lblAvailability);
        
        lblCleanStatus = new JLabel("Clean Status");
        lblCleanStatus.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblCleanStatus.setBounds(270, 30, 100, 20);
        contentPane.add(lblCleanStatus);
        
        lblPrice = new JLabel("Price");
        lblPrice.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblPrice.setBounds(390, 30, 100, 20);
        contentPane.add(lblPrice);
        
        lblBedType = new JLabel("Bed Type");
        lblBedType.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblBedType.setBounds(500, 30, 100, 20);
        contentPane.add(lblBedType);
        
        // Set background color
        getContentPane().setBackground(Color.WHITE);
    }
}

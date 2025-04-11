package hotel.management.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class AddRoom extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JTextField t4, t2;
    private JComboBox<String> comboBox, comboBox_2, comboBox_3;
    JButton b1, b2;

    public static void main(String[] args) {
        new AddRoom().setVisible(true);
    }

    public AddRoom() {
        setBounds(450, 200, 1000, 450);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/twelve.jpg"));
        Image i3 = i1.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT);
        JLabel l15 = new JLabel(new ImageIcon(i3));
        l15.setBounds(400, 30, 500, 370);
        add(l15);

        JLabel l10 = new JLabel("Add Rooms");
        l10.setFont(new Font("Tahoma", Font.BOLD, 18));
        l10.setBounds(194, 10, 120, 22);
        contentPane.add(l10);

        JLabel l1 = new JLabel("Room Number");
        l1.setFont(new Font("Tahoma", Font.BOLD, 14));
        l1.setBounds(64, 70, 102, 22);
        contentPane.add(l1);

        t4 = new JTextField();
        t4.setBounds(174, 70, 156, 20);
        contentPane.add(t4);

        JLabel l2 = new JLabel("Availability");
        l2.setFont(new Font("Tahoma", Font.BOLD, 14));
        l2.setBounds(64, 110, 102, 22);
        contentPane.add(l2);

        comboBox = new JComboBox<>(new String[]{"Available", "Occupied"});
        comboBox.setBounds(176, 110, 154, 20);
        contentPane.add(comboBox);

        JLabel l3 = new JLabel("Cleaning Status");
        l3.setFont(new Font("Tahoma", Font.BOLD, 14));
        l3.setBounds(64, 150, 102, 22);
        contentPane.add(l3);

        comboBox_2 = new JComboBox<>(new String[]{"Cleaned", "Dirty"});
        comboBox_2.setBounds(176, 150, 154, 20);
        contentPane.add(comboBox_2);

        JLabel l4 = new JLabel("Price");
        l4.setFont(new Font("Tahoma", Font.BOLD, 14));
        l4.setBounds(64, 190, 102, 22);
        contentPane.add(l4);

        t2 = new JTextField();
        t2.setBounds(174, 190, 156, 20);
        contentPane.add(t2);

        JLabel l5 = new JLabel("Bed Type");
        l5.setFont(new Font("Tahoma", Font.BOLD, 14));
        l5.setBounds(64, 230, 102, 22);
        contentPane.add(l5);

        comboBox_3 = new JComboBox<>(new String[]{"Single Bed", "Double Bed", "Queen Bed", "King Bed"});
        comboBox_3.setBounds(176, 230, 154, 20);
        contentPane.add(comboBox_3);

        b1 = new JButton("Add");
        b1.addActionListener(this);
        b1.setBounds(64, 321, 111, 33);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        contentPane.add(b1);

        b2 = new JButton("Back");
        b2.addActionListener(this);
        b2.setBounds(198, 321, 111, 33);
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        contentPane.add(b2);

        contentPane.setBackground(Color.WHITE);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            String room = t4.getText();
            String available = (String) comboBox.getSelectedItem();
            String status = (String) comboBox_2.getSelectedItem();
            String price = t2.getText();
            String type = (String) comboBox_3.getSelectedItem();

            // Input validation
            if (room.trim().isEmpty() || price.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all required fields");
                return;
            }

            // Validate price
            try {
                double priceVal = Double.parseDouble(price);
                if (priceVal < 0) {
                    JOptionPane.showMessageDialog(null, "Price cannot be negative");
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid price. Please enter a numeric value.");
                return;
            }

            // Database insertion
            try {
                conn c = new conn();  // Create connection object
                String str = "INSERT INTO room (roomnumber, availability, cleaning_status, price, bed_type) VALUES (?, ?, ?, ?, ?)";

                // Create PreparedStatement using the Connection object (c.c)
                PreparedStatement pst = c.c.prepareStatement(str);  // Use 'c.c' instead of 'c.s' for Connection

                pst.setString(1, room);
                pst.setString(2, available);
                pst.setString(3, status);
                pst.setDouble(4, Double.parseDouble(price));
                pst.setString(5, type);

                int rowsAffected = pst.executeUpdate();  // Check how many rows were affected
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Room Successfully Added");
                    this.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add room. Please try again.");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error: Unable to add room details\n" + e.getMessage());
                e.printStackTrace();  // This will print the detailed exception stack trace for debugging
            }
        } else if (ae.getSource() == b2) {
            this.setVisible(false);
        }
    }
}

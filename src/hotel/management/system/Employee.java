package hotel.management.system;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import net.proteanit.sql.DbUtils;
import java.sql.*;

public class Employee extends JFrame {
    Connection conn = null;
    private JPanel contentPane;
    private JTable table;
    private JLabel lblNewLabel;
    private JLabel lblJob;
    private JLabel lblName;
    private JLabel lblDepartment;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Employee frame = new Employee();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void close() {
        this.dispose();
    }

    public Employee() throws SQLException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(430, 200, 1000, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());
        
        // Create the table and add it to a scroll pane
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        // Create the buttons panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        JButton btnLoadData = new JButton("Load Data");
        btnLoadData.setIcon(new ImageIcon("path/to/load-icon.png")); // Add icon path
        btnLoadData.setBackground(Color.BLACK);
        btnLoadData.setForeground(Color.WHITE);
        btnLoadData.addActionListener(e -> {
            try {
                conn c = new conn();
                String displayCustomersql = "select * from Employee";
                ResultSet rs = c.s.executeQuery(displayCustomersql);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        buttonPanel.add(btnLoadData);
        
        JButton btnExit = new JButton("Back");
        btnExit.setIcon(new ImageIcon("path/to/back-icon.png")); // Add icon path
        btnExit.setBackground(Color.BLACK);
        btnExit.setForeground(Color.WHITE);
        btnExit.addActionListener(e -> {
            new Reception().setVisible(true);
            setVisible(false);
        });
        buttonPanel.add(btnExit);
        
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        
        // Create labels and add them to the panel
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new GridLayout(1, 8)); // 8 columns for labels
        
        lblNewLabel = new JLabel("Name");
        lblJob = new JLabel("Age");
        lblName = new JLabel("Gender");
        lblDepartment = new JLabel("Job");
        JLabel l1 = new JLabel("Salary");
        JLabel l2 = new JLabel("Phone");
        JLabel l3 = new JLabel("Aadhar");
        JLabel l4 = new JLabel("Gmail");
        
        labelPanel.add(lblNewLabel);
        labelPanel.add(lblJob);
        labelPanel.add(lblName);
        labelPanel.add(lblDepartment);
        labelPanel.add(l1);
        labelPanel.add(l2);
        labelPanel.add(l3);
        labelPanel.add(l4);
        
        contentPane.add(labelPanel, BorderLayout.NORTH);
        
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }
}

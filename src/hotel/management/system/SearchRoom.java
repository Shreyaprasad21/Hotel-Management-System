package hotel.management.system;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchRoom extends JFrame {
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    private JPanel contentPane;
    private JTable table;
    Choice c1;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                SearchRoom frame = new SearchRoom();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void close() {
        this.dispose();
    }

    public SearchRoom() throws SQLException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(530, 200, 700, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(240, 248, 255)); // Light AliceBlue background

        JLabel lblSearchForRoom = new JLabel("Search For Room");
        lblSearchForRoom.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblSearchForRoom.setBounds(200, 10, 300, 40);
        contentPane.add(lblSearchForRoom);

        JLabel lblRoomAvailable = new JLabel("Room Bed Type:");
        lblRoomAvailable.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblRoomAvailable.setBounds(50, 70, 120, 30);
        contentPane.add(lblRoomAvailable);

        c1 = new Choice();
        c1.add("Single Bed");
        c1.add("Double Bed"); // Uncomment to include Double Bed
        c1.setBounds(180, 75, 120, 25);
        contentPane.add(c1);

        JCheckBox checkRoom = new JCheckBox("Only display Available");
        checkRoom.setBounds(320, 75, 200, 25);
        checkRoom.setBackground(contentPane.getBackground());
        checkRoom.setFont(new Font("Tahoma", Font.PLAIN, 16));
        contentPane.add(checkRoom);

        JLabel lblRoomType = new JLabel("Room Number");
        lblRoomType.setBounds(25, 140, 120, 30);
        lblRoomType.setFont(new Font("Tahoma", Font.PLAIN, 16));
        contentPane.add(lblRoomType);

        JLabel lblAvailability = new JLabel("Availability");
        lblAvailability.setBounds(180, 140, 120, 30);
        lblAvailability.setFont(new Font("Tahoma", Font.PLAIN, 16));
        contentPane.add(lblAvailability);

        JLabel lblPrice = new JLabel("Price");
        lblPrice.setBounds(400, 140, 120, 30);
        lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 16));
        contentPane.add(lblPrice);

        JLabel lblBedType = new JLabel("Bed Type");
        lblBedType.setBounds(580, 140, 120, 30);
        lblBedType.setFont(new Font("Tahoma", Font.PLAIN, 16));
        contentPane.add(lblBedType);

        JButton btnSearch = new JButton("Search");
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String SQL = "select * from Room where bed_type = '" + c1.getSelectedItem() + "'";
                String SQL2 = "select * from Room where availability = 'Available' AND bed_type = '" + c1.getSelectedItem() + "'";
                try {
                    conn c = new conn();
                    rs = c.s.executeQuery(SQL);
                    table.setModel(DbUtils.resultSetToTableModel(rs));

                    if (checkRoom.isSelected()) {
                        rs = c.s.executeQuery(SQL2);
                        table.setModel(DbUtils.resultSetToTableModel(rs));
                    }
                } catch (SQLException ss) {
                    ss.printStackTrace();
                }
            }
        });
        btnSearch.setBounds(150, 400, 120, 30);
        btnSearch.setBackground(new Color(34, 139, 34)); // Green background
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnSearch.setFocusPainted(false);
        btnSearch.setBorder(BorderFactory.createEtchedBorder());
        contentPane.add(btnSearch);

        JButton btnExit = new JButton("Back");
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Reception().setVisible(true);
                setVisible(false);
            }
        });
        btnExit.setBounds(300, 400, 120, 30);
        btnExit.setBackground(new Color(220, 20, 60)); // Crimson background
        btnExit.setForeground(Color.WHITE);
        btnExit.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnExit.setFocusPainted(false);
        btnExit.setBorder(BorderFactory.createEtchedBorder());
        contentPane.add(btnExit);

        table = new JTable();
        table.setBounds(0, 187, 700, 300);
        contentPane.add(table);

        getContentPane().setBackground(Color.WHITE);
    }
}

package login;
//tabel of the link

import java.util.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.sql.*;
import java.io.FileNotFoundException;


import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.ByteArrayOutputStream;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.*;

public class homepage extends JFrame {
    private JTable table;
    private DefaultTableModel model;
  
 
    public homepage() {
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/sys";
        String user = "root";
        String password = "Password";
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Query the database to retrieve the data
        ArrayList<Object[]> data = new ArrayList<>();
        String query = "SELECT * FROM link";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Object[] row = {rs.getInt(1), rs.getString(2), rs.getString(3)};
                data.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Create the JTable and TableModel
        String[] columns = {"ordinal", "reference","link"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        Color c2=new Color(243, 233, 159);
        table.setBackground(c2);
        JTableHeader th = table.getTableHeader();
        Color c3=new Color(255, 217,61);
        th.setBackground(c3);

        // Populate the TableModel with the retrieved data
        for (Object[] row : data) {
            model.addRow(row);
        }

        // Add the TableModel to the JTable and display the JTable
        getContentPane().add(new JScrollPane(table));
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new homepage();
    }
}
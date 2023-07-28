package login;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.sql.*;

public class Password_manager {
	public static void main(String[] args) {
		JFrame f= new JFrame();
		f.setTitle("Password Manager");
		f.setSize(2180,1000);            
		f.setVisible(true); 
		f.setLayout(new BorderLayout());
		JLabel background=new JLabel(new ImageIcon("C:\\Users\\91902\\Downloads\\pass2.png"));
		f.add(background);
		background.setLayout(new FlowLayout(FlowLayout.LEFT, 150, 250));
        JPanel Panel = new JPanel(new GridBagLayout());
        Color c=new Color(255, 241, 220);
        Panel.setBackground(c);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.insets = new Insets(15, 15, 15, 15);
        JLabel l1=new JLabel("Username:");
        l1.setFont(new Font("Arial", Font.BOLD, 28));
        constraints.gridx = 0;
        constraints.gridy = 0;
        Panel.add(l1, constraints);
        JTextField tf1 = new JTextField(32);
        tf1.setPreferredSize(new Dimension(tf1.getPreferredSize().width, 38));
        constraints.gridx = 1;
        constraints.gridy = 0;
        Panel.add(tf1, constraints);
        JLabel l3=new JLabel("Password:");
        l3.setFont(new Font("Arial", Font.BOLD, 28));
        constraints.gridx = 0;
        constraints.gridy = 1;
        Panel.add(l3, constraints);
        JPasswordField p1 = new JPasswordField(14);
        p1.setFont(new Font("Arial", Font.BOLD, 28));
        constraints.gridx = 1;
        constraints.gridy = 1;
        Panel.add(p1, constraints);  
        JLabel l2=new JLabel("URL:");
        l2.setFont(new Font("Arial", Font.BOLD, 28));
        constraints.gridx = 0;
        constraints.gridy = 2;
        Panel.add(l2, constraints);
        JTextField tf2 = new JTextField(32);
        tf2.setPreferredSize(new Dimension(tf2.getPreferredSize().width, 38));
        constraints.gridx = 1;
        constraints.gridy = 2;
        Panel.add(tf2, constraints);
        constraints.anchor = GridBagConstraints.CENTER;
        JButton Save = new JButton("Save");
        Save.setFont(new Font("Arial", Font.BOLD, 32));
        Save.setBackground(new Color(0, 153, 204));
        Save.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        Panel.add(Save, constraints);
        constraints.anchor = GridBagConstraints.CENTER;
        JButton Del = new JButton("Delete");
        Del.setFont(new Font("Arial", Font.BOLD, 32));
        Del.setBackground(new Color(0, 153, 204));
        Del.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.gridwidth = 2;
        Panel.add(Del, constraints);
       
        JPanel P = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.insets = new Insets(10, 10, 0, 0);

		
        
        
       
		
		ImageIcon i = new ImageIcon("C:\\Users\\91902\\Downloads\\10261330_back_buttons_multimedia_play_stop_icon.png");
		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton startButton = new JButton(i);
        topPanel.add(startButton);
        Color c2=new Color(255, 155,0);
        topPanel.setBackground(c2);
        f.add(topPanel, BorderLayout.NORTH);
        startButton.addActionListener(new ActionListener(){
			 @Override
			 public void actionPerformed(java.awt.event.ActionEvent evt){
				 f.dispose();
				
       
		       }
		    });
        
        
    	Save.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e) {
    			try {
    				Class.forName("com.mysql.cj.jdbc.Driver");
    				Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "Password");
    				String query = "insert into  pass values(?,?,?)";		
    				PreparedStatement pb = con.prepareStatement(query);
    				
    				pb.setString(1, tf1.getText());
    				pb.setString(2, p1.getText());
    				pb.setString(3, tf2.getText());
                    pb.executeUpdate();
                    JOptionPane.showMessageDialog(Save, "Password Saved Successfully");
    				pb.close();
    				con.close();
    			} catch (Exception e1) {
    				e1.printStackTrace();
    			}
    		}
    	});
    	Del.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e) {
    			try {
    				Class.forName("com.mysql.cj.jdbc.Driver");
    				Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "Password");
    				
    				String query = "delete from pass where username=?";		
    				PreparedStatement pb = con.prepareStatement(query);
    				
    				pb.setString(1, tf1.getText());
    				pb.executeUpdate();
    				JOptionPane.showMessageDialog(Del,"Password Deleted Successfully");
    				pb.close();
    				con.close();	
    			} catch (Exception e1) {
    				e1.printStackTrace();
    			}	
    		}
    	});
        background.add(Panel);
	    f.setSize(2180,1000);            
	    f.setVisible(true); 
	}   
}


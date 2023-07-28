package login;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;


public class login extends JFrame  {
    private static JPanel contentPane;
     static Connection con;
     static PreparedStatement ps;
   private static ResultSet rs;
  
    
public static void main(String[] args) {
	// TODO Auto-generated method stub
	 EventQueue.invokeLater(new Runnable() {
         public void run() {
             try {
                 login frame = new login();
                 frame.setVisible(true);
             } catch (Exception e) {
                 e.printStackTrace();
             }
         }
     });
	JFrame f = new JFrame();
	f.setContentPane(new JLabel(new ImageIcon("C:\\Users\\91902\\OneDrive\\Pictures\\Screenshots\\Screenshot (85).png")));
	f.getContentPane().setBackground(Color.white);
	
	JLabel l1 = new JLabel("SIGN IN");
	l1.setFont(new Font("Sitka Text", Font.BOLD, 40));
	l1.setBounds(350, 150, 200, 43);
	f.add(l1);
	
	JLabel title = new JLabel("Student Organizer");
	title.setBounds(200,0,1700,100);
	title.setFont(new Font("Sitka Text", Font.BOLD, 60));
	title.setBackground(Color.white);
	//f.add(title);
	
	JLabel loginid = new JLabel("Username:");
	loginid.setFont(new Font("Sitka Text", Font.BOLD, 30));
	loginid.setBounds(250, 250, 200, 30);
	f.add(loginid);
	
	JLabel pass = new JLabel ("Password:");
	pass.setBounds(250, 350, 200, 30);
	pass.setFont(new Font("Sitka Text", Font.BOLD, 30));
	f.add(pass);
	
	JTextField t1 = new JTextField();
	t1.setBounds(420, 250, 150, 30);
	f.add(t1);
	
	
	JPasswordField t2 = new JPasswordField();
	t2.setBounds(420, 350, 150, 30);
	f.add(t2);
	
	JButton b1 = new JButton("Login");
	 b1.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent e) {
             String userName = t1.getText();
             String password = t2.getText();
             
             
             try {
             	
             	if(t1 .getText().equals("")| t2.getText().equals(""))
 				{
 					JOptionPane.showMessageDialog(null, "Please fill complete information");
 				}
             	else {
                 Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/sys",
                     "root", "Password");

                 PreparedStatement st = (PreparedStatement) connection
                     .prepareStatement("Select name, password from signup where name=? and password=?");

                 st.setString(1, userName);
                 st.setString(2, password);
                 ResultSet rs = st.executeQuery();
                 if (rs.next()) {
                    // dispose();
                	 connection.close();
                    // UserHome ah = new UserHome(userName);
                    // ah.setTitle("Welcome");
                     //ah.setVisible(true);
                     JOptionPane.showMessageDialog(b1, "You have successfully logged in");
                     dashboard r=new dashboard();
         			 dashboard.main(null);
                     
                     
                 } else {
                     JOptionPane.showMessageDialog(b1, "Wrong Username & Password");
                 }
             }
             } catch (SQLException sqlException) {
                 sqlException.printStackTrace();
             }
         }
     });
	
	b1.setBounds(210, 450, 150, 60);
	//b1.setBackground(Color.blue);
	b1.setForeground( Color.blue);
	b1.setFont(new Font("Sitika Text", Font.BOLD, 30));

	f.add(b1);
	
	JLabel user = new JLabel("New User?");
	user.setFont(new Font("Script MT Bold", Font.BOLD, 30));
	user.setBounds(230, 600, 200, 30);
	f.add(user);
	
	JButton b2 = new JButton("Sign Up");
	b2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			
			registration r=new registration();
			registration.main(null);
		}
		});
	
	b2.setBounds(420, 600, 150, 50);
  // b2.setBackground(Color.blue);
	//b2.setForeground( Color.cyan);
	b2.setFont(new Font("Sitika Text", Font.BOLD, 25));
	f.add(b2);

   JButton b3 = new JButton("Clear");
   b3.addActionListener(new ActionListener() {
   	public void actionPerformed(ActionEvent e) {
   		t1.setText("");
   		t2.setText("");
   		
   	}
   });
	b3.setBounds(420, 450, 150, 60);

	b3.setFont(new Font("Sitka Text", Font.BOLD, 30));
	b3.setForeground( Color.blue);

	f.add(b3);
	
	  f.setSize(2180,1000);
	  f.setLayout(null);
	  f.setVisible(true);
	  
}
}

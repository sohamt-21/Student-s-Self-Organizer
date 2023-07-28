package login;
//sign up page
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class registration
{
	public static void main(String[] args) {
		
		
		JFrame f = new JFrame();
		f.setSize(2280,1000); 
		f.setBackground(new Color(255, 204, 255));
		f.getContentPane().setBackground(Color.blue);
		f.setTitle("Registration Form");
		
		JPanel p = new JPanel();
		p.setBackground(Color.white);
		p.setBounds(400, 100, 600, 630);
		f.add(p);
		p.setLayout(null);


		JLabel l1 = new JLabel("SIGN UP");
		l1.setForeground( Color.blue);
		l1.setFont(new Font("Sitka Text", Font.BOLD, 40));
		l1.setBounds(200, 40, 200, 43);
		p.add(l1);
		
		JLabel l2 = new JLabel("Please fill in this form to create an account!");
		l2.setFont(new Font("Sitka Text", Font.BOLD, 25));
		l2.setBounds(30, 90, 1000, 43);
		p.add(l2);
		
		JLabel name = new JLabel("Name:");
		name.setFont(new Font("Sitka Text", Font.BOLD, 30));
		name.setBounds(90, 160, 200, 30);
		p.add(name);
		
		JTextField t1 = new JTextField();
		t1.setBounds(250, 160, 150, 30);
		p.add(t1);
		
		JLabel phoneno = new JLabel("Phone No:");
		phoneno.setFont(new Font("Sitka Text", Font.BOLD, 30));
		phoneno.setBounds(90, 250, 200, 30);
		p.add(phoneno);
		

		JTextField t2 = new JTextField();
		t2.setBounds(250, 250, 150, 30);
		p.add(t2);
		
		JLabel emailid = new JLabel("Email ID:");
		emailid.setFont(new Font("Sitka Text", Font.BOLD, 30));
		emailid.setBounds(90, 350, 200, 35);
		p.add(emailid);
		

		JTextField t3 = new JTextField();
		t3.setBounds(250, 350, 150, 30);
		p.add(t3);
		
		JLabel pass = new JLabel("Password:");
		pass.setFont(new Font("Sitka Text", Font.BOLD, 30));
		pass.setBounds(90, 450, 200, 30);
		p.add(pass);
		
		JPasswordField p1 = new JPasswordField();
		p1.setBounds(250, 450, 150, 30);
		p.add(p1);

		JButton b1 = new JButton("Sign UP");
		  b1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if(t1.getText().equals("")| t2.getText().equals("")|t3.getText().equals("")|p1.getText().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Please fill complete information");
					}
					else {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "Password");
						PreparedStatement ps = con.prepareStatement("insert into signup(name,phoneno,emailid,password) values(?,?,?,?)");
						
						ps.setString(1, t1.getText());
						ps.setString(2, t2.getText());
						ps.setString(3, t3.getText());
						ps.setString(4, p1.getText());
						String name, password;
						  name=t1.getText();
						  password=p1.getText();
						
						
						  String selectQuery = "SELECT COUNT(*) FROM signup  WHERE name = ?";
			                PreparedStatement selectStmt = con.prepareStatement(selectQuery);
			                selectStmt.setString(1, name);
			                ResultSet rs = selectStmt.executeQuery();
			                rs.next();
			                int count = rs.getInt(1);

			                // if the username doesn't exist, insert the new user
			                if (count == 0) {
			                    ps.setString(1, name);
			                    ps.setString(2, password);
			                  //  pstmt.setString(3, name);
			                    ps.executeUpdate();
			                    String email = t3.getText();
				                boolean isValid = isValid(email);
				                if (isValid) {
				                	 JOptionPane.showMessageDialog(null, "New user created successfully.");
					                    dashboard r=new dashboard();
					         			dashboard.main(null);  
				                } else {
				                	JOptionPane.showMessageDialog(null, "Please enter valid email ");
				                }
			                    
			                    
			                   
			                } else {
			                    JOptionPane.showMessageDialog(null, "Error: username already exists.");
			                }
			               
			                
			                
			                
						ps.executeUpdate();
						con.close();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					
					
					
					
				}}
			});
		b1.setBounds(100, 520, 150, 60);
		
		b1.setBackground(Color.blue);
		b1.setForeground( Color.white);
		b1.setFont(new Font("Sitika Text", Font.BOLD, 30));
        p.add(b1);
        
        
        JButton b2 = new JButton("Reset");
        b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t1.setText("");
				t2.setText("");
				t3.setText("");
				p1.setText("");
				
			}
		});

		b2.setBounds(300, 520, 150, 60);
		b2.setBackground(Color.blue);
		b2.setForeground( Color.white);
		b2.setFont(new Font("Sitika Text", Font.BOLD, 30));
        p.add(b2);
		
		 f.setLayout(null);
		 f.setVisible(true);	
				
	}
	public static boolean isValid(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}

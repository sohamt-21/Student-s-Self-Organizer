package login;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class link{
	public static void main(String[] args) {
		JFrame f = new JFrame();
		
		
			f.getContentPane().setBackground(new Color(153, 255, 204));
			
		    f.getContentPane().setBackground(Color.white);

			f.setTitle("Add link");
			f.setContentPane(new JLabel(new ImageIcon("C:\\Users\\91902\\OneDrive\\Pictures\\Screenshots\\Screenshot (90).png")));//background picture
			f.setSize(4109, 6163);
			
			JPanel p = new JPanel();
		    p.setBackground(Color.white);
			p.setBounds(700, 100, 600, 650);
			f.add(p);
			p.setLayout(null);
			
		
			JLabel l = new JLabel(); 
	        l.setIcon(new ImageIcon("C:\\Users\\91902\\Downloads\\3099384_student_woman_icon.png"));//women picture
	        l.setBounds(50, 360, 200, 300);
	        f.add(l);
			   
			  
		    JLabel jl= new JLabel("You can add your link here !");
		    jl.setBounds(100,30,1000,70);
		    jl.setFont(new Font("Sitka Text", Font.BOLD, 30));
		    jl.setBackground(Color.yellow);
		    p.add(jl);  
		    

			JLabel name = new JLabel("Ordinal:");
			name.setFont(new Font("Sitka Text", Font.BOLD, 30));
			name.setBounds(140, 100, 300, 50);
			p.add(name);
			
			JTextField t1 = new JTextField();
			t1.setBounds(270, 110, 150, 30);
			p.add(t1);
			
			JLabel link = new JLabel("Reference:");
			link.setFont(new Font("Sitka Text", Font.BOLD, 30));
			link.setBounds(100, 180, 300, 50);
			p.add(link);
			
			JTextField t2 = new JTextField();
			t2.setBounds(270, 190, 150, 30);
			p.add(t2);
			

			JLabel re = new JLabel("link:");
			re.setFont(new Font("Sitka Text", Font.BOLD, 30));
			re.setBounds(200, 260, 300, 50);
			p.add(re);
			
			JTextField t3 = new JTextField();
			t3.setBounds(270, 270, 150, 30);
			p.add(t3);
			
			ImageIcon icon = new ImageIcon("C:\\Users\\91902\\Downloads\\10261330_back_buttons_multimedia_play_stop_icon.png");//back button
			
			JButton b4 = new JButton(icon); //back button
			b4.addActionListener(new ActionListener(){
				 @Override
				 public void actionPerformed(java.awt.event.ActionEvent evt){
					 f.dispose();   
			       }
			    });
			b4.setBounds(0,0,50,40);
			b4.setBackground(Color.white);
			f.add(b4);
			JButton b1 = new JButton("Save");
			b1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					
					
					
					try {
						
						
						if(t1 .getText().equals("")| t2.getText().equals("")| t3.getText().equals(""))
		 				{
		 					JOptionPane.showMessageDialog(null, "Please fill complete information");
		 				}
						
						
						else{Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "Password");
						PreparedStatement ps = con.prepareStatement("insert into link(id,stud_link,reference) values(?,?,?)");
						
						ps.setString(1, t1.getText());
						ps.setString(2, t2.getText());
						ps.setString(3, t3.getText());
				

						
						
						ps.executeUpdate();
						con.close();
						}
					}
						catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
			});
			b1.setBounds(250, 340, 150, 60);
			
			b1.setBackground(Color.blue);
			b1.setForeground( Color.white);
			b1.setFont(new Font("Sitika Text", Font.BOLD, 30));
	        p.add(b1);
	        
	        JButton b2 = new JButton("Delete");
	        b2.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		t1.setText("");
		    		t2.setText("");
		    		t3.setText("");
		    		
		    	}
		    });
			b2.setBounds(250, 430, 150, 60);
			
			b2.setBackground(Color.blue);
			b2.setForeground( Color.white);
			b2.setFont(new Font("Sitika Text", Font.BOLD, 30));
	        p.add(b2);
	       
	        JButton b3 = new JButton("View All");
	        b3.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {

		    		homepage  hp=new homepage();
					homepage.main(null);
		    		
		    	}
		    });
	        b3.setBounds(220, 530, 200, 60);
	        b3.setBackground(Color.blue);
			b3.setForeground( Color.white);
			b3.setFont(new Font("Sitika Text", Font.BOLD, 30));
	        p.add(b3);
		
		       
      
	    f.setSize(2180,1000);            
	    f.setVisible(true); 
	}   
}

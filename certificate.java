package login;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class certificate extends JFrame {
     
	static String s;
     
	public static void main(String[] args)  throws SQLException,ClassNotFoundException, FileNotFoundException{
		// TODO Auto-generated method stub
		
		JFrame f = new JFrame();
		f.setSize(2180,1000); 
		f.getContentPane().setBackground(new Color(173, 228, 219));
		
		
		JPanel p = new JPanel();
		p.setBackground(Color.white);
		p.setBounds(850, 100, 600, 630);
		f.add(p);
		p.setLayout(null);
	
		    JTextField textID = new JTextField();
		    textID.setBounds(280,130,250,40);
		    textID.setFont(new Font("Sitka Text", Font.BOLD, 26));
		    textID.setBackground(Color.white);
		    p.add(textID);
		    
		    JLabel l1 = new JLabel("ID:");
		    l1.setBounds(120, 130,100,40);
		    l1.setFont(new Font("Sitka Text", Font.BOLD, 35));
		    l1.setBackground(Color.gray);
		    l1.setForeground(Color.black);
		    p.add(l1);

		    JTextField textNAME = new JTextField();
		    textNAME.setBounds(280,220,250,40);
		    textNAME.setFont(new Font("Sitka Text", Font.BOLD, 26));
		    textNAME.setBackground(Color.white);
		    p.add(textNAME);
		    
		    JLabel l2 = new JLabel("Name:");
		    l2.setBounds(120,220,150,40);
		    l2.setFont(new Font("Sitka Text", Font.BOLD, 35));
		    l2.setBackground(Color.white);
		    l2.setForeground(Color.black);
		    p.add(l2);
		    
		    JLabel l4 = new JLabel("Add your certificates here!");
		    l4.setBounds(50, 0, 1200, 143);
		    l4.setFont(new Font("Sitka Text", Font.BOLD, 40));
		    l4.setBackground(Color.white);
		    p.add(l4);
		    
		
		   JTextArea area = new JTextArea(100, 100);
		   JScrollPane pane = new JScrollPane(area);
		   pane.setBounds(280, 310, 250, 70);
		   p.add(pane);
		   
		    JLabel l3 = new JLabel("Details:");
		    l3.setBounds(120,310,200,50);
		    l3.setFont(new Font("Sitka Text", Font.BOLD, 35));
		    l3.setBackground(Color.white);
		    l3.setForeground(Color.black);
		    
		    p.add(l3);
		    JLabel background=new JLabel(new ImageIcon("C:\\Users\\91902\\Downloads\\Mail-sent-pana-removebg-preview.png"));
			f.add(background);
			background.setBounds(50, 0, 800, 800);
		    
		
		ImageIcon icon = new ImageIcon("C:\\Users\\91902\\Downloads\\10261330_back_buttons_multimedia_play_stop_icon.png");
			
		JButton b3 = new JButton(icon); //back button
		b3.addActionListener(new ActionListener(){
			 @Override
			 public void actionPerformed(java.awt.event.ActionEvent evt){
				 f.dispose();   
		       }
		    });
		b3.setBounds(0,0,50,50);
		b3.setBackground(new Color(173, 228, 219));
		f.add(b3);
		
		JButton b1 = new JButton("Add");
		b1.addActionListener(new ActionListener(){
			 @Override
			 public void actionPerformed(java.awt.event.ActionEvent evt){
				 
		           try{
		        	   if(textID .getText().equals("")| textNAME.getText().equals("")| area.getText().equals(""))
		 				{
		 					JOptionPane.showMessageDialog(null, "Please fill complete information");
		 				}
		        	   else
		        	   {
		               Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sys","root","Supriya@1210");
		               PreparedStatement ps = con.prepareStatement("insert into student(id,name,Description,certificate) values(?,?,?,?)");
		              
		               InputStream is = new FileInputStream(new File(s));
		               
		              
		               ps.setString(1, textID.getText());
		               ps.setString(2, textNAME.getText());
		               ps.setString(3, area.getText());
		           
		              
		               ps.setBlob(4,is);
		               ps.executeUpdate();
		               JOptionPane.showMessageDialog(null, "added succesfully");
		           }
		           }
		               catch(Exception ex){
		               ex.printStackTrace();
		           }
		       }
		    });
		  b1.setBounds(220,530,250,70);
		  b1.setFont(new Font("Sitka Text", Font.BOLD, 26));
		  b1.setBackground(new Color(185, 233, 252));
		  b1.setForeground( Color.black);

		  p.add(b1);
		
		
		JButton b2 = new JButton("Upload");
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { JFileChooser fileChooser = new JFileChooser();
	         fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
	         // FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg","gif","png");
	          FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg","gif","png","doc","pdf","*.pdf");
	          fileChooser.addChoosableFileFilter(filter);
	          
	          
	          int result = fileChooser.showSaveDialog(null);
	          
	          
	          
	          if(result == JFileChooser.APPROVE_OPTION){
	              
	              try {
	            	File selectedFile = fileChooser.getSelectedFile();
					FileReader fr=new FileReader(selectedFile);
					String path = selectedFile.getAbsolutePath();
		              
		              s = path;
		              JOptionPane.showMessageDialog(null, "file is selected");
					
					
		              
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	              
	             
	              
	              
	               }
	          else if(result == JFileChooser.CANCEL_OPTION){
	        	 
	          }
	      }
	     });
	    
		 b2.setBounds(220, 430, 250, 70);
		 b2.setFont(new Font("Sitka Text", Font.BOLD, 26));
		
		 b2.setBackground(new Color(185, 233, 252));
		 b2.setForeground( Color.black);
		 p.add(b2);
	
		 f.setLayout(null);
		 f.setVisible(true);
	}

}






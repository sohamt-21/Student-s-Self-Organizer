package login;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public  class dashboard 
{
	public static void main(String[] args) {
		JFrame f = new JFrame();
		
		f.getContentPane().setBackground(new Color(185, 233, 252));
		f.setSize(2180,1000);
		f.setTitle("Dashboard");
		
		
		
		JLabel title = new JLabel("STUDI-FI:Student Task Manager");
		title.setBounds(200,0,1700,100);
		title.setFont(new Font("Sitka Text", Font.BOLD, 60));
		title.setBackground(Color.white);
		f.add(title);
		
		
		JTextField t1 = new JTextField();
		t1.setBounds(50, 120, 300, 50);
		t1.setBackground(Color.blue);
		f.add(t1);
		
		
		ImageIcon t = new ImageIcon("C:\\Users\\91902\\Downloads\\370089_clock_stopwatch_time_alarm_alert_icon.png");
		JButton b3 = new JButton("Timer",t);
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Pomodorotimer pm= new Pomodorotimer();
				Pomodorotimer.main(null);
			}});
		b3.setBounds(50, 170, 300, 50);
		
		b3.setFont(new Font("Sitka Text", Font.BOLD, 25));
		b3.setBackground(Color.white);
		f.add(b3);
		

		JTextField t2 = new JTextField();
		t2.setBounds(50, 300, 300, 50);
		t2.setBackground(Color.blue);
		f.add(t2);
		
		ImageIcon tdl = new ImageIcon("C:\\Users\\91902\\Downloads\\9104111_list_checklist_done_complete_task_icon.png");
		JButton b2 = new JButton("To Do List",tdl);
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ToDoList tdl= new ToDoList();
				ToDoList.main(null);
			}});
		b2.setBounds(50, 350, 300, 50);
		b2.setFont(new Font("Sitka Text", Font.BOLD, 25));
		b2.setBackground(Color.white);
		f.add(b2);
		
		JTextField t4 = new JTextField();
		t4.setBounds(50, 470, 300, 50);
		t4.setBackground(Color.blue);
		f.add(t4);
		
		ImageIcon c = new ImageIcon("C:\\Users\\91902\\Downloads\\5310246_certificate_degree_diploma_education_school_icon.png");
		JButton b4 = new JButton("Certificate",c);
		
		
		JLabel background=new JLabel(new ImageIcon("C:\\Users\\91902\\Downloads\\abcd-removebg-preview.png"));
		f.add(background);
		
		background.setBounds(850, 50, 700, 700);
		
		
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				certificate l=new certificate();
				try {
					certificate.main(null);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}});
		b4.setBounds(50, 520, 300, 50);
		b4.setFont(new Font("Sitka Text", Font.BOLD, 25));
		b4.setBackground(Color.white);
		f.add(b4);
       
		
		
		JTextField t3 = new JTextField();
		t3.setBounds(550, 300, 300, 50);
		t3.setBackground(Color.blue);
		f.add(t3);
		
		
		ImageIcon music = new ImageIcon("C:\\Users\\91902\\Downloads\\211867_note_music_icon.png");
		JButton b1 = new JButton("Music",music);
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dummymusic m= new dummymusic();
				try {
					dummymusic.main(null);
				} catch (UnsupportedAudioFileException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}});
		
		b1.setBounds(550, 350, 300, 50);
		b1.setFont(new Font("Sitka Text", Font.BOLD, 25));
		b1.setBackground(Color.white);
		f.add(b1);
		
		JTextField t5 = new JTextField();
		t5.setBounds(550, 120, 300, 50);
		t5.setBackground(Color.blue);
		f.add(t5);
		
		ImageIcon linkimage = new ImageIcon("C:\\Users\\91902\\Downloads\\9104220_link_permalink_chain_hyperlink_connect_icon.png");
		JButton b5 = new JButton("Link",linkimage);
		b5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				link l=new link();
				link.main(null);
			}});
		
		b5.setBounds(550, 170, 300, 50);
		b5.setFont(new Font("Sitka Text", Font.BOLD, 25));
		b5.setBackground(Color.white);
		f.add(b5);
		
		JTextField t6 = new JTextField();
		t6.setBounds(300, 650, 400, 50);
		t6.setBackground(Color.blue);
		f.add(t6);
		
		ImageIcon pm = new ImageIcon("C:\\Users\\91902\\Downloads\\9024718_key_light_icon.png");
		JButton b6 = new JButton("Password manager",pm);
		b6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Password_manager pm=new Password_manager();
				Password_manager.main(null);
			}});
		b6.setBounds(300, 700, 400, 50);
		b6.setFont(new Font("Sitka Text", Font.BOLD, 25));
		b6.setBackground(Color.white);
		f.add(b6);
		
		JTextField t7 = new JTextField();
		t7.setBounds(550, 470, 300, 50);
		t7.setBackground(Color.blue);
		f.add(t7);
		JButton b7 = new JButton("Safe Document");
		b7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Safe_docs ds=new Safe_docs();
				Safe_docs.main(null);
			}});
		b7.setBounds(550, 520, 300, 50);
		b7.setFont(new Font("Sitka Text", Font.BOLD, 25));
		b7.setBackground(Color.white);
		f.add(b7);
		

		JTextField t8 = new JTextField();
		t8.setBounds(550, 650, 300, 50);
		t8.setBackground(Color.blue);
		//f.add(t8);
		JButton b8 = new JButton("Login");
		b8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}});
		b8.setBounds(550, 700, 300, 50);
		b8.setFont(new Font("Sitka Text", Font.BOLD, 25));
		b8.setBackground(Color.white);
		//f.add(b8);
		
		f.setLayout(null);
		f.setVisible(true);
		
	}
	
	}


package login;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.plaf.ButtonUI;

import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Pomodorotimer {

    private JFrame frame;
    private JLabel timeLabel;
    private Timer timer;
    private int secondsRemaining;
    private boolean isRunning;

    public Pomodorotimer() {
    	
        frame = new JFrame("Pomodoro Timer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(2180, 1000);
       
        frame.getContentPane().setBackground(Color.BLACK); // Set background color to black
        frame.setLayout(null);

        timeLabel = new JLabel("00:00");
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 48));
        timeLabel.setForeground(Color.RED); // Set font color to white
        timeLabel.setBounds(400,10,300,100);
        frame.add(timeLabel);
        

        JButton startButton = new JButton("Start");
       // startButton.setUI(new ButtonUI()); // Set button shape to circle
        startButton.setBackground(Color.GREEN); // Set button color to green
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTimer();
            }
        });
        
        startButton.setBounds(530,40,100,40);
        frame.add(startButton);
        ImageIcon icon = new ImageIcon("C:\\Users\\91902\\Downloads\\10261330_back_buttons_multimedia_play_stop_icon.png");//back button
		
		JButton b4 = new JButton(icon); //back button
		b4.addActionListener(new ActionListener(){
			 @Override
			 public void actionPerformed(java.awt.event.ActionEvent evt){
				 frame.dispose();   
		       }
		    });
		b4.setBounds(10,10,40,40);
		b4.setBackground(Color.white);
		frame.add(b4);

        JButton stopButton = new JButton("Stop");
        //stopButton.setUI(new RoundedButtonUI()); // Set button shape to circle
        stopButton.setBackground(Color.GREEN); // Set button color to green
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopTimer();
            }
        });
        stopButton.setBounds(650,40,100,40);
       frame.add(stopButton);
   
        isRunning = false;
        secondsRemaining = 0;
    }

    private void startTimer() {
        if (!isRunning) {
            secondsRemaining = 1500; // 25 minutes in seconds
            timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    secondsRemaining--;
                    updateTimerLabel();
                    if (secondsRemaining == 0) {
                        stopTimer();
                        playAlarmSound(); // Play alarm sound when time is up
                        JOptionPane.showMessageDialog(frame, "Time's up!");
                    }
                }
            });
            timer.start();
            isRunning = true;
        }
    }

    private void stopTimer() {
        if (isRunning) {
            timer.stop();
            isRunning = false;
        }
    }

    private void updateTimerLabel() {
        int minutes = secondsRemaining / 60;
        int seconds = secondsRemaining % 60;
        String formattedTime = String.format("%02d:%02d", minutes, seconds);
        timeLabel.setText(formattedTime);
    }

    private void playAlarmSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("alarm.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        frame.setVisible(true);
        frame.setLayout(null);
    }

    public static void main(String[] args) {
    	Pomodorotimer pomodoroTimer = new Pomodorotimer();
        pomodoroTimer.run();
    }

    // ButtonUI class to create rounded buttons
    public class RoundedButtonUI extends javax.swing.plaf.basic.BasicButtonUI {
        @Override
        public void paint(Graphics g, JComponent c) {
        	super.paint(g, c);
        	Graphics2D g2 = (Graphics2D) g;
        	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        	g2.setColor(c.getBackground());
        	g2.fill(new RoundRectangle2D.Double(0, 0, c.getWidth() - 1, c.getHeight() - 1, c.getHeight(), c.getHeight()));
        	g2.setColor(c.getForeground());
        	g2.draw(new Ellipse2D.Double(0, 0, c.getWidth() - 1, c.getHeight() - 1));
        	}
        	}
            
        	}
    
   

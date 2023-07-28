
package songs;



import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class songs {
    private static List<File> playlist = new ArrayList<>();
    private static Clip clip;
    private static JLabel statusBar;

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        // Load the music files
        File file1 = new File("Song 1.wav");
        
        File file2 = new File("Song 2.wav");
        
        File file3 = new File("Song 3.wav");
        
        File file4 = new File("Song 4.wav");
        
        File file5 = new File("Song 5.wav");
        
        File file6 = new File("Song 6.wav");
        
        File file7 = new File("Song 7.wav");
        
        File file8 = new File("Song 8.wav");
        
        File file9 = new File("Song 9.wav");
        
        File file10 = new File("Song 10.wav");
        
        // Add the music files to the playlist
        playlist.add(file1);
        playlist.add(file2);
        playlist.add(file3);
        playlist.add(file4);
        playlist.add(file5);
        playlist.add(file6);
        playlist.add(file7);
        playlist.add(file8);
        playlist.add(file9);
        playlist.add(file10);
        playlist.add(file2);
        
        
        AudioInputStream audioStream1 = AudioSystem.getAudioInputStream(file1);
        clip = AudioSystem.getClip();
        clip.open(audioStream1);
        
        // Create the window
        JFrame frame = new JFrame("Music Player");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setResizable(true); // Disable window resizing
        
        // Create the playlist panel
        JPanel playlistPanel = new JPanel();
        playlistPanel.setBackground(new Color(0x57A0D3));
        playlistPanel.setPreferredSize(new Dimension(180, frame.getHeight()));
        frame.add(playlistPanel, BorderLayout.WEST);
        
        // Create the playlist buttons
        for (File file : playlist) {
            JButton playlistButton = new JButton(file.getName());
            playlistButton.addActionListener(e -> {
                try {
                    clip.stop();
                    clip.close();
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
                    clip.open(audioStream);
                    clip.start();
                    statusBar.setText("Now playing: " + file.getName());
                } catch (Exception ex) {
                    System.out.println("Error loading music file: " + ex.getMessage());
                }
            });
            playlistButton.setBackground(new Color(0x57A0D3)); // Set background color
            playlistButton.setForeground(Color.WHITE); // Song name color
            playlistPanel.add(playlistButton);
        }
        
        // Create the image panel
        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(Color.WHITE);
        imagePanel.setPreferredSize(new Dimension(frame.getWidth(), 400));
        frame.add(imagePanel, BorderLayout.CENTER);
        
         //Load the music image
        try {
            BufferedImage musicImage = ImageIO.read(new File(".jpg"));
            JLabel imageLabel = new JLabel(new ImageIcon(musicImage));
            imagePanel.add(imageLabel);
        } catch (Exception e) {
            System.out.println("Error loading music image: " + e.getMessage());
        }
        
        
        // Create the status bar
        statusBar = new JLabel("Now playing: " + playlist.get(0).getName());
        statusBar.setForeground(Color.WHITE);
        statusBar.setBackground(new Color(0x0e4D92));
        statusBar.setOpaque(true); // Make the status bar opaque
        frame.add(statusBar, BorderLayout.NORTH);
        
        // Create the circular buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        /*
        // Create custom button icons
        ImageIcon playIcon = new ImageIcon("\u25B6");
        ImageIcon pauseIcon = new ImageIcon("\u23F8\uFE0E");
        ImageIcon quitIcon = new ImageIcon("X");
        */
        JButton playButton = new JButton("\u25B6");
        JButton pauseButton = new JButton("\u23F8\uFE0E");
        JButton quitButton = new JButton("X");
        
        // Set button tooltips
        playButton.setToolTipText("\u25B6");
        pauseButton.setToolTipText("\u23F8\uFE0E");
        quitButton.setToolTipText("X");
        
        // Set button backgrounds
        playButton.setBackground(Color.white);
        pauseButton.setBackground(Color.white);
        quitButton.setBackground(Color.white);
        
        // Add action listeners to the buttons
        playButton.addActionListener(e -> {
            if (!clip.isRunning()) {
                clip.start();
                statusBar.setText("Now playing: " + playlist.get(playlist.indexOf(clip.getMicrosecondPosition())).getName());
            }
        });
        
        pauseButton.addActionListener(e -> {
            if (clip.isRunning()) {
                clip.stop();
                statusBar.setText("Paused: " + playlist.get(playlist.indexOf(clip.getMicrosecondPosition())).getName());
            }
        });
        
        quitButton.addActionListener(e -> {
            clip.stop();
            clip.close();
            System.exit(0);
        });
        
        // Add the buttons to the button panel
        buttonPanel.add(playButton);
        buttonPanel.add(pauseButton);
        buttonPanel.add(quitButton);
        
        // Add the button panel to the frame
        frame.add(buttonPanel, BorderLayout.SOUTH);
        
        // Make the frame visible
        frame.setVisible(true);
    }
}


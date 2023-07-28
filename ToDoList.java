package login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.swing.table.*;

public class ToDoList extends JFrame {
  public ToDoList() {
	  
    JFrame frame = new JFrame("To Do List");
    DefaultTableModel model = new DefaultTableModel();
    JTable table = new JTable();
    Object[] columns = {"Title", "Description", "Date", "Priority"};
    
    model.setColumnIdentifiers(columns);
    table.setModel(model);
    table.setAutoCreateRowSorter(true);
    table.setBorder(BorderFactory.createLineBorder(Color.black,1));
    
    table.setGridColor(Color.black);
    table.setShowGrid(true);
    JTextField title;
    
    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setForeground(new Color(0xA6D0DD));
    scrollPane.setBackground(new Color(0xA6D0DD));
    
    JPanel panel1 = new JPanel(new GridBagLayout());//left side 
    panel1.setBounds(0, 0, 200, 200);
    ImageIcon imageIcon = new ImageIcon("C:\\Users\\91902\\Downloads\\todolist.jpg");
    JLabel imageLabel = new JLabel(imageIcon);
    panel1.add(imageLabel);
    JPanel panel = new JPanel(new BorderLayout());//right panel
    panel.setBounds(600, 0, 800,800);
    panel.setBackground(new Color(0xA6D0DD));

    JButton add = new JButton("Add");
    add.setAlignmentX(Component.CENTER_ALIGNMENT);
    add.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        addTask(frame, model);
      }
    });
    add.setBounds(80, 600, 100, 50);
    add.setBackground(Color.blue);
	add.setForeground( Color.white);

    JButton edit = new JButton("Edit");
    edit.setAlignmentX(Component.CENTER_ALIGNMENT);
    edit.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        int i = table.getSelectedRow();
        if(i >= 0) {
          editTask(frame, model, i);
        } else {
          JOptionPane.showMessageDialog(panel, "You haven't chosen an entry to modify!", "Error", JOptionPane.ERROR_MESSAGE);
        }
      }
    });
    edit.setBounds(300, 600, 100, 50);
    edit.setBackground(Color.blue);
	edit.setForeground( Color.white);

	ImageIcon i = new ImageIcon("C:\\Users\\91902\\Downloads\\10261330_back_buttons_multimedia_play_stop_icon.png");
	JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JButton startButton = new JButton(i);
    topPanel.add(startButton);
    Color c2=new Color(255, 155,0);
    topPanel.setBackground(c2);
    frame.add(topPanel, BorderLayout.NORTH);
    startButton.addActionListener(new ActionListener(){
		 @Override
		 public void actionPerformed(java.awt.event.ActionEvent evt){
			 frame.dispose();
			
   
	       }
	    });

    JButton del = new JButton("Delete");
    del.setAlignmentX(Component.CENTER_ALIGNMENT);
    del.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        int i = table.getSelectedRow();
        if(i >= 0) {
          int delConfirm = JOptionPane.showConfirmDialog(panel, "Are you sure you want to delete this entry?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
          if(delConfirm == 0)
          {
            model.removeRow(i);
          }
        } else {
          JOptionPane.showMessageDialog(panel, "You haven't chosen an entry to delete!", "Error", JOptionPane.ERROR_MESSAGE);
        }
       }
    });
    del.setBounds(500, 600, 100, 50);
    del.setBackground(Color.blue);
	del.setForeground( Color.white);
    panel.add(add);
    panel.add(edit);
    panel.add(del);
    panel.add(scrollPane, BorderLayout.CENTER);
    JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel1, panel);
    frame.add(splitPane);
    frame.setSize(2100,1000);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
  
  public static void main(String[] args) {
	  
	  ToDoList todolist = new ToDoList();
  }

  public void addTask(JFrame frame, DefaultTableModel model) {
	  JPanel addPanel = new JPanel(new BorderLayout(5,5));
	  JPanel addLabel = new JPanel(new GridLayout(0,1,2,2));
    addLabel.add(new JLabel("Title*", SwingConstants.RIGHT));
    addLabel.add(new JLabel("Description", SwingConstants.RIGHT));
	  addLabel.add(new JLabel("Date", SwingConstants.RIGHT));
	  addLabel.add(new JLabel("Priority", SwingConstants.RIGHT));
    addPanel.add(addLabel, BorderLayout.CENTER);

    JPanel addControls = new JPanel(new GridLayout(0,1,2,2));
    JTextField title = new JTextField();
    addControls.add(title);
    JTextField description = new JTextField();
    addControls.add(description);
	  JTextField date = new JTextField();
	  addControls.add(date);
	  JTextField priority = new JTextField();
	  addControls.add(priority);
    addPanel.add(addControls, BorderLayout.CENTER);

    int addConfirm = JOptionPane.showConfirmDialog(frame, addPanel, "Add", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    if(addConfirm == JOptionPane.OK_OPTION) {
      if(!title.getText().isEmpty()) {
        Object[] row = new Object[4];
        row[0] = title.getText();
        row[1] = description.getText();
        row[2] = date.getText();
        row[3] = priority.getText();
        model.addRow(row);
        
        try		
		{
		     Class.forName("com.mysql.cj.jdbc.Driver");
		     Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/sys","root","");
		     Statement st = c.createStatement();
		     PreparedStatement ps = c.prepareStatement("insert into todo values(?,?,?,?)");
		     ps.setString(1,title.getText());
		     ps.setString(2,description.getText());
		     ps.setString(3,date.getText());
		     ps.setString(4,priority.getText());		     	
		     ps.executeUpdate();
		     c.close();
		     System.out.println("Data Insereted");

	} 
		catch (Exception e){
			System.out.println("Database not found");
		}
        
      } else {
        JOptionPane.showMessageDialog(null, "Title is empty!", "Error", JOptionPane.ERROR_MESSAGE);
      }
    }
  } 
  public void editTask(JFrame frame, DefaultTableModel model, int i) {
    JPanel editPanel = new JPanel(new BorderLayout(5,5));
    JPanel editLabel = new JPanel(new GridLayout(0,1,2,2));
    editLabel.add(new JLabel("Title*", SwingConstants.RIGHT));
    editLabel.add(new JLabel("Description", SwingConstants.RIGHT));
    editLabel.add(new JLabel("Date", SwingConstants.RIGHT));
    editLabel.add(new JLabel("Priority", SwingConstants.RIGHT));
    editPanel.add(editLabel, BorderLayout.WEST);

    JPanel editControls = new JPanel(new GridLayout(0,1,2,2));
    JTextField title = new JTextField();
    title.setText(model.getValueAt(i, 0).toString());
    editControls.add(title);
    JTextField description = new JTextField();
    description.setText(model.getValueAt(i, 1).toString());
    editControls.add(description);
    JTextField date = new JTextField();
    date.setText(model.getValueAt(i, 2).toString());
    editControls.add(date);
    JTextField priority = new JTextField();
    priority.setText(model.getValueAt(i, 3).toString());
    editControls.add(priority);
    editPanel.add(editControls, BorderLayout.CENTER);

    int editConfirm = JOptionPane.showConfirmDialog(frame, editPanel, "Edit", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    if(editConfirm == JOptionPane.OK_OPTION) {
      if(!title.getText().isEmpty()) {
        model.setValueAt(title.getText(), i, 0);
        model.setValueAt(description.getText(), i, 1);
        model.setValueAt(date.getText(), i, 2);
        model.setValueAt(priority.getText(), i, 3);
      } 
      else {
        JOptionPane.showMessageDialog(editPanel, "Title is empty!", "Error", JOptionPane.ERROR_MESSAGE);
      }
    }
  }
}

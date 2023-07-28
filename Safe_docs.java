package login;
import java.util.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.io.FileInputStream;
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
import java.io.FileReader;
import java.io.InputStream;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.ByteArrayOutputStream;
public class Safe_docs {
    static int serialNumber;
    static String s;
	public static void main(String[] args ) 
	{
		JFrame f= new JFrame();
		f.setTitle("SafeDocs");
		JPanel panel1 = new JPanel(new GridBagLayout());
		Color c=new Color(102, 255 ,102);
        TitledBorder br = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 3),"Upload Your Documents");
        br.setTitleFont(new Font("Arial", Font.BOLD, 14));
        panel1.setBorder(br);
        panel1.setBackground(c);
       ImageIcon icon = new ImageIcon("C:\\Users\\91902\\Downloads\\9026081_upload_simple_icon.png");
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.insets = new Insets(10, 10, 10, 10);
        JLabel l1=new JLabel("Document  Name:");
        l1.setFont(new Font("Arial", Font.BOLD, 14));
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel1.add(l1, constraints);
        JTextField tf1 = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 0;
        panel1.add(tf1, constraints);
        JLabel categ = new JLabel("Category:");
        categ.setFont(new Font("Arial", Font.BOLD, 14));
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel1.add(categ, constraints);  
        JComboBox category = new JComboBox<>(new String[]{"Educational", "Government ID", "Job","Personal","Others"});
        constraints.gridx = 1;
        constraints.gridy = 1;
        panel1.add(category, constraints); 
        JLabel validUpto = new JLabel("Valid Upto:");
        validUpto.setFont(new Font("Arial", Font.BOLD, 14));
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel1.add(validUpto, constraints);
        JSpinner validUptoPicker = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editor = new JSpinner.DateEditor(validUptoPicker, "dd/MM/yyyy");
        validUptoPicker.setEditor(editor);
        constraints.gridx = 1;
        constraints.gridy = 2;
        panel1.add(validUptoPicker, constraints);
        JCheckBox chkbox = new JCheckBox("I Acknowledge The Correctness of Documents");
        chkbox.setOpaque(false);
        chkbox.setFont(new Font("Arial", Font.BOLD, 14));
        chkbox.setForeground(Color.BLACK);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        panel1.add(chkbox, constraints);
        JButton uploadButton = new JButton("UPLOAD",icon);
        JFileChooser filechoose = new JFileChooser();
        uploadButton.setFont(new Font("Arial", Font.BOLD, 20));
        uploadButton.setBackground(new Color(0, 153, 204));
        uploadButton.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        panel1.add(uploadButton, constraints);
       
        
        //Right side panel
        JPanel panel2 = new JPanel((new BorderLayout()));
        ArrayList<Document> documents = new ArrayList<>();
        serialNumber = 1;
        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Sr. No.", "Document Name", "File Type", "Category", "Valid Upto"},50);
        JTable doclist = new JTable(tableModel);
        doclist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        Color c2=new Color(255, 198 ,0);
        doclist.setBackground(c2);
        doclist.setBorder(BorderFactory.createLineBorder(Color.black,3));
        JTableHeader th = doclist.getTableHeader();
        Color c3=new Color(58, 79 ,122);
        th.setBackground(c3);
        th.setForeground(Color.white);
        JButton viewButton = new JButton("View");
        JButton downloadButton = new JButton("Download");
        JButton deleteButton = new JButton("Delete");
        JScrollPane tableScrollPane = new JScrollPane(doclist);
        panel2.add(tableScrollPane, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(c2);
        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.black,3));
        buttonPanel.add(viewButton);
        buttonPanel.add(downloadButton);
        buttonPanel.add(deleteButton);
        viewButton.setFont(new Font("Arial", Font.BOLD, 20));
        viewButton.setBackground(new Color(0, 153, 204));
        viewButton.setForeground(Color.WHITE);
        downloadButton.setFont(new Font("Arial", Font.BOLD, 20));
        downloadButton.setBackground(new Color(0, 153, 204));
        downloadButton.setForeground(Color.WHITE);
        deleteButton.setFont(new Font("Arial", Font.BOLD, 20));
        deleteButton.setBackground(new Color(0, 153, 204));
        deleteButton.setForeground(Color.WHITE);
    
        
        
     
        uploadButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        	 String docName = tf1.getText();
             String Category = category.getSelectedItem().toString();
             Date validUpto = (Date) validUptoPicker.getValue();
      
             
             if (!docName.isEmpty() && !Category.isEmpty() && chkbox.isSelected()) {
                 int returnVal = filechoose.showOpenDialog(uploadButton);
                 if (returnVal == JFileChooser.APPROVE_OPTION) {
                     File file = filechoose.getSelectedFile();
                     //FileReader fr= new FileReader(file);
                     String fileType = getFileType(file);
                     String path = file.getAbsolutePath();
                     s= path;
                     documents.add(new Document(serialNumber++, docName, fileType, Category, validUpto, file));
                     updateTable();
                     tf1.setText("");
                     category.setSelectedIndex(0);
                     validUptoPicker.setValue(new Date());
                     chkbox.setSelected(false);
                 } else {
                     JOptionPane.showMessageDialog(uploadButton, "No file selected.", "Error", JOptionPane.ERROR_MESSAGE);
                 }
             } else {
                 JOptionPane.showMessageDialog(uploadButton, "Please fill all mandatory fields and accept the terms and conditions.", "Error", JOptionPane.ERROR_MESSAGE);
             }
        	
        	try{
        		Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "Password");
                PreparedStatement ps = con.prepareStatement("insert into upload values(?,?,?,?)");
               
               // InputStream is = new FileInputStream(s);
                ps.setString(1, docName);
                ps.setString(2, Category);
                ps.setDate(3,new java.sql.Date( validUpto.getTime()));
                //ps.setBlob(4,is);
                FileInputStream fis = new FileInputStream(s);
				ps.setBinaryStream(4, fis);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(uploadButton, "File Uploaded");
            }
                catch(Exception ex){
                ex.printStackTrace();
            }
        	}
        	
        	 void updateTable() {
                DefaultTableModel model = (DefaultTableModel) doclist.getModel();
                model.setRowCount(0);
                for (Document document : documents) {
                    Object[] row = {document.getSerialNumber(), document.getName(), document.getFileType(), document.getCategory(), document.getValidUpto()};
                    model.addRow(row);
                }
                tableModel.setRowCount(50);
            }
        	
             String getFileType(File file) {
                String name = file.getName();
                int lastDotIndex = name.lastIndexOf(".");
                if (lastDotIndex == -1) {
                    return "";
                }
                return name.substring(lastDotIndex + 1).toUpperCase();
            }
        });
        viewButton.addActionListener(new ActionListener(){
            	public void actionPerformed(ActionEvent e) {
            		int selectedRow = doclist.getSelectedRow();
                    if (selectedRow != -1) {
                        Document document = documents.get(selectedRow);
                        try {
                            Desktop.getDesktop().open(document.getFile());
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(viewButton, "Could not open file.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(viewButton, "Please select a document to view.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
            	}
           
            	});
        downloadButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		int selectedRow = doclist.getSelectedRow();
                if (selectedRow != -1) {
                    Document document = documents.get(selectedRow);
                    int returnVal = filechoose.showSaveDialog(downloadButton);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        File destFile = filechoose.getSelectedFile();
                        try {
                            FileInputStream inStream = new FileInputStream(document.getFile());
                            FileOutputStream outStream = new FileOutputStream(destFile);
                            byte[] buffer = new byte[1024];
                            int length;
                            while ((length = inStream.read(buffer)) > 0) {
                                outStream.write(buffer, 0, length);
                            }
                            inStream.close();
                            outStream.close();
                            JOptionPane.showMessageDialog(downloadButton, "File downloaded successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(downloadButton, "Could not download file.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(downloadButton, "Please select a document to download.", "Error", JOptionPane.ERROR_MESSAGE);
                }	
        	}
        	});
        
            
        deleteButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		int selectedRow = doclist.getSelectedRow();
                if (selectedRow != -1) {
                    int confirm = JOptionPane.showConfirmDialog(deleteButton, "Are you sure you want to delete the selected document?", "Confirm", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        documents.remove(selectedRow);
                        updateTable();
                    }
                } else {
                JOptionPane.showMessageDialog(deleteButton, "Please select a document to delete.", "Error", JOptionPane.ERROR_MESSAGE);
                }
        	}
        	void updateTable() {
                DefaultTableModel model = (DefaultTableModel) doclist.getModel();
                model.setRowCount(0);
                for (Document document : documents) {
                    Object[] row = {document.getSerialNumber(), document.getName(), document.getFileType(), document.getCategory(), document.getValidUpto()};
                    model.addRow(row);
                }
                tableModel.setRowCount(50);
            }
        	});
        
        
        panel2.add(buttonPanel, BorderLayout.SOUTH);
        f.setLayout(new GridLayout(1,2));  
        f.add(panel1);
        f.add(panel2);
        f.setSize(800, 500);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        
        }
}
class Document {
int serialNumber;
String name;
String fileType;
String category;
Date validUpto;
File file;
Document(int serialNumber, String name, String fileType, String category, Date validUpto, File file) {
    this.serialNumber = serialNumber;
    this.name = name;
    this.fileType = fileType;
    this.category = category;
    this.validUpto = validUpto;
    this.file = file;
}

public int getSerialNumber() {
    return serialNumber;
}

public String getName() {
    return name;
}

public String getFileType() {
	
    return fileType;
}

public String getCategory() {
    return category;
}

public Date getValidUpto() {
    return validUpto;
}

public File getFile() {
    return file;
}
}

package view;


import com.toedter.calendar.JDateChooser;
import database.Database;
import database.IDatabase;
import model.Customer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import static javax.swing.JOptionPane.showMessageDialog;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class addCustomer extends javax.swing.JInternalFrame {
    private final IDatabase database = Database.getDatabase();

    /**
     * Creates new form addCustomer
     */
    public addCustomer() {
        initComponents();
        autoID();
    }

    String path=null;
    byte[] userimage=null;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    public boolean canSubmitName(String name) {
        return name.matches("[a-zA-Z]+");
    }

    public boolean canSubmitPassportID(String PassportID) { return PassportID.matches("[a-zA-Z0-9]{6,9}");}
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rootLabel = new JPanel();
        firstNameLabel = new JLabel();
        lastNameLabel = new JLabel();
        nicNoLabel = new JLabel();
        passportIdLabel = new JLabel();
        addressLabel = new JLabel();
        txtlastname = new JTextField();
        txtfirstname = new JTextField();
        txtnic = new JTextField();
        txtpassport = new JTextField();
        jScrollPane1 = new JScrollPane();
        txtaddress = new JTextArea();
        customerIdLabel = new JLabel();
        nextCustomerIdLabel = new JLabel();
        jPanel2 = new JPanel();
        dobLabel = new JLabel();
        genderLabel = new JLabel();
        contactLabel = new JLabel();
        maleRadioButton = new JRadioButton();
        femaleRadioButton = new JRadioButton();
        txtcontact = new JTextField();
        txtphoto = new JLabel();
        browseButton = new JButton();
        addButton = new JButton();
        cancelButton = new JButton();

        rootLabel.setBackground(new Color(51, 0, 255));

        firstNameLabel.setFont(new java.awt.Font("Tahoma", Font.BOLD, 11)); // NOI18N
        firstNameLabel.setForeground(new Color(255, 255, 255));
        firstNameLabel.setText("FirstName");

        lastNameLabel.setFont(new java.awt.Font("Tahoma", Font.BOLD, 11)); // NOI18N
        lastNameLabel.setForeground(new Color(255, 255, 255));
        lastNameLabel.setText("LastName");

        nicNoLabel.setFont(new java.awt.Font("Tahoma", Font.BOLD, 11)); // NOI18N
        nicNoLabel.setForeground(new Color(255, 255, 255));
        nicNoLabel.setText("Nic No");

        passportIdLabel.setFont(new java.awt.Font("Tahoma", Font.BOLD, 11)); // NOI18N
        passportIdLabel.setForeground(new Color(255, 255, 255));
        passportIdLabel.setText("Passport ID");

        addressLabel.setFont(new java.awt.Font("Tahoma", Font.BOLD, 11)); // NOI18N
        addressLabel.setForeground(new Color(255, 255, 255));
        addressLabel.setText("Address");

        txtaddress.setColumns(20);
        txtaddress.setRows(5);
        jScrollPane1.setViewportView(txtaddress);

        GroupLayout jPanel1Layout = new GroupLayout(rootLabel);
        rootLabel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(firstNameLabel)
                                .addGap(47, 47, 47)
                                .addComponent(txtfirstname))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(addressLabel)
                                    .addComponent(passportIdLabel)
                                    .addComponent(nicNoLabel))
                                .addGap(38, 38, 38)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane1)
                                    .addComponent(txtpassport)
                                    .addComponent(txtnic))))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lastNameLabel)
                        .addGap(48, 48, 48)
                        .addComponent(txtlastname, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(firstNameLabel)
                    .addComponent(txtfirstname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lastNameLabel)
                    .addComponent(txtlastname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(nicNoLabel)
                    .addComponent(txtnic, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(passportIdLabel)
                    .addComponent(txtpassport, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(addressLabel)
                    .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );

        customerIdLabel.setFont(new java.awt.Font("Tahoma", Font.BOLD, 18)); // NOI18N
        customerIdLabel.setText("Customer ID");

        nextCustomerIdLabel.setFont(new java.awt.Font("Tahoma", Font.BOLD, 20)); // NOI18N
        nextCustomerIdLabel.setForeground(new Color(255, 0, 0));
        nextCustomerIdLabel.setText("jLabel7");
        nextCustomerIdLabel.setName("nextCustomerIdLabel");

        jPanel2.setBackground(new Color(51, 0, 255));

        dobLabel.setFont(new java.awt.Font("Tahoma", Font.BOLD, 11)); // NOI18N
        dobLabel.setForeground(new Color(255, 255, 255));
        dobLabel.setText("Date of Birth");

        genderLabel.setFont(new java.awt.Font("Tahoma", Font.BOLD, 11)); // NOI18N
        genderLabel.setForeground(new Color(255, 255, 255));
        genderLabel.setText("Gender");

        contactLabel.setFont(new java.awt.Font("Tahoma", Font.BOLD, 11)); // NOI18N
        contactLabel.setForeground(new Color(255, 255, 255));
        contactLabel.setText("Contact");

        maleRadioButton.setText("Male");

        femaleRadioButton.setText("Female");

        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(dobLabel)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(genderLabel)
                            .addComponent(contactLabel))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(maleRadioButton)
                                .addGap(18, 18, 18)
                                .addComponent(femaleRadioButton))
                            .addComponent(txtcontact))))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(dobLabel)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(genderLabel)
                    .addComponent(maleRadioButton)
                    .addComponent(femaleRadioButton))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(contactLabel)
                    .addComponent(txtcontact, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(108, Short.MAX_VALUE))
        );

        txtphoto.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 0, 0)));

        browseButton.setText("Browse");
        browseButton.addActionListener(this::onBrowseClicked);

        addButton.setText("Add");
        addButton.addActionListener(this::onAddClicked);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(this::onCancelClicked);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(customerIdLabel)
                        .addGap(50, 50, 50)
                        .addComponent(nextCustomerIdLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(rootLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtphoto, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(337, 337, 337)
                                        .addComponent(browseButton, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(addButton, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(customerIdLabel)
                    .addComponent(nextCustomerIdLabel))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(rootLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtphoto, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                            .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(browseButton, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(addButton, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                            .addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))))
                .addGap(49, 49, 49))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void autoID() {
        nextCustomerIdLabel.setText(database.getNextCustomerId());
    }

    private void onBrowseClicked(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
    
      
        try {
        JFileChooser picchooser = new JFileChooser();
       picchooser.showOpenDialog(null);
       File pic = picchooser.getSelectedFile();       
       FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images","png","jpg");
       picchooser.addChoosableFileFilter(filter);     
       path= pic.getAbsolutePath();
       BufferedImage img;                    
       img = ImageIO.read(picchooser.getSelectedFile());
       ImageIcon imageIcon = new ImageIcon(new
       ImageIcon(img).getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT));
              txtphoto.setIcon(imageIcon); 
              
              
             File image = new File(path);
            FileInputStream fis = new FileInputStream(image);
            ByteArrayOutputStream baos= new ByteArrayOutputStream();
            byte[] buff = new byte[1024];
            for(int readNum; (readNum=fis.read(buff)) !=-1 ; )
            {
                baos.write(buff,0,readNum);
            }
            userimage=baos.toByteArray();
              
              
              
        } catch (IOException ex) {
            Logger.getLogger(addCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void onAddClicked(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
        String id = nextCustomerIdLabel.getText();
        String firstname = "";
        String lastname = "";

        if (canSubmitName(txtlastname.getText()) && canSubmitName(txtlastname.getText())) {
            lastname = txtlastname.getText();
            firstname = txtfirstname.getText();
        } else if (!canSubmitName(txtlastname.getText()) && !canSubmitName(txtfirstname.getText())) {
            showMessageDialog(null, "Check your first name \n Check your last name");
            return;
        } else if (!canSubmitName(txtlastname.getText())) {
            showMessageDialog(null, "Check your last name");
            return;
        } else if (!canSubmitName(txtfirstname.getText())) {
            showMessageDialog(null, "Check your first name");
            return;
        }

         String nic = txtnic.getText(); 
        String passport = txtpassport.getText();
         String address = txtaddress.getText();
        
        DateFormat da = new SimpleDateFormat("yyyy-MM-dd");
        String date = da.format(txtdob.getDate());

        String Gender = (maleRadioButton.isSelected()) ? "Male" : "Female";
        
        String contact = txtcontact.getText();
         
        try {
            var customer = new Customer(id, firstname, lastname, nic, passport, address, date, Gender, Integer.valueOf(contact), userimage);

            database.saveCustomer(customer);

            showMessageDialog(null,"Registation Created");
        } catch (SQLException ex) {
            Logger.getLogger(addCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void onCancelClicked(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        
        this.hide();
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton browseButton;
    private JButton addButton;
    private JButton cancelButton;
    private JLabel firstNameLabel;
    private JLabel contactLabel;
    private JLabel lastNameLabel;
    private JLabel nicNoLabel;
    private JLabel passportIdLabel;
    private JLabel addressLabel;
    private JLabel customerIdLabel;
    private JLabel dobLabel;
    private JLabel genderLabel;
    private JPanel rootLabel;
    private JPanel jPanel2;
    private JScrollPane jScrollPane1;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JTextArea txtaddress;
    private JTextField txtcontact;
    private JTextField txtfirstname;
    private JLabel nextCustomerIdLabel;
    private JTextField txtlastname;
    private JTextField txtnic;
    private JTextField txtpassport;
    private JLabel txtphoto;
    // End of variables declaration//GEN-END:variables
    private JDateChooser txtdob;
}

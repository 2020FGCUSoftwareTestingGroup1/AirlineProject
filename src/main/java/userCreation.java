
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class userCreation extends JInternalFrame {

    /**
     * Creates new form userCreation
     */
    public userCreation() {
        initComponents();
        autoID();
    }
    Connection con;
    PreparedStatement pst;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rootPanel = new JPanel();
        userIdLabel = new JLabel();
        firstNameLabel = new JLabel();
        lastNameLabel = new JLabel();
        usernameLabel = new JLabel();
        passwordLabel = new JLabel();
        userIdInput = new JLabel();
        firstNameInput = new JTextField();
        lastNameInput = new JTextField();
        txtusername = new JTextField();
        addUserButton = new JButton();
        cancelButton = new JButton();
        passwordInput = new JPasswordField();

        rootPanel.setBorder(BorderFactory.createTitledBorder("User Creation"));

        userIdLabel.setFont(new Font("Tahoma", Font.BOLD, 12)); // NOI18N
        userIdLabel.setText("User ID");

        firstNameLabel.setFont(new Font("Tahoma", Font.BOLD, 12)); // NOI18N
        firstNameLabel.setText("FirstName");

        lastNameLabel.setFont(new Font("Tahoma", Font.BOLD, 12)); // NOI18N
        lastNameLabel.setText("LastName");

        usernameLabel.setFont(new Font("Tahoma", Font.BOLD, 12)); // NOI18N
        usernameLabel.setText("User Name");

        passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 12)); // NOI18N
        passwordLabel.setText("Password");

        userIdInput.setFont(new Font("Tahoma", Font.BOLD, 18)); // NOI18N
        userIdInput.setForeground(new Color(255, 0, 0));
        userIdInput.setText("jLabel6");

        addUserButton.setText("Add");
        addUserButton.addActionListener(this::addUser);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(this::cancel);

        GroupLayout jPanel1Layout = new GroupLayout(rootPanel);
        rootPanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(userIdLabel)
                    .addComponent(firstNameLabel)
                    .addComponent(lastNameLabel)
                    .addComponent(usernameLabel)
                    .addComponent(passwordLabel))
                .addGap(55, 55, 55)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(userIdInput)
                    .addComponent(firstNameInput)
                    .addComponent(lastNameInput)
                    .addComponent(txtusername)
                    .addComponent(passwordInput, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(208, Short.MAX_VALUE)
                .addComponent(addUserButton, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(userIdLabel)
                                    .addComponent(userIdInput))
                                .addGap(37, 37, 37)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(firstNameLabel)
                                    .addComponent(firstNameInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(44, 44, 44)
                                .addComponent(lastNameLabel))
                            .addComponent(lastNameInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49)
                        .addComponent(usernameLabel))
                    .addComponent(txtusername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLabel)
                    .addComponent(passwordInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(addUserButton, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(rootPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(rootPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addUser(ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        String id = userIdInput.getText();
        String firstname = firstNameInput.getText();
        String lastname = lastNameInput.getText();
        String username = txtusername.getText();
        String password = passwordInput.getText();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/airline","root","");
            pst = con.prepareStatement("insert into user(id,firstname,lastname,username,password)values(?,?,?,?,?)");
            
            pst.setString(1, id);
            pst.setString(2, firstname);
            pst.setString(3, lastname);
            pst.setString(4, username);
            pst.setString(5, password);
            pst.executeUpdate();
            
            
            JOptionPane.showMessageDialog(null,"User Created");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(addflight.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(addflight.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void cancel(ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.hide();
    }//GEN-LAST:event_jButton2ActionPerformed

    
     public void autoID() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/airline","root","");
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("select MAX(id) from user");
            rs.next();
            rs.getString("MAX(id)");

            if(rs.getString("MAX(id)") == null) {
                userIdInput.setText("UO001");
            } else {
                long id = Long.parseLong(rs.getString("MAX(id)").substring(2,rs.getString("MAX(id)").length()));
                id++;
                userIdInput.setText("UO" + String.format("%03d", id));
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(addCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(addCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton addUserButton;
    private JButton cancelButton;
    private JLabel userIdLabel;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JPanel rootPanel;
    private JTextField firstNameInput;
    private JTextField lastNameInput;
    private JPasswordField passwordInput;
    private JLabel userIdInput;
    private JTextField txtusername;
    // End of variables declaration//GEN-END:variables
}

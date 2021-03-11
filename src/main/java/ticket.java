
import com.toedter.calendar.JDateChooser;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class ticket extends JInternalFrame {

    /**
     * Creates new form ticket
     */
    public ticket() {
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

        jPanel1 = new JPanel();
        txtsource = new JComboBox<>();
        txtdepart = new JComboBox<>();
        sourceLabel = new JLabel();
        departureLabel = new JLabel();
        searchFlightButton = new JButton();
        jScrollPane1 = new JScrollPane();
        jTable1 = new JTable();
        jLabel3 = new JLabel();
        txtticketno = new JLabel();
        jPanel3 = new JPanel();
        customerIdLabel = new JLabel();
        firstNameLabel = new JLabel();
        lastNameLabel = new JLabel();
        txtcustid = new JTextField();
        passportNumberLabel = new JLabel();
        txtfirstname = new JLabel();
        txtlastname = new JLabel();
        txtpassport = new JLabel();
        searchCustomerButton = new JButton();
        jPanel2 = new JPanel();
        flightNumberLabel = new JLabel();
        flightNameLabel = new JLabel();
        departTimeLabel = new JLabel();
        ticketClassLabel = new JLabel();
        priceLabel = new JLabel();
        seatCountLabel = new JLabel();
        flightNumberText = new JLabel();
        flightNameText = new JLabel();
        txtdept = new JLabel();
        ticketClassCombobox = new JComboBox<>();
        txtprice = new JTextField();
        txtseats = new JSpinner();
        bookTicketButton = new JButton();
        cancelTicketButton = new JButton();
        txttotal = new JLabel();

        jPanel1.setBorder(BorderFactory.createTitledBorder(null, "Select Country", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", Font.BOLD, 12))); // NOI18N

        txtsource.setModel(new DefaultComboBoxModel<>(new String[] { "India", "Srilanka", "Uk", "Usa", "Canada", "Chinna" }));

        txtdepart.setModel(new DefaultComboBoxModel<>(new String[] { "India\t", "Srilanka", "Uk", "Usa", "Canada", "Chinna" }));

        sourceLabel.setText("Source");

        departureLabel.setText("Departure");

        searchFlightButton.setText("Search");
        searchFlightButton.addActionListener(this::onSearchFlight);

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtsource, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addComponent(txtdepart, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(sourceLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(departureLabel)
                .addGap(87, 87, 87))
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(searchFlightButton)
                .addGap(49, 49, 49))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(sourceLabel)
                    .addComponent(departureLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsource, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdepart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(searchFlightButton)
                .addContainerGap())
        );

        jTable1.setModel(new DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Flight No", "Flight Name", "Source", "Departure", "Date", "DepTime", "ArrTime", "Charge"
            }
        ));
        jTable1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                onTableLeftClick(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel3.setText("Ticket No");

        txtticketno.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtticketno.setForeground(new java.awt.Color(255, 0, 0));
        txtticketno.setText("Ticket NO");

        customerIdLabel.setText("Customer ID");

        firstNameLabel.setText("FirstName");

        lastNameLabel.setText("LastName");

        passportNumberLabel.setText("Passportno");

        txtfirstname.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtfirstname.setForeground(new java.awt.Color(255, 0, 0));
        txtfirstname.setText("jLabel9");

        txtlastname.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtlastname.setForeground(new java.awt.Color(255, 0, 0));
        txtlastname.setText("jLabel10");

        txtpassport.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtpassport.setForeground(new java.awt.Color(255, 0, 0));
        txtpassport.setText("jLabel11");

        searchCustomerButton.setText("Search");
        searchCustomerButton.addActionListener(this::jButton4ActionPerformed);

        GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(customerIdLabel)
                        .addGap(34, 34, 34)
                        .addComponent(txtcustid, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addComponent(searchCustomerButton))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(firstNameLabel)
                            .addComponent(lastNameLabel)
                            .addComponent(passportNumberLabel))
                        .addGap(56, 56, 56)
                        .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(txtpassport)
                            .addComponent(txtlastname)
                            .addComponent(txtfirstname))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(customerIdLabel)
                    .addComponent(txtcustid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchCustomerButton))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(firstNameLabel)
                    .addComponent(txtfirstname))
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lastNameLabel)
                    .addComponent(txtlastname))
                .addGap(36, 36, 36)
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(passportNumberLabel)
                    .addComponent(txtpassport))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        flightNumberLabel.setText("Flight no");

        flightNameLabel.setText("Filght Name");

        departTimeLabel.setText("Depart Time");

        ticketClassLabel.setText("Class");

        priceLabel.setText("Price");

        seatCountLabel.setText("Seats");

        flightNumberText.setFont(new java.awt.Font("Tahoma", Font.BOLD, 12)); // NOI18N
        flightNumberText.setForeground(new java.awt.Color(255, 0, 0));
        flightNumberText.setText("jLabel18");

        flightNameText.setFont(new java.awt.Font("Tahoma", Font.BOLD, 12)); // NOI18N
        flightNameText.setForeground(new java.awt.Color(255, 0, 0));
        flightNameText.setText("jLabel19");

        txtdept.setFont(new java.awt.Font("Tahoma", Font.BOLD, 12)); // NOI18N
        txtdept.setForeground(new java.awt.Color(255, 0, 0));
        txtdept.setText("jLabel20");

        ticketClassCombobox.setModel(new DefaultComboBoxModel<>(new String[] { "Economy", "Business" }));

        txtseats.addChangeListener(this::txtseatsStateChanged);

        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(departTimeLabel)
                        .addComponent(ticketClassLabel, GroupLayout.Alignment.LEADING)
                        .addComponent(priceLabel, GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(flightNumberLabel)
                            .addComponent(flightNameLabel)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(seatCountLabel)
                        .addGap(35, 35, 35)))
                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(flightNameText)
                            .addComponent(txtdept)
                            .addComponent(flightNumberText)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(ticketClassCombobox, 0, 116, Short.MAX_VALUE)
                            .addComponent(txtprice)
                            .addComponent(txtseats, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(151, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(flightNumberLabel)
                    .addComponent(flightNumberText))
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(flightNameLabel)
                    .addComponent(flightNameText))
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(departTimeLabel)
                    .addComponent(txtdept))
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(ticketClassLabel)
                    .addComponent(ticketClassCombobox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(priceLabel)
                    .addComponent(txtprice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(seatCountLabel)
                    .addComponent(txtseats, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bookTicketButton.setText("Book");
        bookTicketButton.addActionListener(this::onBookFlight);

        cancelTicketButton.setText("Cancel");
        cancelTicketButton.addActionListener(this::onCancelTicket);

        txttotal.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txttotal.setForeground(new java.awt.Color(255, 0, 0));
        txttotal.setText("jLabel4");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(jLabel3))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtticketno)))
                        .addGap(41, 41, 41)
                        .addComponent(jPanel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(31, 31, 31))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 534, GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(207, 207, 207)
                                .addComponent(txttotal, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(bookTicketButton, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cancelTicketButton, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txtticketno))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(txttotal, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(bookTicketButton, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                            .addComponent(cancelTicketButton, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void onSearchFlight(ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        
        String source = txtsource.getSelectedItem().toString().trim();
        String depart = txtdepart.getSelectedItem().toString().trim();
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
             con = DriverManager.getConnection("jdbc:mysql://localhost/airline","root","");
             pst = con.prepareStatement("SELECT * from flight WHERE source = ? and depart = ?");
             
             pst.setString(1, source);
             pst.setString(2, depart);
             ResultSet rs = pst.executeQuery();
             
             ResultSetMetaData rsm = rs.getMetaData();
             int c;
             c = rsm.getColumnCount();
             
             DefaultTableModel Df = (DefaultTableModel)jTable1.getModel();
             Df.setRowCount(0);
             
             while(rs.next()) {
                 Vector<String> v2 = new Vector<>();
                 
                 for(int i = 1; i<= c; i ++) {
                     v2.add(rs.getString("id"));
                  v2.add(rs.getString("flightname"));
                  v2.add(rs.getString("source"));
                  v2.add(rs.getString("depart"));
                  v2.add(rs.getString("date"));
                  v2.add(rs.getString("deptime"));
                  v2.add(rs.getString("arrtime"));
                  v2.add(rs.getString("flightcharge"));
                 }
                 
                 Df.addRow(v2);
                 
              
                 
                 
             }
             
             
             
             
             
             
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ticket.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ticket.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        
        
        
        
        
        
    }//GEN-LAST:event_jButton3ActionPerformed

    
     public void autoID()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/airline","root","");
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("select MAX(id) from ticket");
            rs.next();
            rs.getString("MAX(id)");
            if(rs.getString("MAX(id)") == null)
            {
                txtticketno.setText("TO001");
            }
            else
            {
                long id = Long.parseLong(rs.getString("MAX(id)").substring(2,rs.getString("MAX(id)").length()));
                id++;
                 txtticketno.setText("TO" + String.format("%03d", id));
                
                
            }
            
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(addCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(addCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private void jButton4ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
          String id = txtcustid.getText();
        
        
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/airline","root","");
            pst = con.prepareStatement("select * from customer where id = ?");
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            
            if(rs.next() == false)
            {
                JOptionPane.showMessageDialog(this, "Record not Found");
            }
            else
            {
                 String fname = rs.getString("firstname");
                 String lname = rs.getString("lastname");
               
                 String passport = rs.getString("passport");
        
                 
                 txtfirstname.setText(fname.trim());
                 txtlastname.setText(lname.trim());
               
                  txtpassport.setText(passport.trim());


            
        } 
            
      
                
            } catch (ClassNotFoundException ex) {
            Logger.getLogger(ticket.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ticket.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void onTableLeftClick(MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        
       DefaultTableModel Df = (DefaultTableModel)jTable1.getModel();
       
       int selectIndex = jTable1.getSelectedRow();
       
       
       flightNumberText.setText(Df.getValueAt(selectIndex, 0).toString());
       flightNameText.setText(Df.getValueAt(selectIndex, 1).toString());
       txtdept.setText(Df.getValueAt(selectIndex, 5).toString());
        txtprice.setText(Df.getValueAt(selectIndex, 7).toString());
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void txtseatsStateChanged(ChangeEvent evt) {//GEN-FIRST:event_txtseatsStateChanged
        // TODO add your handling code here:
        
        int price = Integer.parseInt(txtprice.getText());
        int qty = Integer.parseInt(txtseats.getValue().toString());
        
        int tot = price * qty;
        
        txttotal.setText(String.valueOf(tot));
        
        
        
    }//GEN-LAST:event_txtseatsStateChanged

    private void onBookFlight(ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        String ticketid = txtticketno.getText();
        String flightid = flightNumberText.getText();
        String custid = txtcustid.getText();
        String flightclass = ticketClassCombobox.getSelectedItem().toString().trim();
        String price = txtprice.getText();
        String seats = txtseats.getValue().toString();
        DateFormat da = new SimpleDateFormat("yyyy-MM-dd");
        String date = da.format(txtdate.getDate());

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/airline","root","");
            pst = con.prepareStatement("insert into ticket(id,flightid,custid,class,price,seats,date)values(?,?,?,?,?,?,?)");
            
            pst.setString(1, ticketid);
            pst.setString(2, flightid);
            pst.setString(3, custid);          
            pst.setString(4, flightclass);
            pst.setString(5, price);
            pst.setString(6, seats);
            pst.setString(7, date);
         
           
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Ticket Booked");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(addflight.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(addflight.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void onCancelTicket(ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.hide();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JLabel flightNameText;
    private JLabel flightNumberText;
    private JButton bookTicketButton;
    private JButton cancelTicketButton;
    private JButton searchFlightButton;
    private JButton searchCustomerButton;
    private JLabel sourceLabel;
    private JLabel flightNumberLabel;
    private JLabel flightNameLabel;
    private JLabel departTimeLabel;
    private JLabel ticketClassLabel;
    private JLabel priceLabel;
    private JLabel seatCountLabel;
    private JLabel departureLabel;
    private JLabel jLabel3;
    private JLabel customerIdLabel;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel passportNumberLabel;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JScrollPane jScrollPane1;
    private JTable jTable1;
    private JComboBox<String> ticketClassCombobox;
    private JTextField txtcustid;
    private JComboBox<String> txtdepart;
    private JLabel txtdept;
    private JLabel txtfirstname;
    private JLabel txtlastname;
    private JLabel txtpassport;
    private JTextField txtprice;
    private JSpinner txtseats;
    private JComboBox<String> txtsource;
    private JLabel txtticketno;
    private JLabel txttotal;
    // End of variables declaration//GEN-END:variables
    private JDateChooser txtdate;
}

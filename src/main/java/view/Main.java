package view;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.swing.*;

public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        setName("mainFrame");
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        customerRootMenu = new javax.swing.JMenu();
        addCustomerMenuItem = new javax.swing.JMenuItem();
        searchCustomerMenuItem = new javax.swing.JMenuItem();
        ticketRootMenuItem = new javax.swing.JMenu();
        bookTicketMenuItem = new javax.swing.JMenuItem();
        ticketReportMenuItem = new javax.swing.JMenuItem();
        flightRootMenuItem = new javax.swing.JMenu();
        addFlightMenuItem = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        userCreationMenuItem = new javax.swing.JMenuItem();


        ticketRootMenuItem.setName("TicketMenuItem");
        ticketReportMenuItem.setName("TicketReportItem");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1366, 768));

        GroupLayout jDesktopPane1Layout = new GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );

        customerRootMenu.setText("Customer");
        customerRootMenu.setName("customerRootMenu");

        addCustomerMenuItem.setText("Add Customer");
        addCustomerMenuItem.setName("addCustomerMenuItem");
        addCustomerMenuItem.addActionListener(this::onAddCustomerMenuItemClicked);
        customerRootMenu.add(addCustomerMenuItem);
        searchCustomerMenuItem.setText("Search Customer");
        searchCustomerMenuItem.setName("searchCustomer");
        searchCustomerMenuItem.addActionListener(this::onSearchCustomerMenuItemClicked);
        customerRootMenu.add(searchCustomerMenuItem);

        jMenuBar1.add(customerRootMenu);

        ticketRootMenuItem.setText("Tickets");
        bookTicketMenuItem.setName("BookTicket");
        bookTicketMenuItem.setText("Book Ticket");
        bookTicketMenuItem.addActionListener(this::onBookTicketMenuItemClicked);
        ticketRootMenuItem.add(bookTicketMenuItem);

        ticketReportMenuItem.setText("Ticket Report");
        ticketReportMenuItem.addActionListener(this::onTicketReportMenuItemClick);
        ticketRootMenuItem.add(ticketReportMenuItem);

        jMenuBar1.add(ticketRootMenuItem);

        flightRootMenuItem.setText("Flight");
        flightRootMenuItem.setName("flightRootMenu");

        addFlightMenuItem.setText("Add Flight");
        addFlightMenuItem.addActionListener(this::onAddFlightMenuItemClicked);
        flightRootMenuItem.add(addFlightMenuItem);
        addFlightMenuItem.setName("addFlightMenuItem");

        jMenuBar1.add(flightRootMenuItem);

        jMenu4.setText("User");
        jMenu4.setName("userMenuItem");

        userCreationMenuItem.setText("UserCreation");
        userCreationMenuItem.setName("userCreationScreenButton");
        userCreationMenuItem.addActionListener(this::onUserCreationMenuItemClicked);
        jMenu4.add(userCreationMenuItem);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void onAddCustomerMenuItemClicked(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        
        addCustomer cus = new addCustomer();
        jDesktopPane1.add(cus);
        cus.setVisible(true);
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void onSearchCustomerMenuItemClicked(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        
        searchCustomer scus = new searchCustomer();
        jDesktopPane1.add(scus);
        scus.setVisible(true);
        
        
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void onAddFlightMenuItemClicked(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        addflight f = new addflight();
        jDesktopPane1.add(f);
        f.setVisible(true);
        
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void onBookTicketMenuItemClicked(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
         BookTicket t = new BookTicket();
        jDesktopPane1.add(t);
        t.setVisible(true);
        
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void onTicketReportMenuItemClick(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        
        ticketreport ti = new ticketreport();
        jDesktopPane1.add(ti);
        ti.setVisible(true);
        
        
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void onUserCreationMenuItemClicked(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        
         userCreation u = new userCreation();
        jDesktopPane1.add(u);
        u.setVisible(true);
        
        
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenu customerRootMenu;
    private javax.swing.JMenu ticketRootMenuItem;
    private javax.swing.JMenu flightRootMenuItem;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem addCustomerMenuItem;
    private javax.swing.JMenuItem searchCustomerMenuItem;
    private javax.swing.JMenuItem bookTicketMenuItem;
    private javax.swing.JMenuItem addFlightMenuItem;
    private javax.swing.JMenuItem userCreationMenuItem;
    private javax.swing.JMenuItem ticketReportMenuItem;
    // End of variables declaration//GEN-END:variables


}

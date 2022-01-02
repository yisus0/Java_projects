/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terminal;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.DefaultCaret;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

/**
 *
 * @author JPV
 */
public class terminalInterface extends javax.swing.JFrame {
    //static private Object JTextArea1;
    static public String string_textarea = ""; 
    
    static CommPortManager commPortManager = new CommPortManager((String data) -> {
        string_textarea += data;
        System.out.println(string_textarea);
        setTextAreaText();
    });
    
    static public void setTextAreaText () {
        jTextArea1.setText(string_textarea);
    }

    public void Interface_setup(){
        jTextArea1.setForeground(Color.WHITE);
        //jTextArea1.setBackground(Color.white);
        jTextArea1.setEditable(false);
        jComboBoxBaudrate.setSelectedItem("57600");
        /*String com_ports[] = {"--","COM4","COM15"};
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>( com_ports  );
        jComboBoxCOM.setModel( model );*/
    }
    /**
     * Creates new form terminalInterface
     */
    public terminalInterface() {
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jTextField1 = new javax.swing.JTextField();
        jButtonReset = new javax.swing.JButton();
        jComboBoxBaudrate = new javax.swing.JComboBox();
        jComboBoxCOM = new javax.swing.JComboBox();
        jCheckBoxAutoscroll = new javax.swing.JCheckBox();
        jButtonClean = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setBackground(new java.awt.Color(40, 40, 50));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 12)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jTextField1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 200, 200)));
        jTextField1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        jButtonReset.setText("Reset");
        jButtonReset.setToolTipText("");
        jButtonReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetActionPerformed(evt);
            }
        });

        jComboBoxBaudrate.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "4800", "9600", "38400", "57600", "115200" }));
        jComboBoxBaudrate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxBaudrateActionPerformed(evt);
            }
        });

        jComboBoxCOM.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "None" }));
        jComboBoxCOM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCOMActionPerformed(evt);
            }
        });

        jCheckBoxAutoscroll.setSelected(true);
        jCheckBoxAutoscroll.setText("Autoscroll");
        jCheckBoxAutoscroll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxAutoscrollActionPerformed(evt);
            }
        });

        jButtonClean.setText("Clean");
        jButtonClean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCleanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 876, Short.MAX_VALUE)
            .addComponent(jTextField1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jCheckBoxAutoscroll)
                .addGap(18, 18, 18)
                .addComponent(jButtonClean, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonReset, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jComboBoxBaudrate, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jComboBoxCOM, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE)
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxAutoscroll)
                    .addComponent(jComboBoxBaudrate, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxCOM, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonClean)
                    .addComponent(jButtonReset, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        string_textarea += evt.getActionCommand();
        if ( evt.getActionCommand().length() != 0 ) {
            string_textarea += "\r\n";
        }
        jTextArea1.setText(string_textarea);
        jTextField1.setText("");
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        /*if ( evt.getKeyCode() == 10 ) {
            System.out.println("INTRO");
        }*/
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jButtonResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetActionPerformed
        start_stream( (String)jComboBoxCOM.getSelectedItem(), (String)jComboBoxBaudrate.getSelectedItem() );
    }//GEN-LAST:event_jButtonResetActionPerformed

    private void jComboBoxBaudrateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxBaudrateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxBaudrateActionPerformed

    private void jCheckBoxAutoscrollActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxAutoscrollActionPerformed
        DefaultCaret caret = (DefaultCaret)jTextArea1.getCaret();
        if ( jCheckBoxAutoscroll.isSelected() ) {
            caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        }
        else {
            caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
        }
    }//GEN-LAST:event_jCheckBoxAutoscrollActionPerformed

    private void jComboBoxCOMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCOMActionPerformed
        /*System.out.println(jComboBoxCOM.getSelectedItem());
        System.out.println(jComboBoxBaudrate.getSelectedItem());*/
        
        start_stream( (String)jComboBoxCOM.getSelectedItem(), (String)jComboBoxBaudrate.getSelectedItem() );
        if ( commPortManager.get_current_port() != commPortManager.undefinedPort ) {
            jComboBoxCOM.setSelectedItem(commPortManager.get_current_port());
        }
    }//GEN-LAST:event_jComboBoxCOMActionPerformed

    private void jButtonCleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCleanActionPerformed
        clean();
    }//GEN-LAST:event_jButtonCleanActionPerformed

    static private void set_com_ports_list() {
        ArrayList<String> ports_list = commPortManager.searchForPorts();
        String ports_array[] = null;

        if ( ports_list.size() > 0 ) {
            ports_array = ports_list.toArray(new String[0]);
        }

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>( ports_array );
        jComboBoxCOM.setModel((ComboBoxModel) model);
    }
    
    private void start_stream(String port, String baud_rate_0 ) {

        if( port == commPortManager.undefinedPort ) {
            return;
        }
        if( commPortManager.get_current_port() != commPortManager.undefinedPort ) {
            commPortManager.disconnect();
        }
        clean();
        int baud_rate = Integer.parseInt(baud_rate_0);
        commPortManager.set_com_parameters( port, baud_rate );
        commPortManager.connect();
        boolean successful = commPortManager.initIOStream();
        if ( successful ) {
            commPortManager.initListener();
        }
        else {
            System.out.println("Error de puerto");
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        //  ESTILO NETBEANS
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(terminalInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(terminalInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(terminalInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(terminalInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        //</editor-fold>
        /*DefaultCaret caret_init = (DefaultCaret)jTextArea1.getCaret();
        caret_init.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);*/
        System.out.println("HOLA");
        terminalInterface test = new terminalInterface();
        test.Interface_setup();
        test.setVisible(true);

        set_com_ports_list();
        System.out.println("ADIOS");
    }
    
    public void clean(){
        string_textarea = "";
        jTextArea1.setText(string_textarea);
    }
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonClean;
    private javax.swing.JButton jButtonReset;
    private javax.swing.JCheckBox jCheckBoxAutoscroll;
    private javax.swing.JComboBox jComboBoxBaudrate;
    private static javax.swing.JComboBox jComboBoxCOM;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}



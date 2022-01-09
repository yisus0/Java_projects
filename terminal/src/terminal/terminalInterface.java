/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terminal;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

/**
 *
 * @author JPV
 */
public class terminalInterface extends javax.swing.JFrame {

    static boolean autoscroll = true;
    static boolean show_time_enabled = false;
    static boolean dark_theme = false;
    static String input_ending = "\r\n";
    static String input_ending_selection = "None";
    static String baud_rate = "115200";
    
    static ConfigurationManager configurationManager = new ConfigurationManager();
    static TextColorProcessor textColorProcessor = new TextColorProcessor();
    
    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
        getImage(ClassLoader.getSystemResource("resources/terminal.png"));
        return retValue;
     }

    static CommPortManager commPortManager = new CommPortManager((String data) -> {
        String string_textarea = "";
        if ( show_time_enabled ) {
            string_textarea += java.time.LocalTime.now() + "  |  ";
        }
        string_textarea += data;
        process_style( string_textarea );
    });
    
    static public void process_style ( String string_textarea ) {
        Style style = null;
        int index = textColorProcessor.get_match_word_index( string_textarea );
     
        if ( index >= 0 ) {
            setTextAreaText( textColorProcessor.get_init_substring( string_textarea, index ), null );
            style = textColorProcessor.style_match_substring( index );
            setTextAreaText( textColorProcessor.get_match_substring( string_textarea, index ), style );
            setTextAreaText( textColorProcessor.get_end_substring( string_textarea, index ), null );
        }
        else {
            setTextAreaText( string_textarea, style );
        }
    }
    
    static public void setTextAreaText ( String string_textarea, Style style ) {
        StyledDocument doc = jTextPane1.getStyledDocument();
        try {
            doc.insertString( doc.getLength(), string_textarea, style );
        } catch (BadLocationException ex) {
            Logger.getLogger(terminalInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Interface_setup(){
        jTextPane1.setEditable(false);
        jComboBoxBaudrate.setSelectedItem( baud_rate );
        jComboBoxInputEnding.setSelectedItem( input_ending_selection );
        init_listener_to_close();
        init_listener_jtextaerea();
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
        jTextField1 = new javax.swing.JTextField();
        jButtonReset = new javax.swing.JButton();
        jComboBoxBaudrate = new javax.swing.JComboBox();
        jComboBoxCOM = new javax.swing.JComboBox();
        jCheckBoxAutoscroll = new javax.swing.JCheckBox();
        jButtonClean = new javax.swing.JButton();
        jCheckBoxShowTime = new javax.swing.JCheckBox();
        jButtonClose = new javax.swing.JButton();
        jComboBoxInputEnding = new javax.swing.JComboBox();
        jCheckBoxDark = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());

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

        jCheckBoxShowTime.setText("Timestamp");
        jCheckBoxShowTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxShowTimeActionPerformed(evt);
            }
        });

        jButtonClose.setText("Close");
        jButtonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCloseActionPerformed(evt);
            }
        });

        jComboBoxInputEnding.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "None", "CR", "NL", "CR & NL" }));
        jComboBoxInputEnding.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxInputEndingActionPerformed(evt);
            }
        });

        jCheckBoxDark.setText("Dark");
        jCheckBoxDark.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxDarkActionPerformed(evt);
            }
        });

        jTextPane1.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 12)); // NOI18N
        jScrollPane1.setViewportView(jTextPane1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jCheckBoxAutoscroll)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBoxShowTime)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBoxDark)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 142, Short.MAX_VALUE)
                .addComponent(jButtonClean, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonReset, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonClose, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jComboBoxInputEnding, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jComboBoxBaudrate, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jComboBoxCOM, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jTextField1)
                .addGap(4, 4, 4))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonClose, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBoxInputEnding, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonReset, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonClean, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBoxBaudrate, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBoxCOM, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBoxAutoscroll)
                        .addComponent(jCheckBoxShowTime)
                        .addComponent(jCheckBoxDark)))
                .addGap(6, 6, 6))
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
        String input_data = evt.getActionCommand();
        input_data += input_ending;
        if ( evt.getActionCommand().length() != 0 && commPortManager.get_current_port() != commPortManager.undefinedPort ) {
            commPortManager.writeData( input_data );
        }
        jTextField1.setText("");
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed

    }//GEN-LAST:event_jTextField1KeyPressed

    private void jButtonResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetActionPerformed
        start_stream( (String)jComboBoxCOM.getSelectedItem(), (String)jComboBoxBaudrate.getSelectedItem() );
    }//GEN-LAST:event_jButtonResetActionPerformed

    private void jComboBoxBaudrateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxBaudrateActionPerformed
        baud_rate = (String) jComboBoxBaudrate.getSelectedItem();
    }//GEN-LAST:event_jComboBoxBaudrateActionPerformed

    private void jCheckBoxAutoscrollActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxAutoscrollActionPerformed
        autoscroll = jCheckBoxAutoscroll.isSelected();
        set_autoscroll();
    }//GEN-LAST:event_jCheckBoxAutoscrollActionPerformed

    private void jComboBoxCOMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCOMActionPerformed
        start_stream( (String)jComboBoxCOM.getSelectedItem(), (String)jComboBoxBaudrate.getSelectedItem() );
        if ( commPortManager.get_current_port() != commPortManager.undefinedPort ) {
            jComboBoxCOM.setSelectedItem(commPortManager.get_current_port());
        }
    }//GEN-LAST:event_jComboBoxCOMActionPerformed

    private void jButtonCleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCleanActionPerformed
        clean();
    }//GEN-LAST:event_jButtonCleanActionPerformed

    private void jCheckBoxShowTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxShowTimeActionPerformed
        if ( jCheckBoxShowTime.isSelected() ) {
            show_time_enabled = true;
        }
        else {
            show_time_enabled = false;
        }
    }//GEN-LAST:event_jCheckBoxShowTimeActionPerformed

    private void jButtonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCloseActionPerformed
        if( commPortManager.get_current_port() != commPortManager.undefinedPort ) {
            commPortManager.disconnect();
            jComboBoxCOM.setSelectedItem("--");
            clean();
        }
        this.setTitle( "" );
    }//GEN-LAST:event_jButtonCloseActionPerformed

    private void jComboBoxInputEndingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxInputEndingActionPerformed
        input_ending_selection = (String) jComboBoxInputEnding.getSelectedItem();
        set_input_ending();
    }//GEN-LAST:event_jComboBoxInputEndingActionPerformed

    private void jCheckBoxDarkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxDarkActionPerformed
        if ( jCheckBoxDark.isSelected() ) {
            dark_theme = true;
        }
        else {
            dark_theme = false;
        }
        set_dark_theme();
    }//GEN-LAST:event_jCheckBoxDarkActionPerformed

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
        int baud = Integer.parseInt(baud_rate_0);
        commPortManager.set_com_parameters( port, baud );
        if ( !commPortManager.connect() ) {
            this.setTitle( "ERROR" );
            jTextPane1.setText("ERROR. Failed to open " + port + "\r\n" );
            jComboBoxCOM.setSelectedItem("--");
            return;
        }
        boolean successful = commPortManager.initIOStream();
        if ( successful ) {
            commPortManager.initListener();
            this.setTitle( port );
        }
        else {
            System.out.println( "Port error" );
        }
    }
    
    public void clean(){
        jTextPane1.setText( "" );
    }
    
    /*Some piece of code*/
    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            System.exit(0);
    }
    
    public void init_listener_to_close() {
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                save_config();
            }
        });
    }

    public void init_listener_jtextaerea() {
        jTextPane1.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                if ( e.getButton() == MouseEvent.BUTTON1 ) {
                    autoscroll = false;
                }
                if ( e.getButton() == MouseEvent.BUTTON3 ) {
                    autoscroll = true;
                }
                jCheckBoxAutoscroll.setSelected( autoscroll );
                set_autoscroll();
            }
        });
    }
    
    static public void set_autoscroll() {
        DefaultCaret caret = (DefaultCaret)jTextPane1.getCaret();
        if ( autoscroll ) {
            caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
            jTextPane1.setCaretPosition(jTextPane1.getDocument().getLength());
        }
        else {
            caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
        }
    }

    static public void set_dark_theme() {
        if ( dark_theme ) {
           jTextPane1.setForeground(Color.WHITE);
           jTextPane1.setBackground(new Color(40,40,50));
        }
        else {
           jTextPane1.setForeground(Color.BLACK);
           jTextPane1.setBackground(Color.WHITE);
        }
    }
    
    static public void set_input_ending() {
        if ( input_ending_selection == "None" ){
            input_ending = "";
        }
        else if (input_ending_selection == "CR" ) {
            input_ending = "\r";
        }
        else if (input_ending_selection == "NL" ) {
            input_ending = "\n";
        }
        else {
            input_ending = "\r\n";
        }
    }
    
    static public void load_config() {
        set_autoscroll();
        String result = configurationManager.load_config();
        String [] result_array = result.split( "," );
        if (result_array.length != 4 ) {
            return;
        }

        show_time_enabled      = ( result_array[0].equals( "1" ) )?true:false;
        dark_theme             = ( result_array[1].equals( "1" ) )?true:false;
        input_ending_selection = result_array[2];
        baud_rate              = result_array[3];

        jCheckBoxShowTime.setSelected( show_time_enabled );

        jCheckBoxDark.setSelected( dark_theme );
        set_dark_theme();

        jComboBoxInputEnding.setSelectedItem( input_ending_selection );
        set_input_ending();

        jComboBoxBaudrate.setSelectedItem( baud_rate );
    }
    
    static public void save_config() {         
        configurationManager.save_config( (char)(show_time_enabled?'1':'0'), (char)(dark_theme?'1':'0'), input_ending_selection, baud_rate);
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
        System.out.println( "START" );
        terminalInterface test = new terminalInterface();
        test.Interface_setup();
        test.setVisible(true);
        load_config();
        set_com_ports_list();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonClean;
    private javax.swing.JButton jButtonClose;
    private javax.swing.JButton jButtonReset;
    private static javax.swing.JCheckBox jCheckBoxAutoscroll;
    private static javax.swing.JCheckBox jCheckBoxDark;
    private static javax.swing.JCheckBox jCheckBoxShowTime;
    private static javax.swing.JComboBox jComboBoxBaudrate;
    private static javax.swing.JComboBox jComboBoxCOM;
    private static javax.swing.JComboBox jComboBoxInputEnding;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private static javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables
}



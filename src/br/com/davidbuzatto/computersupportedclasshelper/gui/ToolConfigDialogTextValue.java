/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.davidbuzatto.computersupportedclasshelper.gui;

import br.com.davidbuzatto.computersupportedclasshelper.utils.DrawingConfigs;
import java.awt.Color;
import java.awt.Font;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author Prof. Dr. David Buzatto
 */
public class ToolConfigDialogTextValue extends javax.swing.JDialog {

    private DrawingConfigs dConfig;
    private KeyEventDispatcher keyEventDispatcher;
    private String text;
    
    /**
     * Creates new form ToolConfigDialogStrokeWidth
     */
    public ToolConfigDialogTextValue( java.awt.Frame parent, boolean modal, String text ) {
        
        super( parent, modal );
        initComponents();
        setBackground( new Color( 0, 0, 0, 0 ) );
        
        dConfig = DrawingConfigs.getInstance();
        dConfig.setProcessEventsMainWindow( false );
        
        tAreaText.setText( text );
        
        getRootPane().setDefaultButton( btnOK );
        
        keyEventDispatcher = new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent( KeyEvent e ) {
                if ( e.getKeyCode() == KeyEvent.VK_ESCAPE ) {
                    dispose();
                }
                return false;
            }
        };
        
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher( keyEventDispatcher );
        
        addWindowListener( new WindowAdapter() {
            @Override
            public void windowClosed( WindowEvent e ) {
                KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher( keyEventDispatcher );
            }
        });
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings( "unchecked" )
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        buttonGroupFontAlign = new javax.swing.ButtonGroup();
        containerPanel = new br.com.davidbuzatto.computersupportedclasshelper.gui.CustomDialogContainerPanel();
        lblText = new javax.swing.JLabel();
        scrollText = new javax.swing.JScrollPane();
        tAreaText = new javax.swing.JTextArea();
        btnOK = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        lblText.setFont(new Font( "Dialog", Font.BOLD, 12 ));
        lblText.setText("Text:");

        tAreaText.setColumns(20);
        tAreaText.setRows(5);
        scrollText.setViewportView(tAreaText);

        btnOK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/davidbuzatto/computersupportedclasshelper/gui/icons/accept.png"))); // NOI18N
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/davidbuzatto/computersupportedclasshelper/gui/icons/delete.png"))); // NOI18N
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout containerPanelLayout = new javax.swing.GroupLayout(containerPanel);
        containerPanel.setLayout(containerPanelLayout);
        containerPanelLayout.setHorizontalGroup(
            containerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(containerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblText)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, containerPanelLayout.createSequentialGroup()
                        .addComponent(btnOK)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel))
                    .addComponent(scrollText, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        containerPanelLayout.setVerticalGroup(
            containerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerPanelLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(lblText)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollText)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(containerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancel)
                    .addComponent(btnOK))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(containerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(containerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        text = tAreaText.getText();
        dispose();
    }//GEN-LAST:event_btnOKActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        text = null;
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    @Override
    public void dispose() {
        super.dispose();
        dConfig.setProcessEventsMainWindow( true );
    }

    public String getText() {
        return text;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOK;
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.ButtonGroup buttonGroupFontAlign;
    private br.com.davidbuzatto.computersupportedclasshelper.gui.CustomDialogContainerPanel containerPanel;
    private javax.swing.JLabel lblText;
    private javax.swing.JScrollPane scrollText;
    private javax.swing.JTextArea tAreaText;
    // End of variables declaration//GEN-END:variables
}

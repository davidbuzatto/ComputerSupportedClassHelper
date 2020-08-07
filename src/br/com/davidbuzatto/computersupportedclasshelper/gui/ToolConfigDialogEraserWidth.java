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
import javax.swing.JOptionPane;

/**
 *
 * @author David
 */
public class ToolConfigDialogEraserWidth extends javax.swing.JDialog {

    private DrawingConfigs dConfig;
    private KeyEventDispatcher keyEventDispatcher;
    
    /**
     * Creates new form ToolConfigDialogEraserWidth
     */
    public ToolConfigDialogEraserWidth( java.awt.Frame parent, boolean modal ) {
        
        super( parent, modal );
        initComponents();
        setBackground( new Color( 0, 0, 0, 0 ) );
        
        dConfig = DrawingConfigs.getInstance();
        dConfig.setProcessEventsMainWindow( false );
        
        fieldEraserWidth.setText( String.valueOf( dConfig.getEraserWidth() ) );
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

        containerPanel = new br.com.davidbuzatto.computersupportedclasshelper.gui.CustomDialogContainerPanel();
        lblEraserWidth = new javax.swing.JLabel();
        fieldEraserWidth = new javax.swing.JTextField();
        btnOK = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        lblEraserWidth.setFont(new Font( "Dialog", Font.BOLD, 12 ));
        lblEraserWidth.setForeground(new java.awt.Color(204, 204, 204));
        lblEraserWidth.setText("Eraser Width:");

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
                .addComponent(lblEraserWidth)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldEraserWidth, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnOK)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        containerPanelLayout.setVerticalGroup(
            containerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(containerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblEraserWidth)
                    .addComponent(fieldEraserWidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel)
                    .addComponent(btnOK))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
    }// </editor-fold>//GEN-END:initComponents

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        
        try {
            
            DrawingConfigs dc = DrawingConfigs.getInstance();
            double newEraserWith = Double.parseDouble( fieldEraserWidth.getText() );
            
            if ( newEraserWith <= 0 ) {
                CustomMessageAndConfirmDialog.showMessageDialog( this.getOwner(), 
                        "<html>The eraser width must be<br/> greater than zero!</html>", 
                        "ERROR", JOptionPane.ERROR_MESSAGE );
            } else {
                dc.setEraserWidth( newEraserWith );
                dispose();
            }
            
        } catch ( NumberFormatException exc ) {
            CustomMessageAndConfirmDialog.showMessageDialog( this.getOwner(), 
                    "<html>The eraser width must be<br/> a number!<html>", 
                    "ERROR", JOptionPane.ERROR_MESSAGE );
        }
        
    }//GEN-LAST:event_btnOKActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    @Override
    public void dispose() {
        super.dispose();
        dConfig.setProcessEventsMainWindow( true );
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOK;
    private br.com.davidbuzatto.computersupportedclasshelper.gui.CustomDialogContainerPanel containerPanel;
    private javax.swing.JTextField fieldEraserWidth;
    private javax.swing.JLabel lblEraserWidth;
    // End of variables declaration//GEN-END:variables
}

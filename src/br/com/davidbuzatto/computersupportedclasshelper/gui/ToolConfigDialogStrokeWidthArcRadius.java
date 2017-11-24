/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.davidbuzatto.computersupportedclasshelper.gui;

import br.com.davidbuzatto.computersupportedclasshelper.utils.DrawingConfigs;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author David
 */
public class ToolConfigDialogStrokeWidthArcRadius extends javax.swing.JDialog {

    private DrawingConfigs dConfig;
    
    /**
     * Creates new form ToolConfigDialog
     */
    public ToolConfigDialogStrokeWidthArcRadius( java.awt.Frame parent, boolean modal ) {
        
        super( parent, modal );
        initComponents();
        setBackground( new Color( 0, 0, 0, 0 ) );
        
        dConfig = DrawingConfigs.getInstance();
        dConfig.setProcessEventsMainWindow( false );
        
        fieldStrokeWidth.setText( String.valueOf( dConfig.getStrokeWidth() ) );
        fieldArcRadius.setText( String.valueOf( dConfig.getArcRadius() ) );
        getRootPane().setDefaultButton( btnOK );
        
        fieldStrokeWidth.addKeyListener( new KeyAdapter() {
            @Override
            public void keyReleased( KeyEvent e ) {
                if ( e.getKeyCode() == KeyEvent.VK_ESCAPE ) {
                    dispose();
                }
            }
        });
        
        fieldArcRadius.addKeyListener( new KeyAdapter() {
            @Override
            public void keyReleased( KeyEvent e ) {
                if ( e.getKeyCode() == KeyEvent.VK_ESCAPE ) {
                    dispose();
                }
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
        lblStrokeWidth = new javax.swing.JLabel();
        lblArcRadius = new javax.swing.JLabel();
        fieldStrokeWidth = new javax.swing.JTextField();
        fieldArcRadius = new javax.swing.JTextField();
        btnOK = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        lblStrokeWidth.setFont(new Font( "Dialog", Font.BOLD, 12 ));
        lblStrokeWidth.setForeground(new java.awt.Color(204, 204, 204));
        lblStrokeWidth.setText("Stroke Width:");

        lblArcRadius.setFont(new Font( "Dialog", Font.BOLD, 12 ));
        lblArcRadius.setForeground(new java.awt.Color(204, 204, 204));
        lblArcRadius.setText("Arc Radius:");

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
                    .addComponent(lblStrokeWidth, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblArcRadius, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(containerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fieldStrokeWidth, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldArcRadius, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGroup(containerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStrokeWidth)
                    .addComponent(fieldStrokeWidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(containerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblArcRadius)
                    .addComponent(fieldArcRadius, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOK)
                    .addComponent(btnCancel))
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
            double newStrokeWith = Double.parseDouble( fieldStrokeWidth.getText() );
            double newArcRadius = Double.parseDouble( fieldArcRadius.getText() );
            
            if ( newStrokeWith <= 0 ) {
                CustomMessageAndConfirmDialog.showMessageDialog( this.getOwner(), 
                        "The Stroke Width must be greater than zero!", 
                        "ERROR", JOptionPane.ERROR_MESSAGE );
            } else if ( newArcRadius < 0 ) {
                CustomMessageAndConfirmDialog.showMessageDialog( this.getOwner(), 
                        "The Arc Radius must be greater or equal to zero!", 
                        "ERROR", JOptionPane.ERROR_MESSAGE );
            } else {
                dc.setStrokeWidth( newStrokeWith );
                dc.setArcRadius( newArcRadius );
                dispose();
            }
            
        } catch ( NumberFormatException exc ) {
            CustomMessageAndConfirmDialog.showMessageDialog( this.getOwner(), 
                    "The Stroke Width and the Side Quantity must be a number!", 
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
    
    /**
     * @param args the command line arguments
     */
    public static void main( String args[] ) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for ( javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels() ) {
                if ( "Nimbus".equals( info.getName() ) ) {
                    javax.swing.UIManager.setLookAndFeel( info.getClassName() );
                    break;
                }
            }
        } catch ( ClassNotFoundException ex ) {
            java.util.logging.Logger.getLogger(ToolConfigDialogStrokeWidthArcRadius.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        } catch ( InstantiationException ex ) {
            java.util.logging.Logger.getLogger(ToolConfigDialogStrokeWidthArcRadius.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        } catch ( IllegalAccessException ex ) {
            java.util.logging.Logger.getLogger(ToolConfigDialogStrokeWidthArcRadius.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        } catch ( javax.swing.UnsupportedLookAndFeelException ex ) {
            java.util.logging.Logger.getLogger(ToolConfigDialogStrokeWidthArcRadius.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ToolConfigDialogStrokeWidthArcRadius dialog = new ToolConfigDialogStrokeWidthArcRadius( new javax.swing.JFrame(), true );
                dialog.addWindowListener( new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing( java.awt.event.WindowEvent e ) {
                        System.exit( 0 );
                    }
                } );
                dialog.setVisible( true );
            }
        } );
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOK;
    private br.com.davidbuzatto.computersupportedclasshelper.gui.CustomDialogContainerPanel containerPanel;
    private javax.swing.JTextField fieldArcRadius;
    private javax.swing.JTextField fieldStrokeWidth;
    private javax.swing.JLabel lblArcRadius;
    private javax.swing.JLabel lblStrokeWidth;
    // End of variables declaration//GEN-END:variables
}

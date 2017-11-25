/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.davidbuzatto.computersupportedclasshelper.tests;

import br.com.davidbuzatto.computersupportedclasshelper.gui.CustomMessageAndConfirmDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author David
 */
public class Tests {
    
    public static void main( String[] args ) {
        
        try {
            for ( javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels() ) {
                if ( "Nimbus".equals( info.getName() ) ) {
                    javax.swing.UIManager.setLookAndFeel( info.getClassName() );
                    break;
                }
            }
        } catch ( ClassNotFoundException ex ) {
            java.util.logging.Logger.getLogger(Tests.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        } catch ( InstantiationException ex ) {
            java.util.logging.Logger.getLogger(Tests.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        } catch ( IllegalAccessException ex ) {
            java.util.logging.Logger.getLogger(Tests.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        } catch ( javax.swing.UnsupportedLookAndFeelException ex ) {
            java.util.logging.Logger.getLogger(Tests.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        }
        
        CustomMessageAndConfirmDialog.showMessageDialog( null, "a", "bbbb", JOptionPane.INFORMATION_MESSAGE );
        CustomMessageAndConfirmDialog.showMessageDialog( null, "a", "bbbb", JOptionPane.QUESTION_MESSAGE );
        CustomMessageAndConfirmDialog.showMessageDialog( null, "a", "bbbb", JOptionPane.WARNING_MESSAGE );
        CustomMessageAndConfirmDialog.showMessageDialog( null, "a", "bbbb", JOptionPane.ERROR_MESSAGE );
        CustomMessageAndConfirmDialog.showMessageDialog( null, "a", "bbbb", JOptionPane.PLAIN_MESSAGE );
        System.exit( 0 );
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.davidbuzatto.computersupportedclasshelper;

import br.com.davidbuzatto.computersupportedclasshelper.gui.MainWindow;
import br.com.davidbuzatto.computersupportedclasshelper.utils.DrawingConfigs;
import com.formdev.flatlaf.FlatDarkLaf;

/**
 *
 * @author Prof. Dr. David Buzatto
 */
public class Main {

    private static DrawingConfigs dConfig;
    
    /**
     * @param args the command line arguments
     */
    public static void main( String args[] ) {
        
        FlatDarkLaf.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                dConfig = DrawingConfigs.getInstance();
                dConfig.load();
                new MainWindow( dConfig ).setVisible( true );
            }
        } );
    }
    
}

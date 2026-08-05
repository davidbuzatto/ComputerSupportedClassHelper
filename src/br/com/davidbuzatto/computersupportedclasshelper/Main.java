/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.davidbuzatto.computersupportedclasshelper;

import br.com.davidbuzatto.computersupportedclasshelper.gui.MainWindow;
import br.com.davidbuzatto.computersupportedclasshelper.utils.DrawingConfigs;
import com.formdev.flatlaf.FlatDarkLaf;
import java.io.File;

/**
 *
 * @author Prof. Dr. David Buzatto
 */
public class Main {

    private static DrawingConfigs dConfig;

    /**
     * @param args the command line arguments. A single argument is treated as a .csch
     * project file to open at startup -- this is how the OS launches the program when the
     * user double-clicks a .csch file associated with it by the installer.
     */
    public static void main( String args[] ) {

        FlatDarkLaf.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                dConfig = DrawingConfigs.getInstance();
                dConfig.load();

                MainWindow mainWindow = new MainWindow( dConfig );

                if ( args.length == 1 ) {
                    File fileToOpen = new File( args[0] );
                    if ( fileToOpen.exists() && fileToOpen.getName().toLowerCase().endsWith( ".csch" ) ) {
                        mainWindow.openProject( fileToOpen );
                    }
                }

                mainWindow.setVisible( true );

            }
        } );
    }

}

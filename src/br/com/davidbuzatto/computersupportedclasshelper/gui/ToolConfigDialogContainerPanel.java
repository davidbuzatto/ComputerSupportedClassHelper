/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.davidbuzatto.computersupportedclasshelper.gui;

import br.com.davidbuzatto.computersupportedclasshelper.utils.Constants;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 *
 * @author David
 */
public class ToolConfigDialogContainerPanel extends JPanel {

    public ToolConfigDialogContainerPanel() {
        setBackground( new Color( 0, 0, 0, 0 ) );
        setOpaque( false );
    }
    
    @Override
    protected void paintComponent( Graphics g ) {
        super.paintComponent( g );
        
        Graphics2D g2d = ( Graphics2D ) g.create();
        g2d.setRenderingHint( RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON );
        
        g2d.setPaint( Constants.COMPONENT_BACKGROUND_COLOR );
        g2d.fillRoundRect( 0, 0, getWidth(), getHeight(), 10, 10 );
        
        g2d.setPaint( Constants.COMPONENT_STROKE_COLOR );
        g2d.drawRoundRect( 0, 0, getWidth()-1, getHeight()-1, 10, 10 );
        
        g2d.dispose();
        
    }
    
    
    
}

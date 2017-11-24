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
public class CustomDialogContainerPanel extends JPanel {

    private Color backgroundColor;
    private Color strokeColor;
    private boolean forMessageAndInputDialogs;
    
    public CustomDialogContainerPanel() {
        this( Constants.COMPONENT_BACKGROUND_COLOR,
              Constants.COMPONENT_STROKE_COLOR, false );
    }

    public CustomDialogContainerPanel( Color backgroundColor, Color strokeColor, boolean forMessageAndInputDialogs ) {
        setBackground( new Color( 0, 0, 0, 0 ) );
        setOpaque( false );
        this.backgroundColor = backgroundColor;
        this.strokeColor = strokeColor;
        this.forMessageAndInputDialogs = forMessageAndInputDialogs;
    }
    
    @Override
    protected void paintComponent( Graphics g ) {
        super.paintComponent( g );
        
        Graphics2D g2d = ( Graphics2D ) g.create();
        g2d.setRenderingHint( RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON );
        
        g2d.setPaint( backgroundColor );
        g2d.fillRoundRect( 0, 0, getWidth(), getHeight(), 10, 10 );
        
        g2d.setPaint( strokeColor );
        g2d.drawRoundRect( 0, 0, getWidth()-1, getHeight()-1, 10, 10 );
        
        if ( forMessageAndInputDialogs ) {
            g2d.drawLine( 0, 26, getWidth(), 26 );
            g2d.drawLine( 0, getHeight() - 37, getWidth(), getHeight() - 37 );
        }
        
        g2d.dispose();
        
    }

    public void setBackgroundColor( Color backgroundColor ) {
        this.backgroundColor = backgroundColor;
    }

    public void setStrokeColor( Color strokeColor ) {
        this.strokeColor = strokeColor;
    }

    public void setForMessageAndInputDialogs( boolean forMessageAndInputDialogs ) {
        this.forMessageAndInputDialogs = forMessageAndInputDialogs;
    }    
    
}

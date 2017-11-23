/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.davidbuzatto.computersupportedclasshelper.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 *
 * @author David
 */
public class ColorPanel extends JPanel {
    
    private Color color;

    public ColorPanel() {
        this( Color.BLACK );
    }
    
    public ColorPanel( Color startColor ) {
        
        Dimension d = new Dimension( 20, 20 );
        
        setMinimumSize( d );
        setMaximumSize( d );
        setPreferredSize( d );
        
        setCursor( new Cursor( Cursor.HAND_CURSOR ) );
        setBackground( new Color( 0, 0, 0, 0 ) );
        color = startColor;
        
    }
    
    @Override
    protected void paintComponent( Graphics g ) {
        
        super.paintComponent( g );
        
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint( RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON );
        
        if ( color == null ) {
            
            g2d.setPaint( Color.WHITE );
            g2d.fillRoundRect( 0, 0, getWidth(), getHeight(), 10, 10 );
            
            g2d.setPaint( Color.RED );
            g2d.setStroke( new BasicStroke( 2 ) );
            g2d.drawLine( 3, getHeight()-3, getWidth()-3, 3 );
            
        } else {
            g2d.setPaint( color );
            g2d.fillRoundRect( 0, 0, getWidth(), getHeight(), 10, 10 );
        }
        
        g2d.setPaint( Color.BLACK );
        g2d.setStroke( new BasicStroke( 1 ) );
        g2d.drawRoundRect( 0, 0, getWidth()-1, getHeight()-1, 10, 10 );
        
        g2d.dispose();
        
    }

    public Color getColor() {
        return color;
    }

    public void setColor( Color color ) {
        this.color = color;
    }
    
}

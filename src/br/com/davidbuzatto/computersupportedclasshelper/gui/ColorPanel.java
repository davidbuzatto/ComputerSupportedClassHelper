/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.davidbuzatto.computersupportedclasshelper.gui;

import br.com.davidbuzatto.computersupportedclasshelper.utils.Utils;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 *
 * @author Prof. Dr. David Buzatto
 */
public class ColorPanel extends JPanel {
    
    private Color color;
    private String id;
    private int idWidth;
    
    private static final Font ID_FONT;
    private static final FontMetrics ID_FONT_METRICS;
    
    static {
        ID_FONT = new Font( "Dialog", Font.BOLD, 12 );
        ID_FONT_METRICS = Utils.getFontMetrics( ID_FONT );
    }

    public ColorPanel() {
        this( Color.BLACK );
    }
    
    public ColorPanel( Color startColor ) {
        
        Dimension d = new Dimension( 24, 24 );
        
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
            g2d.fillRoundRect( 2, 2, getWidth()-2, getHeight()-2, 10, 10 );
            
            g2d.setPaint( Color.RED );
            g2d.setStroke( new BasicStroke( 2 ) );
            g2d.drawLine( 3, getHeight()-3, getWidth()-3, 3 );
            
        } else {
            g2d.setPaint( color );
            g2d.fillRoundRect( 2, 2, getWidth()-2, getHeight()-2, 10, 10 );
        }
        
        g2d.setPaint( Color.BLACK );
        g2d.setStroke( new BasicStroke( 1 ) );
        g2d.drawRoundRect( 2, 2, getWidth()-3, getHeight()-3, 10, 10 );
        
        if ( id != null ) {
            if ( color == null ) {
                g2d.setColor( Color.WHITE.darker().darker() );
            } else {
                g2d.setColor( color.darker().darker() );
            }
            g2d.setFont( ID_FONT );
            g2d.drawString( id, getWidth() / 2 - idWidth / 2 + 1, getHeight() / 2 + 5 );
        }
        
        g2d.dispose();
        
    }

    public Color getColor() {
        return color;
    }

    public void setColor( Color color ) {
        this.color = color;
    }

    public void setId( String id ) {
        this.id = id;
        idWidth = ID_FONT_METRICS.stringWidth( id );
    }
    
}

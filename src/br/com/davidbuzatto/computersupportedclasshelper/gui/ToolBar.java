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
import java.awt.geom.Path2D;
import javax.swing.JToolBar;

/**
 *
 * @author David
 */
public class ToolBar extends JToolBar {
    
    public enum Side {
        LEFT,
        RIGHT,
        TOP,
        DOWN;
    }
    
    private Side side;
    
    public ToolBar( Side side ) {
        setBackground( new Color( 0, 0, 0, 0 ) );
        this.side = side;
    }
    
    @Override
    protected void paintComponent( Graphics g ) {
        
        super.paintComponent( g );
        
        Graphics2D g2d = ( Graphics2D ) g.create();
        g2d.setRenderingHint( RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON );
        
        Path2D p = new Path2D.Double();
        
        switch ( side ) {
            case LEFT:
                p.moveTo( -2, 0 );
                p.lineTo( 0, 0 );
                p.lineTo( getWidth() - 1, 7 );
                p.lineTo( getWidth() - 1, getHeight() - 7 );
                p.lineTo( 0, getHeight() );
                p.lineTo( -2, getHeight() );
                p.closePath();
                break;
            case RIGHT:
                p.moveTo( getWidth() + 2, 0 );
                p.lineTo( getWidth(), 0 );
                p.lineTo( 1, 7 );
                p.lineTo( 1, getHeight() - 7 );
                p.lineTo( getWidth(), getHeight() );
                p.lineTo( getWidth() + 2, getHeight() );
                p.closePath();
                break;
            case TOP:
                p.moveTo( 0, -2 );
                p.lineTo( 0, 0 );
                p.lineTo( 7, getHeight() - 1 );
                p.lineTo( getWidth() - 7 , getHeight() - 1 );
                p.lineTo( getWidth(), 0 );
                p.lineTo( getWidth(), -2 );
                p.closePath();
                break;
            case DOWN:
                break;
        }
        
        
        g2d.setPaint( Constants.COMPONENT_BACKGROUND_COLOR );
        g2d.fill( p );
        g2d.setPaint( Constants.COMPONENT_STROKE_COLOR );
        g2d.draw( p );
        
        g2d.dispose();
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.davidbuzatto.computersupportedclasshelper.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JMenuItem;

/**
 *
 * @author David
 */
public class ColoredJMenuItem extends JMenuItem {

    private Color color = Color.BLACK;
    
    @Override
    protected void paintComponent( Graphics g ) {
        
        super.paintComponent( g ); //To change body of generated methods, choose Tools | Templates.
        
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint( RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON );
        
        g2d.setColor( Color.WHITE );
        g2d.fillRoundRect( 7, 4, 15, 15, 5, 5 );
        
        g2d.setColor( color );
        g2d.fillRoundRect( 7, 4, 15, 15, 5, 5 );
        
        g2d.setColor( Color.BLACK );
        g2d.drawRoundRect( 7, 4, 15, 15, 5, 5 );
        
    }

    public void setColor( Color color ) {
        this.color = color;
    }
    
}

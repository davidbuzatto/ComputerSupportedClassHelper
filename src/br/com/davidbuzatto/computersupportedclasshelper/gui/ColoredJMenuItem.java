/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.davidbuzatto.computersupportedclasshelper.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.Icon;
import javax.swing.JMenuItem;

/**
 *
 * @author Prof. Dr. David Buzatto
 */
public class ColoredJMenuItem extends JMenuItem {

    private Color color = Color.BLACK;
    
    public ColoredJMenuItem() {
        setIcon( new ColorIcon() );
    }

    public void setColor( Color color ) {
        this.color = color;
    }
    
    private class ColorIcon implements Icon {

        @Override
        public void paintIcon( Component c, Graphics g, int x, int y ) {

            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint( RenderingHints.KEY_ANTIALIASING, 
                    RenderingHints.VALUE_ANTIALIAS_ON );
            
            x = 7;
            y = c.getHeight() / 2 - getIconHeight()/ 2;;
            
            g2d.setColor( Color.BLACK );
            g2d.fillRoundRect( x, y, 16, 16, 5, 5 );
            
            g2d.setColor( Color.WHITE );
            g2d.fillRoundRect( x+1, y+1, 14, 14, 5, 5 );

            g2d.setColor( color );
            g2d.fillRoundRect( x+1, y+1, 14, 14, 5, 5 );

        }

        @Override
        public int getIconWidth() {
            return 16;
        }

        @Override
        public int getIconHeight() {
            return 16;
        }

        public Color getColor() {
            return color;
        }

    }
    
}

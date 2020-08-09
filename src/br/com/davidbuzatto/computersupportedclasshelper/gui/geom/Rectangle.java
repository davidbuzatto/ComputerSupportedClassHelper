/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.davidbuzatto.computersupportedclasshelper.gui.geom;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

/**
 *
 * @author David
 */
public class Rectangle extends Shape implements Serializable, Cloneable {

    @Override
    public void draw( Graphics2D g2d ) {
        
        calculateDrawingBounds();
        g2d = (Graphics2D) g2d.create();
        
        if ( fillColor != null ) {
            g2d.setPaint(fillColor );
            g2d.fill( new Rectangle2D.Double( xStartD, yStartD, xEndD-xStartD, yEndD-yStartD ) );
        }
        
        if ( strokeColor != null ) {
            g2d.setPaint(strokeColor );
            g2d.setStroke( new BasicStroke( (float) strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND ) );
            g2d.draw( new Rectangle2D.Double( xStartD, yStartD, xEndD-xStartD, yEndD-yStartD ) );
        }
        
        drawSelection( g2d );
        g2d.dispose();
        
    }

    @Override
    public boolean intercepts( double x, double y ) {
        return x >= xStartD && x <= xEndD && y >= yStartD && y <= yEndD;
    }
    
    @Override
    public Rectangle clone() throws CloneNotSupportedException {
            
        Rectangle clone = new Rectangle();
        copyData( this, clone );
        
        return clone;
        
    }
    
}

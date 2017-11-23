/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.davidbuzatto.computersupportedclasshelper.gui.geom;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.io.Serializable;

/**
 *
 * @author David
 */
public class RoundRectangle extends Shape implements Serializable {

    protected double arcRadius;
    
    @Override
    public void draw( Graphics2D g2d ) {
        
        Graphics2D g2 = (Graphics2D) g2d.create();
        
        if ( fillColor != null ) {
            g2.setPaint(fillColor );
            g2.fill( new RoundRectangle2D.Double( xStart, yStart, xEnd-xStart, yEnd-yStart, arcRadius, arcRadius ) );
        }
        
        if ( strokeColor != null ) {
            g2.setPaint( strokeColor );
            g2.setStroke( new BasicStroke( (float) strokeWidth ) );
            g2.draw( new RoundRectangle2D.Double( xStart, yStart, xEnd-xStart, yEnd-yStart, arcRadius, arcRadius ) );
        }
        
        g2.dispose();
        
    }
    
    @Override
    public boolean intercepts( double x, double y ) {
        return x >= xStart && x <= xEnd && y >= yStart && y <= yEnd;
    }

    public void setArcRadius( double arcRadius ) {
        this.arcRadius = arcRadius;
    }
    
}

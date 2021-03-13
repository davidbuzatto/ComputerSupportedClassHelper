/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.davidbuzatto.computersupportedclasshelper.gui.geom;

import br.com.davidbuzatto.computersupportedclasshelper.utils.Constants;
import br.com.davidbuzatto.computersupportedclasshelper.utils.Utils;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.io.Serializable;

/**
 *
 * @author David
 */
public class LineSheet extends Shape implements Serializable, Cloneable {

    private static final long serialVersionUID = Constants.SERIAL_VERSION;
    
    private int distance;
    private static final Dimension screenSize = Utils.getScreenSize();
    
    public LineSheet() {
        //setDistanceTo30();
        //strokeColor = new Color( 0, 0, 0, 100 );
    }
    
    @Override
    public void draw( Graphics2D g2d ) {
        
        calculateDrawingBounds();
        g2d = (Graphics2D) g2d.create();
        
        if ( strokeColor != null ) {
            
            g2d.setPaint( strokeColor );
            
            for ( int i = 1; i * distance <= screenSize.height; i++ ) {
                g2d.drawLine( -10, i * distance, screenSize.width + 10, i * distance );
            }
            
        }
        
        g2d.dispose();
        
    }

    @Override
    public boolean intercepts( double x, double y ) {
        return false;
    }
    
    @Override
    public LineSheet clone() throws CloneNotSupportedException {
            
        LineSheet clone = new LineSheet();
        copyData( this, clone );
        
        return clone;
        
    }

    public void setDistance( int distance ) {
        this.distance = distance;
    }
    
    public void setDistanceTo15() {
        this.distance = 15;
    }
    
    public void setDistanceTo30() {
        this.distance = 30;
    }
    
    public void setDistanceTo45() {
        this.distance = 45;
    }
    
    public void setDistanceTo60() {
        this.distance = 60;
    }

    public int getDistance() {
        return distance;
    }

    public Color getStrokeColor() {
        return strokeColor;
    }
    
}

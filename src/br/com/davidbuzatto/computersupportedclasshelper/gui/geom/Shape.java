/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.davidbuzatto.computersupportedclasshelper.gui.geom;

import br.com.davidbuzatto.computersupportedclasshelper.utils.Constants;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

/**
 *
 * @author Prof. Dr. David Buzatto
 */
public abstract class Shape implements Serializable, Cloneable {
    
    private static final long serialVersionUID = Constants.SERIAL_VERSION;
    
    private static int idCount;
    private int id;
    
    protected double xStart;
    protected double yStart;
    protected double xEnd;
    protected double yEnd;
    
    // drawing coordinates
    protected double xStartD;
    protected double yStartD;
    protected double xEndD;
    protected double yEndD;
    
    protected double scale;
    protected double angle;
    
    protected Color strokeColor;
    protected Color fillColor;
    protected double strokeWidth;
    
    transient protected boolean selected;
    transient private int selectedPhase;
    
    public Shape() {
        scale = 1;
        strokeColor = Color.BLACK;
        fillColor = Color.WHITE;
        id = idCount++;
    }
    
    public abstract void draw( Graphics2D g2d );
    public abstract boolean intercepts( double x, double y );

    public void move( double difX, double difY ) {
        xStart += difX;
        xEnd += difX;
        yStart += difY;
        yEnd += difY;
    }
    
    public void drawSelection( Graphics2D g2d ) {
        
        if ( selected ) {
            
            g2d = (Graphics2D) g2d.create();
            
            g2d.setPaint( Constants.SELECTED_COLOR );
            g2d.setStroke( Constants.SELECTED_STROKES[selectedPhase++] );
            g2d.draw( new Rectangle2D.Double( 
                    xStartD - strokeWidth/2, 
                    yStartD - strokeWidth/2, 
                    xEndD-xStartD + strokeWidth, 
                    yEndD-yStartD + strokeWidth ) );
            if ( selectedPhase == Constants.SELECTED_STROKES.length ) {
                selectedPhase = 0;
            }
            
            g2d.dispose();
            
        }
        
    }
    
    public void calculateDrawingBounds() {
        
        xStartD = xStart < xEnd ? xStart : xEnd;
        xEndD = xStart < xEnd ? xEnd : xStart;
        yStartD = yStart < yEnd ? yStart : yEnd;
        yEndD = yStart < yEnd ? yEnd : yStart;
        
    }

    public int getId() {
        return id;
    }
    
    public void setStrokeColor( Color strokeColor ) {
        this.strokeColor = strokeColor;
    }

    public void setFillColor( Color fillColor ) {
        this.fillColor = fillColor;
    }

    public void setStrokeWidth( double strokeWidth ) {
        this.strokeWidth = strokeWidth;
    }

    public void setXStart( double xIni ) {
        this.xStart = xIni;
    }

    public void setXEnd( double xFim ) {
        this.xEnd = xFim;
    }

    public void setYStart( double yIni ) {
        this.yStart = yIni;
    }

    public void setYEnd( double yFim ) {
        this.yEnd = yFim;
    }

    public double getXStart() {
        return xStart;
    }
    
    public double getXEnd() {
        return xEnd;
    }

    public double getYStart() {
        return yStart;
    }
    
    public double getYEnd() {
        return yEnd;
    }
    
    public double getWidth() {
        return xEnd - xStart;
    }
    
    public double getHeight() {
        return yEnd - yStart;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected( boolean selected ) {
        this.selected = selected;
    }

    public static void setIdCount( int value ) {
        idCount = value;
    }

    public double getXStartD() {
        return xStartD;
    }

    public double getYStartD() {
        return yStartD;
    }

    public double getXEndD() {
        return xEndD;
    }

    public double getYEndD() {
        return yEndD;
    }

    public double getScale() {
        return scale;
    }

    public void setScale( double scale ) {
        this.scale = scale;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle( double angle ) {
        this.angle = angle;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        final Shape other = ( Shape ) obj;
        if ( this.id != other.id ) {
            return false;
        }
        return true;
    }
    
    @Override
    public abstract Shape clone() throws CloneNotSupportedException;
    
    protected static void copyData( Shape origin, Shape target ) {
        
        target.xStart = origin.xStart;
        target.yStart = origin.yStart;
        target.xEnd = origin.xEnd;
        target.yEnd = origin.yEnd;

        target.xStartD = origin.xStartD;
        target.yStartD = origin.yStartD;
        target.xEndD = origin.xEndD;
        target.yEndD = origin.yEndD;

        target.strokeColor = origin.strokeColor;
        target.fillColor = origin.fillColor;
        target.strokeWidth = origin.strokeWidth;
        
    }
    
}

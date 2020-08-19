/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.davidbuzatto.computersupportedclasshelper.gui;

import br.com.davidbuzatto.computersupportedclasshelper.gui.geom.Rectangle;
import br.com.davidbuzatto.computersupportedclasshelper.gui.geom.Shape;
import br.com.davidbuzatto.computersupportedclasshelper.utils.Constants;
import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author David
 */
public class DrawPage implements Serializable, Cloneable {
    
    private Color backgroundColor;

    private Shape tempShape;
    private List<Shape> shapes;

    public DrawPage( Color backgroundColor ) {
        if ( backgroundColor == null ) {
            this.backgroundColor = Constants.TRANSPARENT_COLOR;
        } else {
            this.backgroundColor = backgroundColor;
        }
        this.shapes = new ArrayList<>();
    }

    public void reset() {
        tempShape = null;
        shapes.clear();
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor( Color backgroundColor ) {
        if ( backgroundColor == null ) {
            this.backgroundColor = Constants.TRANSPARENT_COLOR;
        } else {
            this.backgroundColor = backgroundColor;
        }
    }

    public Shape getTempShape() {
        return tempShape;
    }

    public void setTempShape( Shape tempShape ) {
        this.tempShape = tempShape;
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public void setShapes( List<Shape> shapes ) {
        this.shapes = shapes;
    }
    
    public Rectangle getRectangularBounds() {
        
        Rectangle r = new Rectangle();
        
        double xStart = 0;
        double yStart = 0;
        double xEnd = 0;
        double yEnd = 0;
        
        boolean first = true;
        
        for ( Shape s : shapes ) {
            
            s.calculateDrawingBounds();
            
            if ( first ) {
                
                xStart = s.getXStartD();
                yStart = s.getYStartD();
                xEnd = s.getXEndD();
                yEnd = s.getYEndD();
                first = false;
                
            } else {
            
                if ( xStart > s.getXStartD() ) {
                    xStart = s.getXStartD();
                }

                if ( yStart > s.getYStartD() ) {
                    yStart = s.getYStartD();
                }

                if ( xEnd < s.getXEndD() ) {
                    xEnd = s.getXEndD();
                }

                if ( yEnd < s.getYEndD() ) {
                    yEnd = s.getYEndD();
                }
                
            }
            
        }
        
        r.setXStart( xStart );
        r.setYStart( yStart );
        r.setXEnd( xEnd );
        r.setYEnd( yEnd );
        
        r.calculateDrawingBounds();
        return r;
        
    }
    
    @Override
    public DrawPage clone() throws CloneNotSupportedException {
        
        DrawPage clone = new DrawPage( backgroundColor );
        
        for ( Shape shape : shapes ) {
            clone.shapes.add( shape.clone() );
        }
        
        return clone;
        
    }
    
}

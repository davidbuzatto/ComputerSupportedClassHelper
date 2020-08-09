/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.davidbuzatto.computersupportedclasshelper.gui;

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
    private List<Shape> redoList;

    public DrawPage( Color backgroundColor ) {
        if ( backgroundColor == null ) {
            this.backgroundColor = Constants.TRANSPARENT_COLOR;
        } else {
            this.backgroundColor = backgroundColor;
        }
        this.shapes = new ArrayList<>();
        this.redoList = new ArrayList<>();
    }

    public void reset() {
        tempShape = null;
        shapes.clear();
        redoList.clear();
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

    public List<Shape> getRedoList() {
        return redoList;
    }

    public void setRedoList( List<Shape> redoList ) {
        this.redoList = redoList;
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

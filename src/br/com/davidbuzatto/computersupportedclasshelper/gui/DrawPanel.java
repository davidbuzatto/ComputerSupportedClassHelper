/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.davidbuzatto.computersupportedclasshelper.gui;

import br.com.davidbuzatto.computersupportedclasshelper.gui.geom.EraserCurve;
import br.com.davidbuzatto.computersupportedclasshelper.gui.geom.Shape;
import br.com.davidbuzatto.computersupportedclasshelper.utils.Constants;
import br.com.davidbuzatto.computersupportedclasshelper.utils.Utils;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author David
 */
public class DrawPanel extends JPanel {
    
    private int currentDrawPageIndex;
    private DrawPage currentDrawPage;
    private List<DrawPage> drawPages;
    
    public DrawPanel() {
        
        setBackground( new Color( 0, 0, 0, 0 ) );
        setOpaque( false );
        
        currentDrawPage = new DrawPage( Constants.TRANSPARENT_COLOR );
        
        drawPages = new ArrayList<>();
        drawPages.add( currentDrawPage );
        
    }
    
    @Override
    protected void paintComponent( Graphics g ) {
        
        super.paintComponent( g );
        Graphics2D g2d = ( Graphics2D ) g.create();
        g2d.setRenderingHint( RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON );
        
        g2d.setPaint( currentDrawPage.getBackgroundColor() );
        g2d.fillRect( 0, 0, getWidth(), getHeight() );
        
        for ( Shape shape : currentDrawPage.getShapes() ) {
            shape.draw( g2d );
        }
        
        if ( currentDrawPage.getTempShape() != null ) {
            currentDrawPage.getTempShape().draw( g2d );
        }
        
        g2d.dispose();
        
    }

    public void updateEraserCurves( Color newColor ) {
        
        for ( Shape shape : currentDrawPage.getShapes() ) {
            if ( shape instanceof EraserCurve ) {
                shape.setStrokeColor( newColor );
            }
        }
        
    }
    
    public void undo() {
        if ( currentDrawPage.getShapes().size() > 0 ) {
            currentDrawPage.getRedoList().add( currentDrawPage.getShapes().remove( currentDrawPage.getShapes().size() - 1 ) );
        }
    }
    
    public void redo() {
        if ( currentDrawPage.getRedoList().size() > 0 ) {
            currentDrawPage.getShapes().add( currentDrawPage.getRedoList().remove( currentDrawPage.getRedoList().size() - 1 ) );
        }
    }
    
    public void moveFront( Shape shape ) {
        
        List<Shape> shapes = currentDrawPage.getShapes();
        
        int v = shapes.indexOf( shape );
        int w;
        
        while ( v < shapes.size() - 1 ) {
            w = v + 1;
            if ( w < shapes.size() ) {
                Utils.<Shape>swap( shapes, v, w );
            }
            v++;
        }
        
    }
    
    public void moveForwards( Shape shape ) {
        
        List<Shape> shapes = currentDrawPage.getShapes();
        
        int v = shapes.indexOf( shape );
        int w = v + 1;
        
        if ( w < shapes.size() ) {
            Utils.<Shape>swap( shapes, v, w );
        }
        
    }
    
    public void moveBack( Shape shape ) {
        
        
        List<Shape> shapes = currentDrawPage.getShapes();
        
        int v = shapes.indexOf( shape );
        int w;
        
        while ( v > 0 ) {
            w = v - 1;
            if ( w >= 0 ) {
                Utils.<Shape>swap( shapes, v, w );
            }
            v--;
        }
        
    }
    
    public void moveBackwards( Shape shape ) {
        
        List<Shape> shapes = currentDrawPage.getShapes();
        
        int v = shapes.indexOf( shape );
        int w = v - 1;
        
        if ( w >= 0 ) {
            Utils.<Shape>swap( shapes, v, w );
        }
        
    }
    
    public void resetRedoList() {
        currentDrawPage.getRedoList().clear();
    }
    
    public boolean isAbleToUndo() {
        return currentDrawPage.getShapes().size() > 0;
    }
    
    public boolean isAbleToRedo() {
        return currentDrawPage.getRedoList().size() > 0;
    }
    
    public Color getBackgroundColor() {
        return currentDrawPage.getBackgroundColor();
    }

    public void setBackgroundColor( Color backgroundColor ) {
        this.currentDrawPage.setBackgroundColor( backgroundColor );
    }

    public DrawPage getCurrentDrawPage() {
        return currentDrawPage;
    }

    public void setTempShape( Shape tempShape ) {
        this.currentDrawPage.setTempShape( tempShape );
    }

    public List<Shape> getShapes() {
        return currentDrawPage.getShapes();
    }
    
    public void addShape( Shape shape ) {
        currentDrawPage.getShapes().add( shape );
    }
    
    public void removeShape( Shape shape ) {
        currentDrawPage.getShapes().remove( shape );
    }
    
    public void duplicateShape( Shape shape ) {
        try {
            currentDrawPage.getShapes().add( shape.clone() );
        } catch ( CloneNotSupportedException exc ) {
            exc.printStackTrace();
        }
    }
    
    public void addDrawPageEnd( Color backgroundColor ) {
        
        if ( backgroundColor == null ) {
            backgroundColor = Constants.TRANSPARENT_COLOR;
        }
        
        currentDrawPage = new DrawPage( backgroundColor );
        drawPages.add( currentDrawPage );
        
    }
    
    public void addDrawPageStart( Color backgroundColor ) {
        
        if ( backgroundColor == null ) {
            backgroundColor = Constants.TRANSPARENT_COLOR;
        }
        
        currentDrawPage = new DrawPage( backgroundColor );
        drawPages.add( 0, currentDrawPage );
        
    }
    
    public void newDrawPage( Color backgroundColor ) {
        
        if ( backgroundColor == null ) {
            backgroundColor = Constants.TRANSPARENT_COLOR;
        }
        
        currentDrawPage = new DrawPage( backgroundColor );
        drawPages.add( currentDrawPageIndex, currentDrawPage );
        
    }
    
    public void nextDrawPage( Color backgroundColor ) {
        
        if ( currentDrawPageIndex == drawPages.size()-1 ) {
            addDrawPageEnd( backgroundColor );
            currentDrawPageIndex++;
        } else {
            currentDrawPage = drawPages.get( ++currentDrawPageIndex );
        }
        
    }
    
    public void previousDrawPage( Color backgroundColor ) {
        
        if ( currentDrawPageIndex == 0 ) {
            addDrawPageStart( backgroundColor );
        } else {
            currentDrawPage = drawPages.get( --currentDrawPageIndex );
        }
        
    }
    
    public void moveCurrentDrawPageToRight() {
        
        if ( currentDrawPageIndex != drawPages.size()-1 ) {
            
            DrawPage current = drawPages.get( currentDrawPageIndex );
            DrawPage next = drawPages.get( currentDrawPageIndex + 1 );
            
            drawPages.set( currentDrawPageIndex, next );
            drawPages.set( currentDrawPageIndex + 1, current );
            
            currentDrawPageIndex++;
            currentDrawPage = drawPages.get( currentDrawPageIndex );
            
        }
        
    }
    
    public void moveCurrentDrawPageToLeft() {
        
        if ( currentDrawPageIndex != 0 ) {
            
            DrawPage current = drawPages.get( currentDrawPageIndex );
            DrawPage previous = drawPages.get( currentDrawPageIndex - 1 );
            
            drawPages.set( currentDrawPageIndex, previous );
            drawPages.set( currentDrawPageIndex - 1, current );
            
            currentDrawPageIndex--;
            currentDrawPage = drawPages.get( currentDrawPageIndex );
            
        }
        
    }
    
    public void deleteCurrentDrawPage() {
        if ( canDeleteDrawPage() ) {
            drawPages.remove( currentDrawPageIndex-- );
            if ( currentDrawPageIndex < 0 ) {
                currentDrawPageIndex = 0;
            }
            currentDrawPage = drawPages.get( currentDrawPageIndex );
        }
    }
    
    public void duplicateCurrentDrawPageToRight() {
        
        try {
            
            DrawPage dpc = drawPages.get( currentDrawPageIndex ).clone();
            drawPages.add( ++currentDrawPageIndex, dpc );
            currentDrawPage = drawPages.get( currentDrawPageIndex );
            
        } catch ( CloneNotSupportedException exc ) {
            exc.printStackTrace();
        }
    }
    
    public void duplicateCurrentDrawPageToLeft() {
        
        try {
            
            DrawPage dpc = drawPages.get( currentDrawPageIndex ).clone();
            drawPages.add( currentDrawPageIndex, dpc );
            currentDrawPage = drawPages.get( currentDrawPageIndex );
            
        } catch ( CloneNotSupportedException exc ) {
            exc.printStackTrace();
        }
    }
    
    public boolean willCreateNewEndDrawPage() {
        return currentDrawPageIndex == drawPages.size()-1;
    }
    
    public boolean willCreateNewStartDrawPage() {
        return currentDrawPageIndex == 0;
    }
    
    public boolean willMoveToRightCurrentDrawPage() {
        return currentDrawPageIndex != drawPages.size()-1;
    }
    
    public boolean willMoveToLeftCurrentDrawPage() {
        return currentDrawPageIndex != 0;
    }
    
    public boolean canDeleteDrawPage() {
        return drawPages.size() > 1;
    }
    
    public void clearCurrentDrawPage() {
        currentDrawPage.reset();
    }

    public List<DrawPage> getDrawPages() {
        return drawPages;
    }

    public int getCurrentDrawPageIndex() {
        return currentDrawPageIndex;
    }
    
    @SuppressWarnings( "unchecked" )
    public void loadDrawPagesFromOutside( Object data ) {
        
        if ( data instanceof List ) {
            List gList = (List) data;
            if ( !gList.isEmpty() && gList.get( 0 ) instanceof DrawPage ) {
                drawPages = (List<DrawPage>) gList;
                currentDrawPageIndex = 0;
                currentDrawPage = drawPages.get( 0 );
            }
        }
        
    }
    
    public void reset() {
        
        currentDrawPage = new DrawPage( Constants.TRANSPARENT_COLOR );
        drawPages.clear();
        drawPages.add( currentDrawPage );
        currentDrawPageIndex = 0;
        
    }
    
    public int getMaxShapeId() {
        
        int maxId = -1;
        
        for ( DrawPage drawPage : drawPages ) {
            for ( Shape shape : drawPage.getShapes() ) {
                if ( maxId < shape.getId() ) {
                    maxId = shape.getId();
                }
            }
        }
        
        return maxId;
        
    }
    
}

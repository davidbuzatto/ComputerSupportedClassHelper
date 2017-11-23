/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.davidbuzatto.computersupportedclasshelper.gui;

import br.com.davidbuzatto.computersupportedclasshelper.gui.geom.Shape;
import br.com.davidbuzatto.computersupportedclasshelper.utils.Constants;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.Serializable;
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
    
    public static class DrawPage implements Serializable {
        
        Color backgroundColor;
    
        Shape tempShape;
        List<Shape> shapes;
        List<Shape> redoList;
    
        DrawPage( Color backgroundColor ) {
            this.backgroundColor = backgroundColor;
            this.shapes = new ArrayList<>();
            this.redoList = new ArrayList<>();
        }
        
        void reset() {
            tempShape = null;
            shapes.clear();
            redoList.clear();
        }
        
    }
    
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
        
        g2d.setPaint( currentDrawPage.backgroundColor );
        g2d.fillRect( 0, 0, getWidth(), getHeight() );
        
        if ( currentDrawPage.tempShape != null ) {
            currentDrawPage.tempShape.draw( g2d );
        }
        
        for ( Shape shape : currentDrawPage.shapes ) {
            shape.draw( g2d );
        }
        
        g2d.dispose();
        
    }

    public void undo() {
        if ( currentDrawPage.shapes.size() > 0 ) {
            currentDrawPage.redoList.add( currentDrawPage.shapes.remove( currentDrawPage.shapes.size() - 1 ) );
        }
    }
    
    public void redo() {
        if ( currentDrawPage.redoList.size() > 0 ) {
            currentDrawPage.shapes.add( currentDrawPage.redoList.remove( currentDrawPage.redoList.size() - 1 ) );
        }
    }
    
    public void resetRedoList() {
        currentDrawPage.redoList.clear();
    }
    
    public boolean isAbleToUndo() {
        return currentDrawPage.shapes.size() > 0;
    }
    
    public boolean isAbleToRedo() {
        return currentDrawPage.redoList.size() > 0;
    }
    
    public Color getBackgroundColor() {
        return currentDrawPage.backgroundColor;
    }

    public void setBackgroundColor( Color backgroundColor ) {
        this.currentDrawPage.backgroundColor = backgroundColor;
    }

    public void setTempShape( Shape tempShape ) {
        this.currentDrawPage.tempShape = tempShape;
    }

    public List<Shape> getShapes() {
        return currentDrawPage.shapes;
    }
    
    public void addShape( Shape shape ) {
        currentDrawPage.shapes.add( shape );
    }
    
    public void addDrawPage( Color backgroundColor ) {
        
        if ( backgroundColor == null ) {
            backgroundColor = Constants.TRANSPARENT_COLOR;
        }
        
        currentDrawPage = new DrawPage( backgroundColor );
        drawPages.add( currentDrawPage );
        
    }
    
    public void nextDrawPage( Color backgroundColor ) {
        
        if ( currentDrawPageIndex == drawPages.size()-1 ) {
            addDrawPage( backgroundColor );
            currentDrawPageIndex++;
        } else {
            currentDrawPage = drawPages.get( ++currentDrawPageIndex );
        }
        
    }
    
    public void previousDrawPage() {
        
        if ( currentDrawPageIndex != 0 ) {
            currentDrawPage = drawPages.get( --currentDrawPageIndex );
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
    
    public boolean willCreateNewDrawPage() {
        return currentDrawPageIndex == drawPages.size()-1;
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
    
    public void loadDrawPagesFromOutside( Object data ) {
        
        drawPages = (List<DrawPage>) data;
        currentDrawPageIndex = 0;
        currentDrawPage = drawPages.get( 0 );
        
    }
    
    public void reset() {
        
        currentDrawPage = new DrawPage( Constants.TRANSPARENT_COLOR );
        drawPages.clear();
        drawPages.add( currentDrawPage );
        currentDrawPageIndex = 0;
        
    }
    
}

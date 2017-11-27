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
        
        if ( currentDrawPage.getTempShape() != null ) {
            currentDrawPage.getTempShape().draw( g2d );
        }
        
        for ( Shape shape : currentDrawPage.getShapes() ) {
            shape.draw( g2d );
        }
        
        g2d.dispose();
        
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

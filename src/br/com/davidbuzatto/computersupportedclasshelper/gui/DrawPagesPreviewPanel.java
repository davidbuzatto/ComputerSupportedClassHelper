/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.davidbuzatto.computersupportedclasshelper.gui;

import br.com.davidbuzatto.computersupportedclasshelper.gui.geom.EraserCurve;
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
public class DrawPagesPreviewPanel extends JPanel {
    
    private int currentDrawPageIndex;
    private DrawPage currentDrawPage;
    private List<DrawPage> drawPages;
    
    public DrawPagesPreviewPanel() {
        
        setBackground( new Color( 0, 0, 0, 0 ) );
        setOpaque( false );
        
        drawPages = new ArrayList<>();
        
    }
    
    @Override
    protected void paintComponent( Graphics g ) {
        
        super.paintComponent( g );
        Graphics2D g2d = ( Graphics2D ) g.create();
        g2d.setRenderingHint( RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON );
        
        /*g2d.setPaint( currentDrawPage.getBackgroundColor() );
        g2d.fillRect( 0, 0, getWidth(), getHeight() );
        
        for ( Shape shape : currentDrawPage.getShapes() ) {
            shape.draw( g2d );
        }*/
        
        g2d.dispose();
        
    }
    
    public Color getBackgroundColor() {
        return currentDrawPage.getBackgroundColor();
    }

    public void setBackgroundColor( Color backgroundColor ) {
        this.currentDrawPage.setBackgroundColor( backgroundColor );
    }

    public void setDrawPages( List<DrawPage> drawPages ) {
        this.drawPages = drawPages;
    }
    
    public void movePreviewToLeft() {
        System.out.println( "le" );
    }
    
    public void movePreviewToRight() {
        System.out.println( "ri" );
    }
    
}

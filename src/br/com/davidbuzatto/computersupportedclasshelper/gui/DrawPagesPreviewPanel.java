/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.davidbuzatto.computersupportedclasshelper.gui;

import br.com.davidbuzatto.computersupportedclasshelper.gui.geom.Shape;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author Prof. Dr. David Buzatto
 */
public class DrawPagesPreviewPanel extends JPanel {
    
    private int currentDrawPageIndex;
    private List<DrawPage> drawPages;
    
    private double scale;
    
    public DrawPagesPreviewPanel() {
        
        setBackground( new Color( 0, 0, 0, 0 ) );
        setOpaque( false );
        
        drawPages = new ArrayList<>();
        
        // calculate the scale to 10% of the current screen dimensions
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        scale = 160 / (double) d.width;
        
    }
    
    @Override
    protected void paintComponent( Graphics g ) {
        
        super.paintComponent( g );
        Graphics2D g2d = ( Graphics2D ) g.create();
        g2d.setRenderingHint( RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON );
        
        g2d.setFont( new Font( "dialog", Font.BOLD, 12 ) );
        int max = 3;
        int startTranslation = 0;
        
        if ( !drawPages.isEmpty() ) {
            
            int q = drawPages.size();
            if ( q > max ) {
                q = max;
            }
            
            if ( q == 1 ) {
                startTranslation = 180;
            } else if ( q == 2 ) {
                startTranslation = 90;
            }
            
            for ( int i = 0; i < q; i++ ) {
                DrawPage currentDrawPage = drawPages.get( currentDrawPageIndex + i );
                drawPagePreview( currentDrawPage, g2d, i, startTranslation );
            }
            
            g2d.setColor( Color.BLACK );
            if ( q == 1 ) {
                g2d.fillRect( 0, 0, 180, 90 );
                g2d.fillRect( 340, 0, 300, 90 );
            } else if ( q == 2 ) {
                g2d.fillRect( 0, 0, 90, 90 );
                g2d.fillRect( 250, 0, 20, 90 );
                g2d.fillRect( 430, 0, 300, 90 );
            } else {
                g2d.fillRect( 160, 0, 20, 90 );
                g2d.fillRect( 340, 0, 20, 90 );
            }
            
        }
        
        g2d.dispose();
        
    }

    private void drawPagePreview( DrawPage currentDrawPage, 
            Graphics2D g2d, 
            int i,
            int startTranslation ) {
        
        Graphics2D g2d1 = (Graphics2D) g2d.create();
        g2d1.translate( startTranslation + 180 * i, 0 );

        g2d1.setPaint( currentDrawPage.getBackgroundColor() );
        g2d1.fillRect( 0, 0, 160, 90 );

        Graphics2D g2d2 = (Graphics2D) g2d1.create();
        g2d2.scale( scale, scale );
        
        if ( currentDrawPage.isDrawLineSheet() ) {
            currentDrawPage.getLineSheet().draw( g2d2 );
        } else if ( currentDrawPage.isDrawGrid() ) {
            currentDrawPage.getGrid().draw( g2d2 );
        }
        
        for ( Shape shape : currentDrawPage.getShapes() ) {
            shape.draw( g2d2 );
        }
        
        g2d2.dispose();
        
        g2d1.setPaint( Color.WHITE );
        g2d1.drawRect( 0, 0, 159, 89 );
        g2d1.drawString( "page " + ( i + 1 + currentDrawPageIndex ), 10, 80 );
        g2d1.dispose();
        
    }
    
    public void setDrawPages( List<DrawPage> drawPages ) {
        this.drawPages = drawPages;
    }
    
    public void movePreviewToLeft() {
        
        if ( currentDrawPageIndex != 0 ) {
            currentDrawPageIndex--;
        }
        
        repaint();
        
    }
    
    public void movePreviewToRight() {
        
        if ( currentDrawPageIndex < drawPages.size() - 3 ) {
            currentDrawPageIndex++;
        }
        
        repaint();
        
    }
    
}

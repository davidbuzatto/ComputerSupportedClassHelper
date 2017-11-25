/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.davidbuzatto.computersupportedclasshelper.gui.geom;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author David
 */
public class Curve extends Shape implements Serializable {

    private List<Double> xs;
    private List<Double> ys;
    
    public Curve() {
        xs = new ArrayList<>();
        ys = new ArrayList<>();
    }
    
    @Override
    public void draw( Graphics2D g2d ) {
        
        calculateDrawingBounds();
        g2d = (Graphics2D) g2d.create();
        
        Path2D path = new Path2D.Double();
        boolean move = true;
        int p = 0;

        for ( double x : xs ) {

            if ( move ) {
                path.moveTo( x, ys.get( p ) );
                path.lineTo( x, ys.get( p ) );
                move = false;
            } else {
                path.lineTo( x, ys.get( p ) );
            }

            p++;

        }
            
        if ( fillColor != null ) {
            g2d.setPaint( fillColor );
            g2d.fill( path );
        }
        
        if ( strokeColor != null ) {
            g2d.setPaint( strokeColor );
            g2d.setStroke( new BasicStroke( (float) strokeWidth ) );
            g2d.draw( path );
        }
        
        drawSelection( g2d );
        g2d.dispose();
        
    }

    public void addX( double x ) {
        
        xs.add( x );
        
        if ( xs.size() == 1 ) {
            xStart = x;
            xEnd = x;
        } else if ( x < xStart ) {
            xStart = x;
        } else if ( x > xEnd ) {
            xEnd = x;
        }
        
    }

    public void addY( double y ) {
        
        ys.add( y );
        
        if ( ys.size() == 1 ) {
            yStart = y;
            yEnd = y;
        } else if ( y < yStart ) {
            yStart = y;
        } else if ( y > yEnd ) {
            yEnd = y;
        }
        
    }
    
    @Override
    public void move( double difX, double difY ) {
        
        super.move( difX, difY );
        
        for ( int i = 0; i < xs.size(); i++ ) {
            xs.set( i, xs.get( i ) + difX );
            ys.set( i, ys.get( i ) + difY );
        }
        
    }
    
    @Override
    public boolean intercepts( double x, double y ) {
        return x >= xStartD && x <= xEndD && y >= yStartD && y <= yEndD;
    }
    
}

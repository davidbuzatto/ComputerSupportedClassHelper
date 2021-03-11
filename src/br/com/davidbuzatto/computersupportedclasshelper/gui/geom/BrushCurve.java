/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.davidbuzatto.computersupportedclasshelper.gui.geom;

import br.com.davidbuzatto.computersupportedclasshelper.utils.Constants;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.simplify.DouglasPeuckerSimplifier;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author David
 */
public class BrushCurve extends Shape implements Serializable, Cloneable {

    private static final long serialVersionUID = Constants.SERIAL_VERSION;
    
    private List<Coordinate> coords;
    
    public BrushCurve() {
        coords = new ArrayList<>();
    }
    
    @Override
    public void draw( Graphics2D g2d ) {
        
        calculateDrawingBounds();
        g2d = (Graphics2D) g2d.create();
        
        try {
            
            Path2D path = new Path2D.Double();
            boolean move = true;
                
            if ( coords.size() < 2 ) {
                
                path.moveTo( coords.get( 0 ).x, coords.get( 0 ).y );
                path.lineTo( coords.get( 0 ).x, coords.get( 0 ).y );
                
            } else {
                
                GeometryFactory f = new GeometryFactory();
                LineString ls = f.createLineString( coords.toArray( new Coordinate[0] ) );
                Geometry simple = DouglasPeuckerSimplifier.simplify( ls, 3.0 );
                
                if ( simple.getCoordinates().length < 2 ) {
                    return;
                }

                List<Coordinate> raw = new ArrayList<>();
                raw.addAll( Arrays.asList( simple.getCoordinates() ) );
                List<Coordinate> spline = CatmullRom.interpolate( raw, 10 );

                for ( Coordinate c : spline ) {

                    if ( move ) {
                        path.moveTo( c.x, c.y );
                        path.lineTo( c.x, c.y );
                        move = false;
                    } else {
                        path.lineTo( c.x, c.y );
                    }

                }
                
                /*Coordinate cc = spline.get( spline.size() - 1 );
                path.lineTo( cc.x - 10, cc.y + 10 );*/
                Collections.reverse( spline );
                
                for ( Coordinate c : spline ) {
                    path.lineTo( c.x - strokeWidth, c.y + strokeWidth );
                }
                
                path.closePath();
                
            }

            if ( strokeColor != null ) {
                g2d.setPaint( strokeColor );
                g2d.fill( path );
            }
            
        } catch ( Exception exc ) {
            exc.printStackTrace();
        }
        
        drawSelection( g2d );
        g2d.dispose();
        
    }
    
    public void addCoordinate( double x, double y ) {
        
        coords.add( new Coordinate( x, y ) );
        
        if ( coords.size() == 1 ) {
            xStart = x;
            xEnd = x;
        } else if ( x < xStart ) {
            xStart = x;
        } else if ( x > xEnd ) {
            xEnd = x;
        }
        
        if ( coords.size() == 1 ) {
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
        
        for ( Coordinate c : coords ) {
            c.x += difX;
            c.y += difY;
        }
        
    }
    
    @Override
    public boolean intercepts( double x, double y ) {
        return x >= xStartD && x <= xEndD && y >= yStartD && y <= yEndD;
    }
    
    @Override
    public BrushCurve clone() throws CloneNotSupportedException {
            
        BrushCurve clone = new BrushCurve();
        copyCurveData( this, clone );
        
        return clone;
        
    }

    @Override
    public void setStrokeColor( Color strokeColor ) {
        
        super.setStrokeColor( strokeColor );
        
    }
    
    protected static void copyCurveData( BrushCurve origin, BrushCurve target ) {
        
        copyData( origin, target );
        
        for ( Coordinate c : origin.coords ) {
            target.coords.add( (Coordinate) c.clone() );
        }
        
    }
    
}

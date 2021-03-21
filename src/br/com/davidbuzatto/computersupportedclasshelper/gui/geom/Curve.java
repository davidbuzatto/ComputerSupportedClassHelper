/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.davidbuzatto.computersupportedclasshelper.gui.geom;

import br.com.davidbuzatto.computersupportedclasshelper.utils.Constants;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.simplify.DouglasPeuckerSimplifier;

/**
 *
 * @author Prof. Dr. David Buzatto
 */
public class Curve extends Shape implements Serializable, Cloneable {

    private static final long serialVersionUID = Constants.SERIAL_VERSION;
    
    private List<Coordinate> coords;
    private Color strokeAlphaColor;
    private int smoothIterations;
    
    public Curve() {
        this( 0 );
    }
    
    public Curve( int smoothIterations ) {
        coords = new ArrayList<>();
        this.smoothIterations = smoothIterations;
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
                
            }
            
            if ( fillColor != null ) {
                g2d.setPaint( fillColor );
                g2d.fill( path );
            }

            if ( strokeColor != null ) {
                
                if ( smoothIterations == 0 ) {
                    
                    g2d.setPaint( strokeColor );
                    g2d.setStroke( new BasicStroke( (float) strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND ) );
                    g2d.draw( path );
                    
                } else {
                    
                    float b = (float) strokeWidth / ( smoothIterations + 1 );
                    float cB = (float) strokeWidth;
                    
                    g2d.setPaint( strokeAlphaColor );
                    
                    for ( int i = 0; i < smoothIterations; i++ ) {
                        g2d.setStroke( new BasicStroke( cB, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND ) );
                        g2d.draw( path );
                        cB -= b;
                    }
                    
                    g2d.setPaint( strokeColor );
                    g2d.setStroke( new BasicStroke( (float) cB, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND ) );
                    g2d.draw( path );
                    
                }
                
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
    public Curve clone() throws CloneNotSupportedException {
            
        Curve clone = new Curve( smoothIterations );
        copyCurveData( this, clone );
        
        return clone;
        
    }

    @Override
    public void setStrokeColor( Color strokeColor ) {
        
        super.setStrokeColor( strokeColor );
        
        strokeAlphaColor = new Color( 
                strokeColor.getRed(),
                strokeColor.getGreen(),
                strokeColor.getBlue(), 255 / ( smoothIterations + 1 ) );
        
    }
    
    protected static void copyCurveData( Curve origin, Curve target ) {
        
        copyData( origin, target );
        
        for ( Coordinate c : origin.coords ) {
            target.coords.add( (Coordinate) c.clone() );
        }
        
    }
    
}

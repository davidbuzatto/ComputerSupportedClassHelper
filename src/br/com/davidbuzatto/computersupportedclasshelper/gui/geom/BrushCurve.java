/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.davidbuzatto.computersupportedclasshelper.gui.geom;

import br.com.davidbuzatto.computersupportedclasshelper.utils.Constants;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
public class BrushCurve extends Shape implements Serializable, Cloneable {

    private static final long serialVersionUID = Constants.SERIAL_VERSION;

    // Simplification tolerance used once, in finalizeStroke(), instead of on every draw().
    private static final double SIMPLIFY_TOLERANCE = 3.0;
    //private static final double SIMPLIFY_TOLERANCE = 5.0;

    private List<Coordinate> coords;

    // Running position used by the exponential smoothing filter in addCoordinate().
    // Only meaningful while the stroke is being captured, so it isn't serialized.
    private transient Coordinate smoothedPos;
    private boolean finalized;

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

                // Simplification (Douglas-Peucker) no longer happens here on every repaint;
                // it runs once in finalizeStroke() when the stroke is done. While the stroke
                // is still being captured, coords already comes smoothed and resampled from
                // addCoordinate(), so it can be fed to the spline directly.
                List<Coordinate> spline = CatmullRom.interpolate( coords, 10 );

                for ( Coordinate c : spline ) {

                    if ( move ) {
                        path.moveTo( c.x, c.y );
                        path.lineTo( c.x, c.y );
                        move = false;
                    } else {
                        path.lineTo( c.x, c.y );
                    }

                }
                
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

        if ( coords.isEmpty() ) {

            // First sample of the stroke: nothing to smooth or resample against yet.
            smoothedPos = new Coordinate( x, y );
            appendCoordinate( smoothedPos );

        } else {

            // 1) exponential smoothing damps hand tremor in the raw samples.
            smoothedPos = FreehandSmoothing.smooth(
                    smoothedPos, x, y, FreehandSmoothing.DEFAULT_SMOOTHING_FACTOR );

            // 2) arc-length resampling: if the mouse/pen moved fast and the last stored
            // point is far from this one, fill the gap with synthetic points so no long,
            // straight segment is ever handed to the Catmull-Rom spline.
            Coordinate last = coords.get( coords.size() - 1 );
            for ( Coordinate c : FreehandSmoothing.resample(
                    last, smoothedPos, FreehandSmoothing.DEFAULT_MAX_SEGMENT_LENGTH ) ) {
                appendCoordinate( c );
            }

        }

    }

    private void appendCoordinate( Coordinate c ) {

        coords.add( c );

        if ( coords.size() == 1 ) {
            xStart = c.x;
            xEnd = c.x;
        } else if ( c.x < xStart ) {
            xStart = c.x;
        } else if ( c.x > xEnd ) {
            xEnd = c.x;
        }

        if ( coords.size() == 1 ) {
            yStart = c.y;
            yEnd = c.y;
        } else if ( c.y < yStart ) {
            yStart = c.y;
        } else if ( c.y > yEnd ) {
            yEnd = c.y;
        }

    }

    /**
     * Ends the capture of this stroke. Runs Douglas-Peucker simplification once over the
     * whole (smoothed + resampled) buffer and keeps only the simplified points, instead of
     * holding onto every raw/synthetic sample captured while dragging. Must be called once,
     * when the mouse/pen is released.
     */
    public void finalizeStroke() {

        if ( finalized ) {
            return;
        }

        if ( coords.size() >= 3 ) {

            GeometryFactory f = new GeometryFactory();
            LineString ls = f.createLineString( coords.toArray( new Coordinate[0] ) );
            Geometry simple = DouglasPeuckerSimplifier.simplify( ls, SIMPLIFY_TOLERANCE );

            coords = new ArrayList<>( Arrays.asList( simple.getCoordinates() ) );

        }

        finalized = true;

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

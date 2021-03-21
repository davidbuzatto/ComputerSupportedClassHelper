/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.davidbuzatto.computersupportedclasshelper.gui.undo;

import br.com.davidbuzatto.computersupportedclasshelper.gui.geom.Curve;
import br.com.davidbuzatto.computersupportedclasshelper.gui.geom.Rectangle;
import br.com.davidbuzatto.computersupportedclasshelper.gui.geom.Shape;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.locationtech.jts.geom.Coordinate;

/**
 *
 * @author Prof. Dr. David Buzatto
 */
public class ShapeChangeAction extends ChangeAction {
    
    private Shape shape;
    private Map<String, Object> beforeChange;
    private Map<String, Object> afterChange;
    
    public ShapeChangeAction( Shape shape ) {
        this.shape = shape;
        beforeChange = new HashMap<>();
        afterChange = new HashMap<>();
    }
    
    private void inspect( Shape origin, Map<String, Object> saveTo ) {
        
        try {
            
            Class k = origin.getClass();

            while ( true ) {

                for ( Field f : k.getDeclaredFields() ) {

                    f.setAccessible( true );

                    if ( !Modifier.isTransient( f.getModifiers() ) &&
                         !Modifier.isStatic( f.getModifiers() ) ) {
                        
                        Object o = f.get( origin );
                        
                        // special cases...
                        if ( k == Curve.class ) {
                            
                            if ( o instanceof List ) {
                                List<Coordinate> lo = (List<Coordinate>) o;
                                List<Coordinate> newCoords = new ArrayList<>();
                                for ( Coordinate c : lo ) {
                                    newCoords.add( (Coordinate) c.clone() );
                                }
                                o = newCoords;
                            }
                            
                        }
                        
                        saveTo.put( f.getName(), o );
                        
                    }

                }

                if ( k == Shape.class ) {
                    break;
                }

                k = k.getSuperclass();

            }
            
        } catch ( IllegalAccessException exc ) {
            exc.printStackTrace();
        }
        
    }
    
    private void apply( Shape shape, Map<String, Object> getFrom ) {
        
        try {
            
            Class k = shape.getClass();

            while ( true ) {
                
                for ( Field f : k.getDeclaredFields() ) {

                    f.setAccessible( true );

                    if ( !Modifier.isTransient( f.getModifiers() ) &&
                         !Modifier.isStatic( f.getModifiers() ) ) {
                        f.set( shape, getFrom.get( f.getName() ) );
                    }

                }

                if ( k == Shape.class ) {
                    break;
                }

                k = k.getSuperclass();
                
            }
            
        } catch ( IllegalAccessException exc ) {
            exc.printStackTrace();
        }
        
    }
    
    public void inspectShapeBeforeChange( Shape shape ) {
        inspect( shape, beforeChange );
    }
    
    public void inspectShapeAfterChange( Shape shape ) {
        inspect( shape, afterChange );
    }
    
    @Override
    public void applyBeforeChange() {
        apply( shape, beforeChange );
    }
    
    @Override
    public void applyAfterChange() {
        apply( shape, afterChange );
    }

    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        
        for ( Entry<String, Object> e : beforeChange.entrySet() ) {
            sb.append( String.format( "before %s -> %s", e.getKey(), e.getValue() ) );
            sb.append( String.format( " ---- after %s -> %s\n", e.getKey(), afterChange.get( e.getKey() ) ) );
        }
        
        return sb.toString();
        
    }
    
    public static void main( String[] args ) {
        
        Rectangle r = new Rectangle();
        r.setFillColor( Color.WHITE );
        r.setStrokeColor( Color.BLACK );
        r.setStrokeWidth( 5 );
        r.setXStart( 10 );
        r.setYStart( 10 );
        r.setXEnd( 50 );
        r.setYEnd( 30 );
        
        ShapeChangeAction ca = new ShapeChangeAction( r );
        ca.inspectShapeBeforeChange( r );
        
        r.setFillColor( Color.RED );
        r.setStrokeColor( Color.BLUE );
        r.setStrokeWidth( 10 );
        r.setXStart( 100 );
        r.setYStart( 100 );
        r.setXEnd( 150 );
        r.setYEnd( 130 );
        ca.inspectShapeAfterChange( r );
        
        System.out.println( ca );
        
        ChangeAction.trace( r );
        ca.applyBeforeChange();
        ChangeAction.trace( r );
        
    }
    
    
}

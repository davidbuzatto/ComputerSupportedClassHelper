/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.davidbuzatto.computersupportedclasshelper.gui.undo;

import br.com.davidbuzatto.computersupportedclasshelper.gui.geom.Shape;
import java.lang.reflect.Field;

/**
 *
 * @author David
 */
public abstract class ChangeAction {
    
    public abstract void applyBeforeChange();
    public abstract void applyAfterChange();
    
    public static void trace( Shape shape ) {
        
        try {
            
            Class k = shape.getClass();

            while ( true ) {

                System.out.println( k );
                
                for ( Field f : k.getDeclaredFields() ) {
                    f.setAccessible( true );
                    System.out.printf( "\t%s -> %s\n", f.getName(), f.get( shape ) );
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
    
}

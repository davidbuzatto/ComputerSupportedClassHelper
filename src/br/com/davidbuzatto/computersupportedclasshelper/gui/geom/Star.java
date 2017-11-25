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

/**
 *
 * @author David
 */
public class Star extends Polygon implements Serializable {
    
    @Override
    public void draw( Graphics2D g2d ) {
        
        calculate();
        
        g2d = (Graphics2D) g2d.create();
        
        Path2D.Double star = new Path2D.Double();
        star.moveTo( xs[0], ys[0] );
        
        if ( sideQuantity % 2 == 0 ) {
            
            for ( int i = 2; i < sideQuantity; i += 2 ) {
                star.lineTo( xs[i], ys[i] );
            }
            star.closePath();
            
            star.moveTo( xs[1], ys[1] );
            for ( int i = 3; i < sideQuantity; i += 2 ) {
                star.lineTo( xs[i], ys[i] );
            }
            
        } else {
            
            for ( int i = 2; i < sideQuantity * 2; i += 2 ) {
                star.lineTo( xs[i%sideQuantity], ys[i%sideQuantity] );
            }
            
        }
        
        star.closePath();
        
        if ( fillColor != null ) {
            g2d.setPaint(fillColor );
            g2d.fill( star );
        }
        
        if ( strokeColor != null ) {
            g2d.setPaint(strokeColor );
            g2d.setStroke( new BasicStroke( (float) strokeWidth ) );
            g2d.draw( star );
        }
        
        drawSelection( g2d );
        g2d.dispose();
        
    }
    
}

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
        
        Graphics2D g2 = (Graphics2D) g2d.create();
        
        Path2D.Double estrela = new Path2D.Double();
        estrela.moveTo( xs[0], ys[0] );
        
        if ( sideQuantity % 2 == 0 ) {
            
            // se par, faz em duas partes
            for ( int i = 2; i < sideQuantity; i += 2 ) {
                estrela.lineTo( xs[i], ys[i] );
            }
            estrela.closePath();
            
            estrela.moveTo( xs[1], ys[1] );
            for ( int i = 3; i < sideQuantity; i += 2 ) {
                estrela.lineTo( xs[i], ys[i] );
            }
            
        } else {
            
            for ( int i = 2; i < sideQuantity * 2; i += 2 ) {
                estrela.lineTo( xs[i%sideQuantity], ys[i%sideQuantity] );
            }
            
        }
        
        estrela.closePath();
        
        if ( fillColor != null ) {
            g2.setPaint(fillColor );
            g2.fill( estrela );
        }
        
        if ( strokeColor != null ) {
            g2.setPaint(strokeColor );
            g2.setStroke( new BasicStroke( (float) strokeWidth ) );
            g2.draw( estrela );
        }
        
        g2.dispose();
        
    }
    
}

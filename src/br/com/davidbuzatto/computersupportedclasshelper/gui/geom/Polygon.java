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
public class Polygon extends Shape implements Serializable {

    protected double xCenter;
    protected double yCenter;
    protected double[] xs;
    protected double[] ys;
    
    protected double radius;
    protected double angle;
    protected int sideQuantity;
    
    protected void calculate() {
        
        xs = new double[sideQuantity];
        ys = new double[sideQuantity];
        double angleIncrement = 360.0 / sideQuantity;
        
        for ( int i = 0; i < sideQuantity; i++ ) {
            xs[i] = xCenter + Math.cos( Math.toRadians(angle ) ) * radius;
            ys[i] = yCenter + Math.sin( Math.toRadians(angle ) ) * radius;
            angle += angleIncrement;
        }
        
    }
    
    @Override
    public void draw( Graphics2D g2d ) {
        
        calculate();
        
        Graphics2D g2 = (Graphics2D) g2d.create();
        
        Path2D.Double poligono = new Path2D.Double();
        poligono.moveTo( xs[0], ys[0] );
        
        for ( int i = 1; i < sideQuantity; i++ ) {
            poligono.lineTo( xs[i], ys[i] );
        }
        
        poligono.closePath();
        
        if ( fillColor != null ) {
            g2.setPaint( fillColor );
            g2.fill( poligono );
        }
        
        if ( strokeColor != null ) {
            g2.setPaint( strokeColor );
            g2.setStroke( new BasicStroke( (float) strokeWidth ) );
            g2.draw( poligono );
        }
        
        g2.dispose();
        
    }

    @Override
    public boolean intercepts( double x, double y ) {
        
        double c1 = x - xCenter;
        double c2 = y - yCenter;
        
        return c1 * c1 + c2 * c2 <= radius * radius;
        
    }

    @Override
    public void move( double difX, double difY ) {
        super.move( difX, difY );
        setXCenter( xStart + radius );
        setYCenter( yStart + radius );
    }
    
    @Override
    public void setXStart( double xStart ) {
        super.setXStart( xStart );
        setXCenter(xStart + radius );
    }
    
    @Override
    public void setYStart( double yStart ) {
        super.setYStart( yStart );
        setYCenter(yStart + radius );
    }
    
    public void setXCenter( double xCenter ) {
        this.xCenter = xCenter;
        xStart = xCenter - radius;
        xEnd = xCenter + radius;
    }

    public void setYCenter( double yCenter ) {
        this.yCenter = yCenter;
        yStart = yCenter - radius;
        yEnd = yCenter + radius;
    }

    public void setRadius( double radius ) {
        this.radius = radius;
    }

    public void setAngle( double angle ) {
        this.angle = angle;
    }

    public void setSideQuantity( int sideQuantity ) {
        this.sideQuantity = sideQuantity;
    }
    
}

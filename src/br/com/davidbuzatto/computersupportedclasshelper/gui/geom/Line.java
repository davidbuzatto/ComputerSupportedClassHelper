/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.davidbuzatto.computersupportedclasshelper.gui.geom;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.io.Serializable;

/**
 *
 * @author David
 */
public class Line extends Shape implements Serializable {

    @Override
    public void draw( Graphics2D g2d ) {
        
        Graphics2D g2 = (Graphics2D) g2d.create();
        
        if ( strokeColor != null ) {
            g2.setPaint(strokeColor );
            g2.setStroke( new BasicStroke( (float) strokeWidth ) );
        }
        
        g2.draw( new Line2D.Double( xStart, yStart, xEnd, yEnd ) );
        
        g2.dispose();
        
    }
    
    @Override
    public boolean intercepts( double x, double y ) {
        
        double xrIni = xStart < xEnd ? xStart : xEnd;
        double xrFim = xStart < xEnd ? xEnd : xStart;
        double yrIni = yStart < yEnd ? yStart : yEnd;
        double yrFim = yStart < yEnd ? yEnd : yStart;
        
        return x >= xrIni && x <= xrFim && y >= yrIni && y <= yrFim;
        
    }
    
}

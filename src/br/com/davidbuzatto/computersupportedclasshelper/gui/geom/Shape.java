/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.davidbuzatto.computersupportedclasshelper.gui.geom;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;

/**
 *
 * @author David
 */
public abstract class Shape implements Serializable {
    
    protected double xStart;
    protected double yStart;
    protected double xEnd;
    protected double yEnd;
    
    protected Color strokeColor;
    protected Color fillColor;
    protected double strokeWidth;
    
    public Shape() {
        strokeColor = Color.BLACK;
        fillColor = Color.WHITE;
    }
    
    public abstract void draw( Graphics2D g2d );
    public abstract boolean intercepts( double x, double y );

    public void move( double difX, double difY ) {
        xStart += difX;
        xEnd += difX;
        yStart += difY;
        yEnd += difY;
    }
    
    public void setStrokeColor( Color corTraco ) {
        this.strokeColor = corTraco;
    }

    public void setFillColor( Color corPreenchimento ) {
        this.fillColor = corPreenchimento;
    }

    public void setStrokeWidth( double espessuraTraco ) {
        this.strokeWidth = espessuraTraco;
    }

    public void setXStart( double xIni ) {
        this.xStart = xIni;
    }

    public void setXEnd( double xFim ) {
        this.xEnd = xFim;
    }

    public void setYStart( double yIni ) {
        this.yStart = yIni;
    }

    public void setYEnd( double yFim ) {
        this.yEnd = yFim;
    }

    public double getXStart() {
        return xStart;
    }
    
    public double getXEnd() {
        return xEnd;
    }

    public double getYStart() {
        return yStart;
    }
    
    public double getYEnd() {
        return yEnd;
    }
    
    public double getWidth() {
        return xEnd - xStart;
    }
    
    public double getHeight() {
        return yEnd - yStart;
    }
    
}

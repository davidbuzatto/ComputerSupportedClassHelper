/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.davidbuzatto.computersupportedclasshelper.utils;

/**
 *
 * @author David
 */
public class DrawingConfigs {
    
    private static final DrawingConfigs INSTANCE;
    
    private double strokeWidth;
    private double arcRadius;
    private int sideQuantity;
    
    private boolean processEventsMainWindow;
    
    static {
        INSTANCE = new DrawingConfigs();
    }
    
    private DrawingConfigs() {
        strokeWidth = 5;
        arcRadius = 10;
        sideQuantity = 5;
        processEventsMainWindow = true;
    }
    
    public static DrawingConfigs getInstance() {
        return INSTANCE;
    }

    public double getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth( double strokeWidth ) {
        this.strokeWidth = strokeWidth;
    }

    public int getSideQuantity() {
        return sideQuantity;
    }

    public void setSideQuantity( int sideQuantity ) {
        this.sideQuantity = sideQuantity;
    }

    public boolean isProcessEventsMainWindow() {
        return processEventsMainWindow;
    }

    public void setProcessEventsMainWindow( boolean processEventsMainWindow ) {
        this.processEventsMainWindow = processEventsMainWindow;
    }

    public double getArcRadius() {
        return arcRadius;
    }

    public void setArcRadius( double arcRadius ) {
        this.arcRadius = arcRadius;
    }
    
}

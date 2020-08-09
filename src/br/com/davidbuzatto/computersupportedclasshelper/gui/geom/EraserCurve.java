/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.davidbuzatto.computersupportedclasshelper.gui.geom;

import java.io.Serializable;

/**
 *
 * @author David
 */
public class EraserCurve extends Curve implements Serializable, Cloneable {
    
    @Override
    public boolean intercepts( double x, double y ) {
        return false;
    }
    
    @Override
    public EraserCurve clone() throws CloneNotSupportedException {
            
        EraserCurve clone = new EraserCurve();
        copyCurveData( this, clone );
        
        return clone;
        
    }
    
}
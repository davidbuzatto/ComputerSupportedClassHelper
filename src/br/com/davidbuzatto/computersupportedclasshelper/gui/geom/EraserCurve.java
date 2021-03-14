/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.davidbuzatto.computersupportedclasshelper.gui.geom;

import br.com.davidbuzatto.computersupportedclasshelper.utils.Constants;
import java.io.Serializable;

/**
 *
 * @author Prof. Dr. David Buzatto
 */
public class EraserCurve extends Curve implements Serializable, Cloneable {
    
    private static final long serialVersionUID = Constants.SERIAL_VERSION;
    
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

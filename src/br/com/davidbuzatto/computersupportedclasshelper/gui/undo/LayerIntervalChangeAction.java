/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.davidbuzatto.computersupportedclasshelper.gui.undo;

import br.com.davidbuzatto.computersupportedclasshelper.gui.DrawPage;
import br.com.davidbuzatto.computersupportedclasshelper.gui.geom.Shape;
import br.com.davidbuzatto.computersupportedclasshelper.utils.Utils;
import java.util.List;

/**
 *
 * @author David
 */
public class LayerIntervalChangeAction extends ChangeAction {
    
    private int positionBefore;
    private int positionAfter;
    private DrawPage drawPage;
    
    public LayerIntervalChangeAction( 
            DrawPage drawPage,
            int positionBefore, 
            int positionAfter ) {
        this.drawPage = drawPage;
        this.positionBefore = positionBefore;
        this.positionAfter = positionAfter;
    }
    
    @Override
    public void applyBeforeChange() {
        
        List<Shape> shapes = drawPage.getShapes();
        int v = positionBefore;
        int w;
        
        while ( v < positionAfter - 1 ) {
            w = v + 1;
            if ( w < positionAfter ) {
                Utils.<Shape>swap( shapes, v, w );
            }
            v++;
        }
        
    }
    
    @Override
    public void applyAfterChange() {
        
        List<Shape> shapes = drawPage.getShapes();
        int v = positionAfter;
        int w;
        
        while ( v > 0 ) {
            w = v - 1;
            if ( w >= positionBefore ) {
                Utils.<Shape>swap( shapes, v, w );
            }
            v--;
        }
        
    }
    
}

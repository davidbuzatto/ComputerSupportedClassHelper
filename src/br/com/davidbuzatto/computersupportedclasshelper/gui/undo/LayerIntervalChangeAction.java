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
 * @author Prof. Dr. David Buzatto
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
        int v = positionAfter;
        
        if ( v > positionBefore ) {
            while ( v > positionBefore ) {
                Utils.<Shape>swap( shapes, v, v - 1 );
                v--;
            }
        } else if ( v < positionBefore ) {
            while ( v < positionBefore ) {
                Utils.<Shape>swap( shapes, v, v + 1 );
                v++;
            }
        }
        
    }
    
    @Override
    public void applyAfterChange() {
        
        List<Shape> shapes = drawPage.getShapes();
        int v = positionBefore;
        
        if ( v < positionAfter ) {
            while ( v < positionAfter ) {
                Utils.<Shape>swap( shapes, v, v + 1 );
                v++;
            }
        } else if ( v > positionAfter ) {
            while ( v > positionAfter ) {
                Utils.<Shape>swap( shapes, v, v - 1 );
                v--;
            }
        }
        
    }
    
}

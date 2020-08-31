/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.davidbuzatto.computersupportedclasshelper.gui.undo;

import br.com.davidbuzatto.computersupportedclasshelper.gui.DrawPage;
import br.com.davidbuzatto.computersupportedclasshelper.gui.geom.Shape;

/**
 *
 * @author David
 */
public class AddShapeChangeAction extends ChangeAction {
    
    private Shape shape;
    private int position;
    private DrawPage drawPage;
    
    public AddShapeChangeAction( Shape shape, DrawPage drawPage ) {
        this.shape = shape;
        this.drawPage = drawPage;
        position = drawPage.getShapes().size() - 1; // always inserted in the end
    }
    
    @Override
    public void applyBeforeChange() {
        drawPage.getShapes().remove( shape );
    }
    
    @Override
    public void applyAfterChange() {
        drawPage.getShapes().add( position, shape );
    }
    
}

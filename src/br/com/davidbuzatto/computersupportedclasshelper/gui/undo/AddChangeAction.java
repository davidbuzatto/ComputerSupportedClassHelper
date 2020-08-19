/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.davidbuzatto.computersupportedclasshelper.gui.undo;

import br.com.davidbuzatto.computersupportedclasshelper.gui.DrawPanel;
import br.com.davidbuzatto.computersupportedclasshelper.gui.geom.Shape;

/**
 *
 * @author David
 */
public class AddChangeAction extends ChangeAction {
    
    private Shape shape;
    private int position;
    private DrawPanel drawPanel;
    
    public AddChangeAction( Shape shape, DrawPanel drawPanel ) {
        this.shape = shape;
        this.drawPanel = drawPanel;
        position = drawPanel.getShapes().size() - 1; // always inserted in the end
    }
    
    @Override
    public void applyBeforeChange() {
        drawPanel.getShapes().remove( shape );
    }
    
    @Override
    public void applyAfterChange() {
        drawPanel.getShapes().add( position, shape );
    }
    
}

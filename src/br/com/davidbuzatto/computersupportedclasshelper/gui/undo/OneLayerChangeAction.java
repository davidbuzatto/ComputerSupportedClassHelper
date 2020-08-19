/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.davidbuzatto.computersupportedclasshelper.gui.undo;

import br.com.davidbuzatto.computersupportedclasshelper.gui.DrawPage;
import br.com.davidbuzatto.computersupportedclasshelper.gui.geom.Shape;
import br.com.davidbuzatto.computersupportedclasshelper.utils.Utils;

/**
 *
 * @author David
 */
public class OneLayerChangeAction extends ChangeAction {
    
    private int positionBefore;
    private int positionAfter;
    private DrawPage drawPage;
    
    public OneLayerChangeAction( 
            DrawPage drawPage,
            int positionBefore, 
            int positionAfter ) {
        this.drawPage = drawPage;
        this.positionBefore = positionBefore;
        this.positionAfter = positionAfter;
    }
    
    @Override
    public void applyBeforeChange() {
        Utils.<Shape>swap( drawPage.getShapes(), positionBefore, positionAfter );
    }
    
    @Override
    public void applyAfterChange() {
        Utils.<Shape>swap( drawPage.getShapes(), positionAfter, positionBefore );
    }
    
}

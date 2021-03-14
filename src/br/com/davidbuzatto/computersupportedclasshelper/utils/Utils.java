/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.davidbuzatto.computersupportedclasshelper.utils;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 *
 * @author Prof. Dr. David Buzatto
 */
public class Utils {
    
    public static FontMetrics getFontMetrics( Font font ) {
        BufferedImage dummyImg = new BufferedImage( 1, 1, BufferedImage.TYPE_INT_ARGB );
        FontMetrics fm = dummyImg.getGraphics().getFontMetrics( font );
        dummyImg = null;
        return fm;
    }
    
    public static <T> void swap( List<T> list, int v, int w ) {
        T temp = list.get( v );
        list.set( v, list.get( w ) );
        list.set( w, temp );
    }
    
    public static Dimension getScreenSize() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }
    
}

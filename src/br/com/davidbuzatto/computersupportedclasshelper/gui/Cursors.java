/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.davidbuzatto.computersupportedclasshelper.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author David
 */
public class Cursors {
    
    private Cursor cBucket;
    private Cursor cMove;
    private Cursor cPencil;
    private Cursor cCorss;
    
    private static final Cursors INSTANCE;
    
    public enum Type {
        BUCKET,
        MOVE,
        PENCIL,
        CROSS
    }
    
    static {
        INSTANCE = new Cursors();
    }
    
    private Cursors() {
        
        try {
            
            Toolkit t = Toolkit.getDefaultToolkit();
            
            BufferedImage imgCross = new BufferedImage( 32, 32, BufferedImage.TYPE_INT_ARGB );
            BufferedImage imgBucket = new BufferedImage( 32, 32, BufferedImage.TYPE_INT_ARGB );
            BufferedImage imgMove = new BufferedImage( 32, 32, BufferedImage.TYPE_INT_ARGB );
            BufferedImage imgPencil = new BufferedImage( 32, 32, BufferedImage.TYPE_INT_ARGB );
            
            Graphics g = imgCross.createGraphics();
            g.setColor( Color.BLACK );
            g.drawOval( 8, 8, 6, 6 );
            g.drawLine( 11, 0, 11, 8 );
            g.drawLine( 11, 14, 11, 21 );
            g.drawLine( 0, 11, 8, 11 );
            g.drawLine( 14, 11, 21, 11 );
            
            g.dispose();
            
            imgBucket.getGraphics().drawImage( ImageIO.read( 
                    getClass().getResource( "/br/com/davidbuzatto/computersupportedclasshelper/gui/icons/bucket.png" ) ), 0, 0, null );
            imgMove.getGraphics().drawImage( ImageIO.read( 
                    getClass().getResource( "/br/com/davidbuzatto/computersupportedclasshelper/gui/icons/cursor_openhand.png" ) ), 0, 0, null );
            imgPencil.getGraphics().drawImage( ImageIO.read( 
                    getClass().getResource( "/br/com/davidbuzatto/computersupportedclasshelper/gui/icons/pencil.png" ) ), 0, 0, null );
            
            cBucket = t.createCustomCursor( imgBucket, new Point( 0, 12 ),  "bucket" );
            cMove = t.createCustomCursor( imgMove, new Point( 7, 7 ), "move" );
            cPencil = t.createCustomCursor( imgPencil, new Point( 0, 14 ), "pencil" );
            cCorss = t.createCustomCursor( imgCross, new Point( 11, 11 ), "cross" );
        
        } catch ( IOException exc ) {
            exc.printStackTrace();
        }
        
    }
    
    public static Cursor getCursor( Type type ) {
        
        switch ( type ) {
            case BUCKET:
                return INSTANCE.cBucket;
            case MOVE:
                return INSTANCE.cMove;
            case PENCIL:
                return INSTANCE.cPencil;
            case CROSS:
                return INSTANCE.cCorss;
            
        }
        
        return INSTANCE.cCorss;
        
    }
    
    
}

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
 * @author Prof. Dr. David Buzatto
 */
public class Cursors {
    
    private Cursor cBucket;
    private Cursor cMove;
    private Cursor cPencil;
    private Cursor cBrush;
    private Cursor cEraser;
    private Cursor cImage;
    private Cursor cText;
    private Cursor cCross;
    
    private static final Cursors INSTANCE;
    private static final Toolkit TOOLKIT;
    
    public enum Type {
        BUCKET,
        MOVE,
        PENCIL,
        BRUSH,
        ERASER,
        IMAGE,
        TEXT,
        CROSS
    }
    
    static {
        TOOLKIT = Toolkit.getDefaultToolkit();
        INSTANCE = new Cursors();
    }
    
    private Cursors() {
        
        try {
            
            BufferedImage imgBucket = new BufferedImage( 32, 32, BufferedImage.TYPE_INT_ARGB );
            BufferedImage imgMove = new BufferedImage( 32, 32, BufferedImage.TYPE_INT_ARGB );
            BufferedImage imgPencil = new BufferedImage( 32, 32, BufferedImage.TYPE_INT_ARGB );
            BufferedImage imgBrush = new BufferedImage( 32, 32, BufferedImage.TYPE_INT_ARGB );
            BufferedImage imgEraser = new BufferedImage( 32, 32, BufferedImage.TYPE_INT_ARGB );
            BufferedImage imgImage = new BufferedImage( 32, 32, BufferedImage.TYPE_INT_ARGB );
            BufferedImage imgText = new BufferedImage( 32, 32, BufferedImage.TYPE_INT_ARGB );
            
            imgBucket.getGraphics().drawImage( ImageIO.read( 
                    getClass().getResource( "/br/com/davidbuzatto/computersupportedclasshelper/gui/icons/paintcan.png" ) ), 0, 0, null );
            imgMove.getGraphics().drawImage( ImageIO.read( 
                    getClass().getResource( "/br/com/davidbuzatto/computersupportedclasshelper/gui/icons/cursor_openhand.png" ) ), 0, 0, null );
            imgPencil.getGraphics().drawImage( ImageIO.read( 
                    getClass().getResource( "/br/com/davidbuzatto/computersupportedclasshelper/gui/icons/pencil.png" ) ), 0, 0, null );
            imgBrush.getGraphics().drawImage( ImageIO.read( 
                    getClass().getResource( "/br/com/davidbuzatto/computersupportedclasshelper/gui/icons/paintbrush.png" ) ), 0, 0, null );
            imgEraser.getGraphics().drawImage( ImageIO.read( 
                    getClass().getResource( "/br/com/davidbuzatto/computersupportedclasshelper/gui/icons/eraser.png" ) ), 0, 0, null );
            imgImage.getGraphics().drawImage( ImageIO.read( 
                    getClass().getResource( "/br/com/davidbuzatto/computersupportedclasshelper/gui/icons/picture_add.png" ) ), 0, 0, null );
            imgText.getGraphics().drawImage( ImageIO.read( 
                    getClass().getResource( "/br/com/davidbuzatto/computersupportedclasshelper/gui/icons/font_add.png" ) ), 0, 0, null );
            
            cBucket = TOOLKIT.createCustomCursor( imgBucket, new Point( 14, 11 ),  "bucket" );
            cMove = TOOLKIT.createCustomCursor( imgMove, new Point( 7, 7 ), "move" );
            cPencil = TOOLKIT.createCustomCursor( imgPencil, new Point( 0, 14 ), "pencil" );
            cBrush = TOOLKIT.createCustomCursor( imgBrush, new Point( 0, 14 ), "brush" );
            cEraser = TOOLKIT.createCustomCursor( imgEraser, new Point( 0, 14 ), "eraser" );
            cImage = TOOLKIT.createCustomCursor( imgImage, new Point( 0, 14 ), "image" );
            cText = TOOLKIT.createCustomCursor( imgText, new Point( 0, 14 ), "text" );
            cCross = TOOLKIT.createCustomCursor( createCrossImage( Color.BLACK ), new Point( 11, 11 ), "cross" );
        
        } catch ( IOException exc ) {
            exc.printStackTrace();
        }
        
    }
    
    public static Cursor getCursor( Type type, Color c ) {
        
        switch ( type ) {
            case BUCKET:
                return INSTANCE.cBucket;
            case MOVE:
                return INSTANCE.cMove;
            case PENCIL:
                return INSTANCE.cPencil;
            case BRUSH:
                return INSTANCE.cBrush;
            case ERASER:
                return INSTANCE.cEraser;
            case IMAGE:
                return INSTANCE.cImage;
            case TEXT:
                return INSTANCE.cText;
            case CROSS:
                changeCrossColor( c );
                return INSTANCE.cCross;
            
        }
        
        return INSTANCE.cCross;
        
    }
    
    private static BufferedImage createCrossImage( Color c ) {
        
        BufferedImage imgCross = new BufferedImage( 32, 32, BufferedImage.TYPE_INT_ARGB );
        
        Graphics g = imgCross.createGraphics();
        g.setColor( c );
        g.drawOval( 8, 8, 6, 6 );
        g.drawLine( 11, 0, 11, 8 );
        g.drawLine( 11, 14, 11, 21 );
        g.drawLine( 0, 11, 8, 11 );
        g.drawLine( 14, 11, 21, 11 );

        g.dispose();
        
        return imgCross;
            
    }
    
    public static void changeCrossColor( Color c ) {
        INSTANCE.cCross = TOOLKIT.createCustomCursor( createCrossImage( c ), new Point( 11, 11 ), "cross" );
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.davidbuzatto.computersupportedclasshelper.utils;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author David
 */
public class DrawingConfigs implements Serializable {
    
    private static final long serialVersionUID = Constants.SERIAL_VERSION;
    
    private static final DrawingConfigs INSTANCE;
    
    private double strokeWidth;
    private double eraserWidth;
    private double arcRadius;
    
    private int sideQuantity;
    
    private int fontSize;
    private FontTypeEnum fontType;
    private boolean fontIsBold;
    private boolean fontIsItalic;
    private FontAlignmentEnum fontAlignment;
    
    private int lineSheetDistance;
    private int gridDistance;
    private boolean drawLineSheet;
    private boolean drawGrid;
    
    private Map<String, Color> colors;
    private File defaultDir;
    
    transient private boolean processEventsMainWindow;
    
    static {
        INSTANCE = new DrawingConfigs();
    }
    
    private DrawingConfigs() {
        
        strokeWidth = 5;
        eraserWidth = 10;
        arcRadius = 10;
        sideQuantity = 5;
        
        fontSize = 20;
        fontType = FontTypeEnum.DIALOG;
        fontAlignment = FontAlignmentEnum.LEFT;
        
        lineSheetDistance = 30;
        gridDistance = 30;
        drawLineSheet = false;
        drawGrid = false;
        
        colors = new HashMap<>();
        
        colors.put( "sc", new Color( 0, 0, 0 ) );
        colors.put( "fc", null );
        colors.put( "bc", null );
        
        colors.put( "lsc", new Color( 0, 0, 0, 100 ) );
        colors.put( "gc", new Color( 0, 0, 0, 100 ) );
        
        colors.put( "sc1", new Color( 0, 0, 0 ) );
        colors.put( "sc2", new Color( 255, 255, 255 ) );
        colors.put( "sc3", new Color( 0, 153, 255 ) );
        colors.put( "sc4", new Color( 0, 204, 51 ) );
        colors.put( "sc5", new Color( 255, 102, 0 ) );
        colors.put( "sc6", new Color( 255, 0, 51 ) );
        colors.put( "sc7", new Color( 255, 0, 204 ) );
        colors.put( "sc8", new Color( 102, 0, 204 ) );
        
        colors.put( "fc1", null );
        colors.put( "fc2", null );
        colors.put( "fc3", null );
        colors.put( "fc4", null );
        colors.put( "fc5", null );
        colors.put( "fc6", null );
        colors.put( "fc7", null );
        colors.put( "fc8", null );
        
        File home = new File( System.getProperty( "user.home" ) + File.separator + "Documents" );
        if ( home.exists() ) {
            defaultDir = home;
        } else {
            defaultDir = new File( System.getProperty( "user.home" ) );
        }
        
        processEventsMainWindow = true;
        
    }
    
    public void save() {
        try {
            ObjectOutputStream o = new ObjectOutputStream( new FileOutputStream( new File( "conf" ) ) );
            o.writeObject( this );
            o.close();
        } catch ( IOException exc ) {
            exc.printStackTrace();
        }
    }
    
    @SuppressWarnings( "unchecked" )
    public void load() {
        
        try {
            
            File f = new File( "conf" );

            if ( !f.exists() ) {
                save();
            }

            ObjectInputStream i = new ObjectInputStream( new FileInputStream( new File( "conf" ) ) );
            DrawingConfigs c = (DrawingConfigs) i.readObject();
            i.close();
            
            this.strokeWidth = c.strokeWidth;
            this.eraserWidth = c.eraserWidth;
            this.arcRadius = c.arcRadius;
            this.sideQuantity = c.sideQuantity;
            
            this.fontType = c.fontType;
            this.fontSize = c.fontSize;
            this.fontIsBold = c.fontIsBold;
            this.fontIsItalic = c.fontIsItalic;
            this.fontAlignment = c.fontAlignment;
            
            this.lineSheetDistance = c.lineSheetDistance;
            this.gridDistance = c.gridDistance;
            this.drawLineSheet = c.drawLineSheet;
            this.drawGrid = c.drawGrid;
            
            this.colors = c.colors;
            this.defaultDir = c.defaultDir;
            
        } catch ( IOException | ClassNotFoundException exc ) {
            exc.printStackTrace();
        }
        
    }
    
    public static DrawingConfigs getInstance() {
        return INSTANCE;
    }

    public double getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth( double strokeWidth ) {
        this.strokeWidth = strokeWidth;
    }

    public double getEraserWidth() {
        return eraserWidth;
    }

    public void setEraserWidth( double eraserWidth ) {
        this.eraserWidth = eraserWidth;
    }

    public int getSideQuantity() {
        return sideQuantity;
    }

    public void setSideQuantity( int sideQuantity ) {
        this.sideQuantity = sideQuantity;
    }

    public boolean isProcessEventsMainWindow() {
        return processEventsMainWindow;
    }

    public void setProcessEventsMainWindow( boolean processEventsMainWindow ) {
        this.processEventsMainWindow = processEventsMainWindow;
    }

    public double getArcRadius() {
        return arcRadius;
    }

    public void setArcRadius( double arcRadius ) {
        this.arcRadius = arcRadius;
    }

    public Map<String, Color> getColors() {
        return colors;
    }

    public void setColors( Map<String, Color> colors ) {
        this.colors = colors;
    }

    public File getDefaultDir() {
        return defaultDir;
    }

    public void setDefaultDir( File defaultDir ) {
        this.defaultDir = defaultDir;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize( int fontSize ) {
        this.fontSize = fontSize;
    }

    public FontTypeEnum getFontType() {
        return fontType;
    }

    public void setFontType( FontTypeEnum fontType ) {
        this.fontType = fontType;
    }

    public boolean isFontIsBold() {
        return fontIsBold;
    }

    public void setFontIsBold( boolean fontIsBold ) {
        this.fontIsBold = fontIsBold;
    }

    public boolean isFontIsItalic() {
        return fontIsItalic;
    }

    public void setFontIsItalic( boolean fontIsItalic ) {
        this.fontIsItalic = fontIsItalic;
    }

    public FontAlignmentEnum getFontAlignment() {
        return fontAlignment;
    }

    public void setFontAlignment( FontAlignmentEnum fontAlignment ) {
        this.fontAlignment = fontAlignment;
    }

    public int getLineSheetDistance() {
        return lineSheetDistance;
    }

    public void setLineSheetDistance( int lineSheetDistance ) {
        this.lineSheetDistance = lineSheetDistance;
    }

    public int getGridDistance() {
        return gridDistance;
    }

    public void setGridDistance( int gridDistance ) {
        this.gridDistance = gridDistance;
    }

    public boolean isDrawLineSheet() {
        return drawLineSheet;
    }

    public void setDrawLineSheet( boolean drawLineSheet ) {
        this.drawLineSheet = drawLineSheet;
    }

    public boolean isDrawGrid() {
        return drawGrid;
    }

    public void setDrawGrid( boolean drawGrid ) {
        this.drawGrid = drawGrid;
    }
    
}

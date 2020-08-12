/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.davidbuzatto.computersupportedclasshelper.gui.geom;

import br.com.davidbuzatto.computersupportedclasshelper.utils.FontAlignmentEnum;
import br.com.davidbuzatto.computersupportedclasshelper.utils.FontTypeEnum;
import br.com.davidbuzatto.computersupportedclasshelper.utils.Utils;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

/**
 *
 * @author David
 */
public class Text extends Shape implements Serializable, Cloneable {

    private String text;
    transient private String[] lines;
    
    private Font font;
    private FontMetrics fontMetrics;
    private int lineHeight;
    private int maxWidth;
    
    private FontTypeEnum fontType;
    private int fontSize;
    private boolean fontIsBold;
    private boolean fontIsItalic;
    private FontAlignmentEnum fontAlignment;
    
    @Override
    public void draw( Graphics2D g2d ) {
        
        if ( lines != null ) {
            
            calculateDrawingBounds();
            g2d = (Graphics2D) g2d.create();

            g2d.setFont( font );

            if ( fillColor != null ) {
                g2d.setPaint( fillColor );
                g2d.fill( new Rectangle2D.Double( xStartD - 5, yStartD, xEndD-xStartD + 10, yEndD-yStartD ) );
            }

            if ( strokeColor == null ) {
                strokeColor = Color.BLACK;
            }

            g2d.setPaint( strokeColor );

            int lineCount = 1;
            int lineWidth;
            int xStartString = 0;
            
            for ( String line : lines ) {
                
                switch ( fontAlignment ) {
                    case LEFT:
                        xStartString = (int) xStartD;
                        break;
                    case CENTER:
                        lineWidth = fontMetrics.stringWidth( line );
                        xStartString = (int) xStartD + ( (int) ( xEndD-xStartD ) ) / 2 - lineWidth / 2;
                        break;
                    case RIGHT:
                        lineWidth = fontMetrics.stringWidth( line );
                        xStartString = (int) xEndD - lineWidth;
                        break;
                }
                
                g2d.drawString( line, xStartString, (int) yStartD + lineCount * lineHeight - lineHeight / 4 );
                lineCount++;
                
            }

            drawSelection( g2d );
            g2d.dispose();
            
        }
        
    }

    @Override
    public void calculateDrawingBounds() {
        
        prepareFont();
        
        xStartD = xStart;
        yStartD = yStart;
        
        maxWidth = 0;
        int lineWidth;
        
        for ( String line : lines ) {
            lineWidth = fontMetrics.stringWidth( line );
            if ( maxWidth < lineWidth ) {
                maxWidth = lineWidth;
            }
        }
        
        xEndD = xStartD + maxWidth;
        yEndD = yStartD + lineHeight * lines.length;
        
    }
    
    @Override
    public boolean intercepts( double x, double y ) {
        return x >= xStartD && x <= xEndD && y >= yStartD && y <= yEndD;
    }

    private Font createFont() {
        
        String fontName = "";
        int fontStyle = Font.PLAIN;
        
        switch ( fontType ) {
            case DIALOG:
                fontName = "Dialog";
                break;
            case SANS_SERIF:
                fontName = "SansSerif";
                break;
            case SERIF:
                fontName = "Serif";
                break;
            case MONOSPACED:
                fontName = "Monospaced";
                break;
        }
        
        if ( fontIsBold ) {
            fontStyle += Font.BOLD;
        }
        
        if ( fontIsItalic ) {
            fontStyle += Font.ITALIC;
        }
        
        return new Font( fontName, fontStyle, fontSize );
        
    }
    
    public void prepareFont() {
        font = createFont();
        fontMetrics = Utils.getFontMetrics( font );
        lineHeight = fontMetrics.getHeight();
    }
    
    public String getText() {
        return text;
    }

    public void setText( String text ) {
        lines = text.split( "\n" );
        this.text = text;
    }

    public FontTypeEnum getFontType() {
        return fontType;
    }

    public void setFontType( FontTypeEnum fontType ) {
        this.fontType = fontType;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize( int fontSize ) {
        this.fontSize = fontSize;
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
    
    @Override
    public Text clone() throws CloneNotSupportedException {
            
        Text clone = new Text();
        copyData( this, clone );
        
        clone.setText( text );
        clone.fontType = fontType;
        clone.fontSize = fontSize;
        clone.fontIsBold = fontIsBold;
        clone.fontIsItalic = fontIsItalic;
        clone.fontAlignment = fontAlignment;
        
        return clone;
        
    }
    
}

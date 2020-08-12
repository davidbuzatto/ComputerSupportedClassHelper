/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.davidbuzatto.computersupportedclasshelper.gui.geom;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.imageio.ImageIO;

/**
 *
 * @author David
 */
public class Image extends Shape implements Serializable, Cloneable {

    transient private BufferedImage image;
    
    private String format;
    private double scale;
    
    public Image() {
        format = "png";
        scale = 1;
    }
    
    @Override
    public void draw( Graphics2D g2d ) {
        
        if ( image != null ) {
            
            calculateDrawingBounds();
            
            g2d = (Graphics2D) g2d.create();
            g2d.drawImage( image, (int) xStartD, (int) yStartD, null );
            g2d.dispose();
            
        }
        
    }
    
    @Override
    public boolean intercepts( double x, double y ) {
        if ( image != null ) {
            return x >= xStartD && x <= xEndD && 
                   y >= yStartD && y <= yEndD;
        } else {
            return false;
        }
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage( BufferedImage image ) {
        this.image = image;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat( String format ) {
        this.format = format;
    }
    
    private void writeObject( ObjectOutputStream out ) throws IOException {
        out.defaultWriteObject();
        ImageIO.write( image, format, out );
    }

    private void readObject( ObjectInputStream in ) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        image = ImageIO.read( in );
    }
    
    @Override
    public Image clone() throws CloneNotSupportedException {
            
        Image clone = new Image();
        copyData( this, clone );
        
        clone.image = image;
        clone.format = format;
            
        return clone;
        
    }
    
}

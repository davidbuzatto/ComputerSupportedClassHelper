/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.davidbuzatto.computersupportedclasshelper.utils;

import java.awt.BasicStroke;
import java.awt.Color;

/**
 *
 * @author David
 */
public interface Constants {
    
    public static final Color TRANSPARENT_COLOR = new Color( 0, 0, 0, 1 );
    
    public static final Color SELECTED_COLOR = new Color( 204, 204, 204, 190 );
    public static final BasicStroke[] SELECTED_STROKES = new BasicStroke[]{ 
        new BasicStroke( 1, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND, 5, new float[]{ 5 }, 5f ),
        new BasicStroke( 1, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND, 5, new float[]{ 5 }, 4f ),
        new BasicStroke( 1, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND, 5, new float[]{ 5 }, 3f ),
        new BasicStroke( 1, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND, 5, new float[]{ 5 }, 2f ),
        new BasicStroke( 1, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND, 5, new float[]{ 5 }, 1f ),
        new BasicStroke( 1, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND, 5, new float[]{ 5 }, 0f )
    };
    
    public static final Color COMPONENT_BACKGROUND_COLOR = new Color( 40, 44, 52, 190 );
    public static final Color COMPONENT_STROKE_COLOR = new Color( 72, 77, 87 );
    
    public static final Color COMPONENT_BACKGROUND_COLOR_INFORMATION = new Color( 35, 45, 56, 190 );
    public static final Color COMPONENT_STROKE_COLOR_INFORMATION = new Color( 65, 86, 99 );
    
    public static final Color COMPONENT_BACKGROUND_COLOR_QUESTION = new Color( 39, 52, 41, 190 );
    public static final Color COMPONENT_STROKE_COLOR_QUESTION = new Color( 74, 89, 77 );
    
    public static final Color COMPONENT_BACKGROUND_COLOR_WARNING = new Color( 56, 51, 35, 190 );
    public static final Color COMPONENT_STROKE_COLOR_WARNING = new Color( 91, 89, 72 );
    
    public static final Color COMPONENT_BACKGROUND_COLOR_ERROR = new Color( 58, 33, 33, 190 );
    public static final Color COMPONENT_STROKE_COLOR_ERROR = new Color( 90, 73, 73 );
    
}

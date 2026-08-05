/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.davidbuzatto.computersupportedclasshelper.gui.geom;

import java.util.ArrayList;
import java.util.List;
import org.locationtech.jts.geom.Coordinate;

/**
 * Conditions the raw mouse/pen samples captured by the freehand drawing tools
 * (pencil, brush, eraser) before they are handed to the Catmull-Rom spline in
 * CatmullRom. Two problems show up when the input device moves fast:
 *
 * 1) hand tremor shows up as small zig-zags between consecutive raw samples;
 * 2) consecutive samples end up far apart, so the spline has to guess the
 *    shape of the stroke across a long, straight gap.
 *
 * smooth() addresses (1) with an exponential moving average and resample()
 * addresses (2) by inserting synthetic points along the gap.
 *
 * @author Prof. Dr. David Buzatto
 */
public class FreehandSmoothing {

    // Closer to 1 favors the previous smoothed position, removing more jitter
    // but adding more lag between the pointer and the drawn stroke.
    public static final double DEFAULT_SMOOTHING_FACTOR = 0.35;

    // Above this distance (in pixels) between two consecutive samples,
    // synthetic points are inserted so no segment fed to the spline is longer
    // than this, regardless of how fast the mouse/pen moved.
    public static final double DEFAULT_MAX_SEGMENT_LENGTH = 5.0;

    private FreehandSmoothing() {
    }

    /**
     * Exponential smoothing: moves the previous smoothed position a fraction of the way
     * towards the new raw sample, damping high-frequency jitter.
     *
     * @param previousSmoothed the previously smoothed position.
     * @param rawX raw x coordinate of the new sample.
     * @param rawY raw y coordinate of the new sample.
     * @param factor weight kept from previousSmoothed, in [0, 1). 0 disables smoothing.
     * @return the new smoothed position.
     */
    public static Coordinate smooth( Coordinate previousSmoothed, double rawX, double rawY, double factor ) {
        return new Coordinate(
                previousSmoothed.x * factor + rawX * ( 1 - factor ),
                previousSmoothed.y * factor + rawY * ( 1 - factor ) );
    }

    /**
     * Returns the points needed to get from "from" to "to" without any segment longer than
     * maxSegmentLength, by linearly interpolating synthetic points along the way. The
     * returned list always ends with "to" and never includes "from".
     *
     * @param from the last point already captured.
     * @param to the new point just captured.
     * @param maxSegmentLength the longest allowed distance between consecutive points.
     * @return "to", possibly preceded by synthetic in-between points.
     */
    public static List<Coordinate> resample( Coordinate from, Coordinate to, double maxSegmentLength ) {

        List<Coordinate> points = new ArrayList<>();

        double dx = to.x - from.x;
        double dy = to.y - from.y;
        double distance = Math.sqrt( dx * dx + dy * dy );

        if ( distance > maxSegmentLength ) {

            int steps = (int) Math.ceil( distance / maxSegmentLength );

            for ( int i = 1; i < steps; i++ ) {
                double t = (double) i / steps;
                points.add( new Coordinate( from.x + dx * t, from.y + dy * t ) );
            }

        }

        points.add( to );
        return points;

    }

}

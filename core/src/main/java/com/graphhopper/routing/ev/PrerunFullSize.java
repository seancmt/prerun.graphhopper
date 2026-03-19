package com.graphhopper.routing.ev;

/**
 * Whether full-size 4x4 vehicles are allowed on this trail/road.
 */
public class PrerunFullSize {
    public static final String KEY = "prerun_full_size";

    public static BooleanEncodedValue create() {
        return new SimpleBooleanEncodedValue(KEY);
    }
}

package com.graphhopper.routing.ev;

/**
 * Whether UTVs/SxS (50" width) are allowed on this trail/road.
 */
public class PrerunUtv50 {
    public static final String KEY = "prerun_utv_50";

    public static BooleanEncodedValue create() {
        return new SimpleBooleanEncodedValue(KEY);
    }
}

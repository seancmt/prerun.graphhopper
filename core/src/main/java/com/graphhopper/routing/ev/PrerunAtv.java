package com.graphhopper.routing.ev;

/**
 * Whether ATVs/quads are allowed on this trail/road.
 */
public class PrerunAtv {
    public static final String KEY = "prerun_atv";

    public static BooleanEncodedValue create() {
        return new SimpleBooleanEncodedValue(KEY);
    }
}

package com.graphhopper.routing.ev;

/**
 * Whether dirt bikes are allowed on this trail/road.
 */
public class PrerunDirtBike {
    public static final String KEY = "prerun_dirt_bike";

    public static BooleanEncodedValue create() {
        return new SimpleBooleanEncodedValue(KEY);
    }
}

package com.graphhopper.routing.ev;

import com.graphhopper.util.Helper;

/**
 * Whether the trail/road came from USFS roads, USFS trails, BLM, state, or OSM data.
 */
public enum PrerunSource {
    MISSING, ROAD, TRAIL;

    public static final String KEY = "prerun_source";

    public static EnumEncodedValue<PrerunSource> create() {
        return new EnumEncodedValue<>(KEY, PrerunSource.class);
    }

    @Override
    public String toString() {
        return Helper.toLowerCase(super.toString());
    }

    public static PrerunSource find(String name) {
        if (Helper.isEmpty(name))
            return MISSING;
        try {
            return PrerunSource.valueOf(Helper.toUpperCase(name));
        } catch (IllegalArgumentException ex) {
            return MISSING;
        }
    }
}

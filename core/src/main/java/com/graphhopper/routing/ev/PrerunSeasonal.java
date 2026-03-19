package com.graphhopper.routing.ev;

import com.graphhopper.util.Helper;

/**
 * Whether the trail/road is seasonal or open year-round.
 */
public enum PrerunSeasonal {
    MISSING, SEASONAL, YEARLONG;

    public static final String KEY = "prerun_seasonal";

    public static EnumEncodedValue<PrerunSeasonal> create() {
        return new EnumEncodedValue<>(KEY, PrerunSeasonal.class);
    }

    @Override
    public String toString() {
        return Helper.toLowerCase(super.toString());
    }

    public static PrerunSeasonal find(String name) {
        if (Helper.isEmpty(name))
            return MISSING;
        try {
            return PrerunSeasonal.valueOf(Helper.toUpperCase(name.trim()));
        } catch (IllegalArgumentException ex) {
            return MISSING;
        }
    }
}

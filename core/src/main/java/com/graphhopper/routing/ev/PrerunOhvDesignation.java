package com.graphhopper.routing.ev;

import com.graphhopper.util.Helper;

/**
 * BLM OHV designation: Open, Limited, or Closed.
 */
public enum PrerunOhvDesignation {
    MISSING, OPEN, LIMITED, CLOSED;

    public static final String KEY = "prerun_ohv_designation";

    public static EnumEncodedValue<PrerunOhvDesignation> create() {
        return new EnumEncodedValue<>(KEY, PrerunOhvDesignation.class);
    }

    @Override
    public String toString() {
        return Helper.toLowerCase(super.toString());
    }

    public static PrerunOhvDesignation find(String name) {
        if (Helper.isEmpty(name))
            return MISSING;
        try {
            return PrerunOhvDesignation.valueOf(Helper.toUpperCase(name.trim()));
        } catch (IllegalArgumentException ex) {
            return MISSING;
        }
    }
}

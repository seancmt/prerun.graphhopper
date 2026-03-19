package com.graphhopper.routing.ev;

import com.graphhopper.util.Helper;

/**
 * USFS trail class: TC1 (minimally developed) through TC5 (fully developed).
 */
public enum PrerunTrailClass {
    MISSING, TC1, TC2, TC3, TC4, TC5;

    public static final String KEY = "prerun_trail_class";

    public static EnumEncodedValue<PrerunTrailClass> create() {
        return new EnumEncodedValue<>(KEY, PrerunTrailClass.class);
    }

    @Override
    public String toString() {
        return Helper.toLowerCase(super.toString());
    }

    public static PrerunTrailClass find(String name) {
        if (Helper.isEmpty(name))
            return MISSING;
        // Handle full strings like "TC2 - MODERATELY DEVELOPED"
        String code = name.trim().split(" ")[0].toUpperCase();
        try {
            return PrerunTrailClass.valueOf(code);
        } catch (IllegalArgumentException ex) {
            return MISSING;
        }
    }
}

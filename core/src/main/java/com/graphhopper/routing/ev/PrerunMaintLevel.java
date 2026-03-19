package com.graphhopper.routing.ev;

import com.graphhopper.util.Helper;

/**
 * USFS road maintenance level: L1 (basic custodial) through L5 (high standard).
 */
public enum PrerunMaintLevel {
    MISSING, L1, L2, L3, L4, L5;

    public static final String KEY = "prerun_maint_level";

    public static EnumEncodedValue<PrerunMaintLevel> create() {
        return new EnumEncodedValue<>(KEY, PrerunMaintLevel.class);
    }

    @Override
    public String toString() {
        return Helper.toLowerCase(super.toString());
    }

    public static PrerunMaintLevel find(String name) {
        if (Helper.isEmpty(name))
            return MISSING;
        // Handle full strings like "3 - SUITABLE FOR PASSENGER CARS"
        String level = name.trim().split(" ")[0];
        try {
            int num = Integer.parseInt(level);
            if (num >= 1 && num <= 5)
                return PrerunMaintLevel.valueOf("L" + num);
        } catch (NumberFormatException ignored) {
        }
        return MISSING;
    }
}

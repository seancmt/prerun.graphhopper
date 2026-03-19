package com.graphhopper.routing.util.parsers;

import com.graphhopper.reader.ReaderWay;
import com.graphhopper.routing.ev.*;
import com.graphhopper.storage.IntsRef;

/**
 * Parses PreRun custom OSM tags (prerun:*) into encoded values for off-road routing.
 */
public class OSMPrerunTagParser implements TagParser {

    private final EnumEncodedValue<PrerunSource> sourceEnc;
    private final EnumEncodedValue<PrerunTrailClass> trailClassEnc;
    private final EnumEncodedValue<PrerunMaintLevel> maintLevelEnc;
    private final EnumEncodedValue<PrerunOhvDesignation> ohvDesignationEnc;
    private final EnumEncodedValue<PrerunSeasonal> seasonalEnc;
    private final BooleanEncodedValue dirtBikeEnc;
    private final BooleanEncodedValue atvEnc;
    private final BooleanEncodedValue utv50Enc;
    private final BooleanEncodedValue fullSizeEnc;

    public OSMPrerunTagParser(
            EnumEncodedValue<PrerunSource> sourceEnc,
            EnumEncodedValue<PrerunTrailClass> trailClassEnc,
            EnumEncodedValue<PrerunMaintLevel> maintLevelEnc,
            EnumEncodedValue<PrerunOhvDesignation> ohvDesignationEnc,
            EnumEncodedValue<PrerunSeasonal> seasonalEnc,
            BooleanEncodedValue dirtBikeEnc,
            BooleanEncodedValue atvEnc,
            BooleanEncodedValue utv50Enc,
            BooleanEncodedValue fullSizeEnc) {
        this.sourceEnc = sourceEnc;
        this.trailClassEnc = trailClassEnc;
        this.maintLevelEnc = maintLevelEnc;
        this.ohvDesignationEnc = ohvDesignationEnc;
        this.seasonalEnc = seasonalEnc;
        this.dirtBikeEnc = dirtBikeEnc;
        this.atvEnc = atvEnc;
        this.utv50Enc = utv50Enc;
        this.fullSizeEnc = fullSizeEnc;
    }

    @Override
    public void handleWayTags(int edgeId, EdgeIntAccess edgeIntAccess, ReaderWay readerWay, IntsRef relationFlags) {
        // Source (road/trail)
        PrerunSource source = PrerunSource.find(readerWay.getTag("prerun:source"));
        if (source != PrerunSource.MISSING)
            sourceEnc.setEnum(false, edgeId, edgeIntAccess, source);

        // Trail class (TC1-TC5)
        PrerunTrailClass trailClass = PrerunTrailClass.find(readerWay.getTag("prerun:trail_class"));
        if (trailClass != PrerunTrailClass.MISSING)
            trailClassEnc.setEnum(false, edgeId, edgeIntAccess, trailClass);

        // Maintenance level (1-5)
        PrerunMaintLevel maintLevel = PrerunMaintLevel.find(readerWay.getTag("prerun:maint_level"));
        if (maintLevel != PrerunMaintLevel.MISSING)
            maintLevelEnc.setEnum(false, edgeId, edgeIntAccess, maintLevel);

        // OHV designation (Open/Limited/Closed)
        PrerunOhvDesignation ohvDes = PrerunOhvDesignation.find(readerWay.getTag("prerun:ohv_designation"));
        if (ohvDes != PrerunOhvDesignation.MISSING)
            ohvDesignationEnc.setEnum(false, edgeId, edgeIntAccess, ohvDes);

        // Seasonal
        PrerunSeasonal seasonal = PrerunSeasonal.find(readerWay.getTag("prerun:seasonal"));
        if (seasonal != PrerunSeasonal.MISSING)
            seasonalEnc.setEnum(false, edgeId, edgeIntAccess, seasonal);

        // Vehicle types — read from the comma-separated prerun:vehicle_types tag
        String vehicleTypes = readerWay.getTag("prerun:vehicle_types", "");
        if (!vehicleTypes.isEmpty()) {
            if (vehicleTypes.contains("dirt_bike"))
                dirtBikeEnc.setBool(false, edgeId, edgeIntAccess, true);
            if (vehicleTypes.contains("atv"))
                atvEnc.setBool(false, edgeId, edgeIntAccess, true);
            if (vehicleTypes.contains("utv_50"))
                utv50Enc.setBool(false, edgeId, edgeIntAccess, true);
            if (vehicleTypes.contains("full_size"))
                fullSizeEnc.setBool(false, edgeId, edgeIntAccess, true);
        }
    }
}

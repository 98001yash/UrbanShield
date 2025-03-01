package com.company.urban.UrbanShield.utils;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

public class GeometryUtils {

    public static Point createPoint(double[] coordinates) {
        if (coordinates == null || coordinates.length != 2) {
            throw new IllegalArgumentException("Coordinates must be an array of two doubles: [longitude, latitude].");
        }

        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
        Coordinate coordinate = new Coordinate(coordinates[0], coordinates[1]);
        return geometryFactory.createPoint(coordinate);
    }
}

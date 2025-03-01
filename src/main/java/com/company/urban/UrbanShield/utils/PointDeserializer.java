package com.company.urban.UrbanShield.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

import java.io.IOException;

public class PointDeserializer extends JsonDeserializer<Point> {
    @Override
    public Point deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        double[] coordinates = jsonParser.readValueAs(double[].class);
        if (coordinates.length != 2) {
            throw new IOException("Invalid coordinates: " + coordinates);
        }

        GeometryFactory geometryFactory = new GeometryFactory();
        Coordinate coordinate = new Coordinate(coordinates[0], coordinates[1]);
        return geometryFactory.createPoint(coordinate);
    }
}

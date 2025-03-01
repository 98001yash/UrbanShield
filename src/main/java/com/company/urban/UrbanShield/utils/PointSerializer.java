package com.company.urban.UrbanShield.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.JsonSerializer;
import org.locationtech.jts.geom.Point;

import java.io.IOException;

public class PointSerializer extends JsonSerializer<Point> {
    @Override
    public void serialize(Point point, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (point != null) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("coordinates");
            jsonGenerator.writeStartArray();
            jsonGenerator.writeNumber(point.getX());
            jsonGenerator.writeNumber(point.getY());
            jsonGenerator.writeEndArray();
            jsonGenerator.writeEndObject();
        }
    }
}

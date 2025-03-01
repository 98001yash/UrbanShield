package com.company.urban.UrbanShield.dto;

import com.company.urban.UrbanShield.utils.PointDeserializer;
import com.company.urban.UrbanShield.utils.PointSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Data
public class ConstructionSiteDto {
    private Long id;
    private String name;
    private String address;
    private String ownerName;

    @JsonSerialize(using = PointSerializer.class)
    @JsonDeserialize(using = PointDeserializer.class)
    private Point location;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    // Method to get coordinates as an array
    public double[] getCoordinates() {
        return new double[]{location.getX(), location.getY()};
    }
}

package com.company.urban.UrbanShield.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationDto {
    private Long id;

    private Point coordinates;

    private String description;
}

package com.company.urban.UrbanShield.services;

import com.company.urban.UrbanShield.dto.LocationDto;
import org.locationtech.jts.geom.Point;

import java.util.List;

public interface LocationService {

    LocationDto findByCoordinates(Point coordinates);
    List<LocationDto> findByDescriptionContaining(String keyword);
}

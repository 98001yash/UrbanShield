package com.company.urban.UrbanShield.repositories;

import com.company.urban.UrbanShield.dto.LocationDto;
import com.company.urban.UrbanShield.entities.Location;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location,Long> {

}

package com.company.urban.UrbanShield.repositories;

import com.company.urban.UrbanShield.dto.ConstructionSiteDto;
import com.company.urban.UrbanShield.entities.ConstructionSite;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ConstructionSiteRepository extends JpaRepository<ConstructionSite, Long> {

    List<ConstructionSite> findByOwnerName(String ownerName);

    List<ConstructionSite> findByStartDateAfter(LocalDateTime startDate);

    Optional<ConstructionSite> findByLocation(Point location);
}

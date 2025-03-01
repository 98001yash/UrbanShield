package com.company.urban.UrbanShield.repositories;

import com.company.urban.UrbanShield.dto.ConstructionPermitDto;
import com.company.urban.UrbanShield.entities.ConstructionPermit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ConstructionPermitRepository extends JpaRepository<ConstructionPermit,Long> {

    Optional<ConstructionPermit> findByConstructionSiteId(Long constructionSiteId);

    Optional<ConstructionPermit> findByPermitNumber(String permitNumber);

    List<ConstructionPermit> findByIssueDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
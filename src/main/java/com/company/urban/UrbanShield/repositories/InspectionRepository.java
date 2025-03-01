package com.company.urban.UrbanShield.repositories;

import com.company.urban.UrbanShield.dto.InspectionDto;
import com.company.urban.UrbanShield.entities.Inspection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface InspectionRepository extends JpaRepository<Inspection, Long> {

    List<Inspection> findByConstructionSiteId(Long constructionSiteId);

    List<Inspection> findByInspectorName(String inspectorName);

    List<Inspection> findByInspectionDate(LocalDateTime date);
}
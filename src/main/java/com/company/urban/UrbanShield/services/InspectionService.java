package com.company.urban.UrbanShield.services;

import com.company.urban.UrbanShield.dto.InspectionDto;

import java.time.LocalDateTime;
import java.util.List;

public interface InspectionService {

    List<InspectionDto> findByConstructionSite(Long constructionSiteId);
    List<InspectionDto> findByInspectorName(String inspectorName);
    List<InspectionDto> findByInspectionDate(LocalDateTime date);

    InspectionDto createInspection(InspectionDto inspectionDto);

    InspectionDto updateInspection(Long id, InspectionDto inspectionDto);
}

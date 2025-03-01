package com.company.urban.UrbanShield.services;

import com.company.urban.UrbanShield.dto.ConstructionPermitDto;

import java.time.LocalDateTime;
import java.util.List;

public interface ConstructionPermitService {

    ConstructionPermitDto findByConstructionSite(Long constructionSiteId);
    ConstructionPermitDto findByPermitNumber(String permitNumber);
    List<ConstructionPermitDto> findByIssueDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    ConstructionPermitDto createPermit(ConstructionPermitDto permitDto);
    ConstructionPermitDto updatePermit(Long id, ConstructionPermitDto permitDto);
}

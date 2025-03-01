package com.company.urban.UrbanShield.services;

import com.company.urban.UrbanShield.dto.ConstructionSiteDto;

import java.time.LocalDateTime;
import java.util.List;

public interface ConstructionSiteService {
    ConstructionSiteDto findByLocation(double[] coordinates);
    List<ConstructionSiteDto> findByOwnerName(String ownerName);
    List<ConstructionSiteDto> findByStartDateAfter(LocalDateTime startDate);
    ConstructionSiteDto createConstructionSite(ConstructionSiteDto constructionSiteDto);
    ConstructionSiteDto updateConstructionSite(Long id, ConstructionSiteDto constructionSiteDto);
    void deleteConstructionSite(Long id);
    ConstructionSiteDto findById(Long id);
}

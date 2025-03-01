package com.company.urban.UrbanShield.services;

import com.company.urban.UrbanShield.dto.ViolationDto;

import java.time.LocalDateTime;
import java.util.List;

public interface ViolationService {

    List<ViolationDto> findByConstructionSite(Long constructionSiteId);
    List<ViolationDto> findByResolved(boolean resolved);
    List<ViolationDto> findByViolationDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    ViolationDto resolveViolation(Long violationId);

    ViolationDto createViolation(ViolationDto violationDto);
}

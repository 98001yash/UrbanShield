package com.company.urban.UrbanShield.services;

import com.company.urban.UrbanShield.dto.PhotoEvidenceDto;

import java.time.LocalDateTime;
import java.util.List;

public interface PhotoEvidentService {

    List<PhotoEvidenceDto> findByConstructionSite(Long constructionSiteId);

    List<PhotoEvidenceDto> findByCaptureDate(LocalDateTime date);

    List<PhotoEvidenceDto> findByPhotoUrlContaining(String keyword);
    PhotoEvidenceDto savePhotoEvidence(PhotoEvidenceDto photoEvidenceDto);
}

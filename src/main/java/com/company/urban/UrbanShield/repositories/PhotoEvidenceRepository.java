package com.company.urban.UrbanShield.repositories;

import com.company.urban.UrbanShield.dto.PhotoEvidenceDto;
import com.company.urban.UrbanShield.entities.PhotoEvidence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface PhotoEvidenceRepository extends JpaRepository<PhotoEvidence,Long> {

    List<PhotoEvidence> findByConstructionSiteId(Long constructionSiteId);

    List<PhotoEvidence> findByCaptureDate(LocalDateTime date);

    List<PhotoEvidence> findByPhotoUrlContaining(String keyword);
}
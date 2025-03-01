package com.company.urban.UrbanShield.repositories;

import com.company.urban.UrbanShield.dto.ViolationDto;
import com.company.urban.UrbanShield.entities.Violation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ViolationRepository extends JpaRepository<Violation,Long> {

    List<Violation> findByConstructionSiteId(Long constructionSiteId);

    List<Violation> findByResolved(boolean resolved);

    List<Violation> findByViolationDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
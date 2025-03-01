package com.company.urban.UrbanShield.repositories;

import com.company.urban.UrbanShield.entities.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<Alert,Long> {

    List<Alert> findByConstructionSiteId(Long constructionSiteId);

    List<Alert> findByAcknowledged(boolean acknowledged);

    List<Alert> findByAlertDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
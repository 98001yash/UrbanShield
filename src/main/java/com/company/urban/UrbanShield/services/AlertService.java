package com.company.urban.UrbanShield.services;

import com.company.urban.UrbanShield.dto.AlertDto;

import java.time.LocalDateTime;
import java.util.List;

public interface AlertService {

    List<AlertDto> findByConstructionSite(Long constructionSiteId);
    List<AlertDto> findByAcknowledged(boolean acknowledged);
    List<AlertDto> findByAlertDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    AlertDto saveAlert(AlertDto alertDto);

    AlertDto updateAlert(Long id, AlertDto alertDto);

    void deleteAlert(Long id);
}

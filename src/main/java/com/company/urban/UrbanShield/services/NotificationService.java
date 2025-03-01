package com.company.urban.UrbanShield.services;

import com.company.urban.UrbanShield.dto.NotificationDto;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationService {
    List<NotificationDto> findByUser(Long userId);
    List<NotificationDto> findBySentDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<NotificationDto> findByAcknowledged(boolean acknowledged);
    NotificationDto saveNotification(NotificationDto notificationDto);
}

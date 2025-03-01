package com.company.urban.UrbanShield.repositories;

import com.company.urban.UrbanShield.dto.NotificationDto;
import com.company.urban.UrbanShield.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {

    List<Notification> findByUserId(Long userId);

    List<Notification> findBySentDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Notification> findByAcknowledged(boolean acknowledged);
}

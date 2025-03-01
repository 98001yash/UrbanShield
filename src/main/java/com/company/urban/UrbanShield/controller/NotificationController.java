package com.company.urban.UrbanShield.controller;

import com.company.urban.UrbanShield.dto.NotificationDto;
import com.company.urban.UrbanShield.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NotificationDto>> getByUser(@PathVariable Long userId) {
        List<NotificationDto> notifications = notificationService.findByUser(userId);
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/date")
    public ResponseEntity<List<NotificationDto>> getByDateRange(
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        List<NotificationDto> notifications = notificationService.findBySentDateBetween(startDate, endDate);
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/acknowledged")
    public ResponseEntity<List<NotificationDto>> getByAcknowledged(@RequestParam boolean acknowledged) {
        List<NotificationDto> notifications = notificationService.findByAcknowledged(acknowledged);
        return ResponseEntity.ok(notifications);
    }

    @PostMapping
    public ResponseEntity<NotificationDto> createNotification(@RequestBody NotificationDto notificationDto) {
        NotificationDto savedNotification = notificationService.saveNotification(notificationDto);
        return ResponseEntity.ok(savedNotification);
    }
}

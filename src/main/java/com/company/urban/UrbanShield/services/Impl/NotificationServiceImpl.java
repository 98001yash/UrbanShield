package com.company.urban.UrbanShield.services.Impl;

import com.company.urban.UrbanShield.dto.NotificationDto;
import com.company.urban.UrbanShield.entities.Notification;
import com.company.urban.UrbanShield.repositories.NotificationRepository;
import com.company.urban.UrbanShield.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final ModelMapper modelMapper;



    @Override
    public List<NotificationDto> findByUser(Long userId) {
        List<Notification> notifications = notificationRepository.findByUserId(userId);
        return notifications.stream()
                .map(notification -> modelMapper.map(notification, NotificationDto.class))
                .collect(Collectors.toList());
    }


    @Override
    public List<NotificationDto> findBySentDateBetween(LocalDateTime startDate, LocalDateTime endDate) {
        List<Notification> notifications = notificationRepository.findBySentDateBetween(startDate, endDate);
        return notifications.stream()
                .map(notification -> modelMapper.map(notification, NotificationDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<NotificationDto> findByAcknowledged(boolean acknowledged) {
        List<Notification> notifications = notificationRepository.findByAcknowledged(acknowledged);
        return notifications.stream()
                .map(notification -> modelMapper.map(notification, NotificationDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public NotificationDto saveNotification(NotificationDto notificationDto) {
        Notification notification = modelMapper.map(notificationDto, Notification.class);
        notification.setSentDate(LocalDateTime.now());
        Notification savedNotification = notificationRepository.save(notification);
        return modelMapper.map(savedNotification, NotificationDto.class);
    }
}

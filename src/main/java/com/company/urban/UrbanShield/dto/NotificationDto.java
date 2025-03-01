package com.company.urban.UrbanShield.dto;

import com.company.urban.UrbanShield.entities.User;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String message;
    private LocalDateTime sentDate;
}

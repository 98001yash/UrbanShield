package com.company.urban.UrbanShield.dto;

import com.company.urban.UrbanShield.entities.ConstructionSite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViolationDto {
    private Long id;
    private ConstructionSite constructionSite;
    private String description;
    private LocalDateTime violationDate;
    private boolean resolved;
}
package com.company.urban.UrbanShield.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhotoEvidenceDto {

    private Long id;
    private String photoUrl;
    private Long constructionSiteId;
    private LocalDateTime captureDate;
}

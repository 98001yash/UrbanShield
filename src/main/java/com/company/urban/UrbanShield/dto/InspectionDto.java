package com.company.urban.UrbanShield.dto;

import com.company.urban.UrbanShield.entities.ConstructionSite;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InspectionDto {


    private Long id;
    private ConstructionSite constructionSite;
    private LocalDateTime inspectionDate;
    private String inspectorName;
    private String findings;
    public String comments;

}

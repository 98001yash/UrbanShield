package com.company.urban.UrbanShield.dto;

import com.company.urban.UrbanShield.entities.ConstructionSite;

import lombok.Data;

import java.time.LocalDateTime;


    @Data
    public class ConstructionPermitDto {

        private Long id;
        private ConstructionSite constructionSite;
        private String permitNumber;
        private LocalDateTime issueDate;
        private LocalDateTime expirationDate;


        public Long getConstructionSiteId() {
            return constructionSite != null ? constructionSite.getId() : null;
        }


}

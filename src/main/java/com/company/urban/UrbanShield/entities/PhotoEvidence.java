package com.company.urban.UrbanShield.entities;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class PhotoEvidence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String photoUrl;

    @ManyToOne
    @JoinColumn(name = "construction_site_id")
    private ConstructionSite constructionSite;

    private LocalDateTime captureDate;
}

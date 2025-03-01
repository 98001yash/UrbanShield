package com.company.urban.UrbanShield.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConstructionSite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String ownerName;

    @Column(columnDefinition = "geometry(Point,4326)")
    private Point location; // JTS Point

    private LocalDateTime startDate;
    private LocalDateTime endDate;

}

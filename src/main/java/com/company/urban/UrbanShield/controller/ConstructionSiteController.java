package com.company.urban.UrbanShield.controller;

import com.company.urban.UrbanShield.dto.ConstructionSiteDto;
import com.company.urban.UrbanShield.services.ConstructionSiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/construction-sites")
@RequiredArgsConstructor
public class ConstructionSiteController {

    private final ConstructionSiteService constructionSiteService;

    @GetMapping("/location")
    public ResponseEntity<ConstructionSiteDto> getByLocation(@RequestParam double[] coordinates) {
        ConstructionSiteDto constructionSite = constructionSiteService.findByLocation(coordinates);
        return ResponseEntity.ok(constructionSite);
    }

    @GetMapping("/owner")
    public ResponseEntity<List<ConstructionSiteDto>> getByOwnerName(@RequestParam String ownerName) {
        List<ConstructionSiteDto> constructionSites = constructionSiteService.findByOwnerName(ownerName);
        return ResponseEntity.ok(constructionSites);
    }

    @GetMapping("/start-date")
    public ResponseEntity<List<ConstructionSiteDto>> getByStartDateAfter(@RequestParam LocalDateTime startDate) {
        List<ConstructionSiteDto> constructionSites = constructionSiteService.findByStartDateAfter(startDate);
        return ResponseEntity.ok(constructionSites);
    }

    @PostMapping
    public ResponseEntity<ConstructionSiteDto> createConstructionSite(@RequestBody ConstructionSiteDto constructionSiteDto) {
        ConstructionSiteDto createdSite = constructionSiteService.createConstructionSite(constructionSiteDto);
        return ResponseEntity.status(201).body(createdSite);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConstructionSiteDto> updateConstructionSite(@PathVariable Long id, @RequestBody ConstructionSiteDto constructionSiteDto) {
        ConstructionSiteDto updatedSite = constructionSiteService.updateConstructionSite(id, constructionSiteDto);
        return ResponseEntity.ok(updatedSite);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConstructionSite(@PathVariable Long id) {
        constructionSiteService.deleteConstructionSite(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConstructionSiteDto> getById(@PathVariable Long id) {
        ConstructionSiteDto constructionSite = constructionSiteService.findById(id);
        return ResponseEntity.ok(constructionSite);
    }
}

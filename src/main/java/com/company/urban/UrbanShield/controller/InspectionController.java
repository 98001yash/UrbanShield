package com.company.urban.UrbanShield.controller;

import com.company.urban.UrbanShield.dto.InspectionDto;
import com.company.urban.UrbanShield.services.InspectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/inspections")
@RequiredArgsConstructor
public class InspectionController {

    private final InspectionService inspectionService;

    @PostMapping
    public ResponseEntity<InspectionDto> createInspection(@RequestBody InspectionDto inspectionDto) {

        InspectionDto createdInspection = inspectionService.createInspection(inspectionDto);
        return ResponseEntity.status(201).body(createdInspection);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InspectionDto> updateInspection(@PathVariable Long id, @RequestBody InspectionDto inspectionDto) {
        InspectionDto updatedInspection = inspectionService.updateInspection(id, inspectionDto);
        return ResponseEntity.ok(updatedInspection);
    }

    @GetMapping("/construction-site/{constructionSiteId}")
    public ResponseEntity<List<InspectionDto>> getByConstructionSite(@PathVariable Long constructionSiteId) {
        List<InspectionDto> inspections = inspectionService.findByConstructionSite(constructionSiteId);
        return ResponseEntity.ok(inspections);
    }

    @GetMapping("/inspector/{inspectorName}")
    public ResponseEntity<List<InspectionDto>> getByInspectorName(@PathVariable String inspectorName) {
        List<InspectionDto> inspections = inspectionService.findByInspectorName(inspectorName);
        return ResponseEntity.ok(inspections);
    }

    @GetMapping("/date")
    public ResponseEntity<List<InspectionDto>> getByInspectionDate(@RequestParam String date) {
        LocalDateTime inspectionDate = LocalDateTime.parse(date);
        List<InspectionDto> inspections = inspectionService.findByInspectionDate(inspectionDate);
        return ResponseEntity.ok(inspections);
    }
}

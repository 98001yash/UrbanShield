package com.company.urban.UrbanShield.controller;

import com.company.urban.UrbanShield.dto.ViolationDto;
import com.company.urban.UrbanShield.services.ViolationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/violations")
@RequiredArgsConstructor
public class ViolationController {

    private final ViolationService violationService;

    @GetMapping("/construction-site/{siteId}")
    public ResponseEntity<List<ViolationDto>> getViolationsByConstructionSite(@PathVariable Long siteId) {
        List<ViolationDto> violations = violationService.findByConstructionSite(siteId);
        return ResponseEntity.ok(violations);
    }

    @GetMapping("/resolved")
    public ResponseEntity<List<ViolationDto>> getViolationsByResolved(@RequestParam boolean resolved) {
        List<ViolationDto> violations = violationService.findByResolved(resolved);
        return ResponseEntity.ok(violations);
    }


    @GetMapping("/date-range")
    public ResponseEntity<List<ViolationDto>> getViolationsByDateRange(
            @RequestParam("startDate") String startDateStr,
            @RequestParam("endDate") String endDateStr) {

        LocalDateTime startDate = LocalDateTime.parse(startDateStr);
        LocalDateTime endDate = LocalDateTime.parse(endDateStr);
        List<ViolationDto> violations = violationService.findByViolationDateBetween(startDate, endDate);
        return ResponseEntity.ok(violations);
    }


    @PostMapping
    public ResponseEntity<ViolationDto> createViolation(@RequestBody ViolationDto violationDto) {
        ViolationDto createdViolation = violationService.createViolation(violationDto);
        return ResponseEntity.ok(createdViolation);
    }

    @PostMapping("/{violationId}/resolve")
    public ResponseEntity<ViolationDto> resolveViolation(@PathVariable Long violationId) {
        ViolationDto resolvedViolation = violationService.resolveViolation(violationId);
        return ResponseEntity.ok(resolvedViolation);
    }
}
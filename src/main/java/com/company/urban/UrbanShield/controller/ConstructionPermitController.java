package com.company.urban.UrbanShield.controller;

import com.company.urban.UrbanShield.dto.ConstructionPermitDto;
import com.company.urban.UrbanShield.services.ConstructionPermitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/construction-permits")
@RequiredArgsConstructor
public class ConstructionPermitController {

    private final ConstructionPermitService constructionPermitService;

    @GetMapping("/construction-site/{constructionSiteId}")
    public ResponseEntity<ConstructionPermitDto> getByConstructionSite(@PathVariable Long constructionSiteId) {
        ConstructionPermitDto permit = constructionPermitService.findByConstructionSite(constructionSiteId);
        return ResponseEntity.ok(permit);
    }

    @GetMapping("/permit-number/{permitNumber}")
    public ResponseEntity<ConstructionPermitDto> getByPermitNumber(@PathVariable String permitNumber) {
        ConstructionPermitDto permit = constructionPermitService.findByPermitNumber(permitNumber);
        return ResponseEntity.ok(permit);
    }

    @GetMapping("/issue-date")
    public ResponseEntity<List<ConstructionPermitDto>> getByIssueDateBetween(
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        List<ConstructionPermitDto> permits = constructionPermitService.findByIssueDateBetween(startDate, endDate);
        return ResponseEntity.ok(permits);
    }
        @PostMapping
        public ResponseEntity<ConstructionPermitDto> createPermit(@Valid @RequestBody ConstructionPermitDto permitDto) {
            ConstructionPermitDto createdPermit = constructionPermitService.createPermit(permitDto);
            return new ResponseEntity<>(createdPermit, HttpStatus.CREATED);
        }


        @PutMapping("/{id}")
        public ResponseEntity<ConstructionPermitDto> updatePermit(
                @PathVariable Long id,
                @Valid @RequestBody ConstructionPermitDto permitDto) {
            ConstructionPermitDto updatedPermit = constructionPermitService.updatePermit(id, permitDto);
            return ResponseEntity.ok(updatedPermit);
        }
    }
package com.company.urban.UrbanShield.controller;


import com.company.urban.UrbanShield.dto.AlertDto;
import com.company.urban.UrbanShield.services.AlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/alerts")
@RequiredArgsConstructor
public class AlertController {

    private final AlertService alertService;


    @GetMapping("/construction-site/{id}")
    public ResponseEntity<List<AlertDto>> getAlertByConstructionSite(@PathVariable Long id) {
        List<AlertDto> alerts = alertService.findByConstructionSite(id);
        return ResponseEntity.ok(alerts);
    }

    @GetMapping("/acknowledged/{status}")
    public ResponseEntity<List<AlertDto>> getAlertByAcknowledge(@PathVariable boolean status) {
        List<AlertDto> alerts = alertService.findByAcknowledged(status);
        return ResponseEntity.ok(alerts);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<AlertDto>> getAlertByDateRange(
            @RequestParam("startDate") String startDateStr,
            @RequestParam("endDate") String endDateStr) {
        LocalDateTime startDate = LocalDateTime.parse(startDateStr);
        LocalDateTime endDate = LocalDateTime.parse(endDateStr);

        List<AlertDto> alerts = alertService.findByAlertDateBetween(startDate, endDate);
        return ResponseEntity.ok(alerts);

    }

    @PostMapping
    public ResponseEntity<AlertDto> createAlert(@RequestBody AlertDto alertDto) {
        AlertDto createdAlert = alertService.saveAlert(alertDto);
        return ResponseEntity.status(201).body(createdAlert);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlertDto> updateAlert(@PathVariable Long id, @RequestBody AlertDto alertDto) {
        AlertDto updatedAlert = alertService.updateAlert(id, alertDto);
        return ResponseEntity.ok(updatedAlert);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlert(@PathVariable Long id) {
        alertService.deleteAlert(id);
        return ResponseEntity.noContent().build();
    }

}



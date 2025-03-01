package com.company.urban.UrbanShield.services.Impl;

import com.company.urban.UrbanShield.dto.AlertDto;
import com.company.urban.UrbanShield.entities.Alert;
import com.company.urban.UrbanShield.repositories.AlertRepository;
import com.company.urban.UrbanShield.services.AlertService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AlertServiceImpl implements AlertService {

    private final AlertRepository alertRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<AlertDto> findByConstructionSite(Long constructionSiteId) {
        List<Alert> alerts = alertRepository.findByConstructionSiteId(constructionSiteId);
        return alerts.stream()
                .map(alert -> modelMapper.map(alert, AlertDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<AlertDto> findByAcknowledged(boolean acknowledged) {
        List<Alert> alerts = alertRepository.findByAcknowledged(acknowledged);
        return alerts.stream()
                .map(alert -> modelMapper.map(alert, AlertDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<AlertDto> findByAlertDateBetween(LocalDateTime startDate, LocalDateTime endDate) {
        List<Alert> alerts = alertRepository.findByAlertDateBetween(startDate, endDate);
        return alerts.stream()
                .map(alert -> modelMapper.map(alert, AlertDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public AlertDto saveAlert(AlertDto alertDto) {
        Alert alertEntity = modelMapper.map(alertDto, Alert.class);
        Alert savedAlert = alertRepository.save(alertEntity);
        return modelMapper.map(savedAlert, AlertDto.class);
    }

    @Override
    public AlertDto updateAlert(Long id, AlertDto alertDto) {
        Optional<Alert> optionalAlert = alertRepository.findById(id);

        if (optionalAlert.isPresent()) {
            Alert existingAlert = optionalAlert.get();
            modelMapper.map(alertDto, existingAlert);
            Alert updatedAlert = alertRepository.save(existingAlert);
            return modelMapper.map(updatedAlert, AlertDto.class);
        } else {
            throw new RuntimeException("Alert not found with ID: " + id);
        }
    }

    @Override
    public void deleteAlert(Long id) {
        if (alertRepository.existsById(id)) {
            alertRepository.deleteById(id);
        } else {
            throw new RuntimeException("Alert not found with ID: " + id);
        }
    }
}

package com.company.urban.UrbanShield.services.Impl;

import com.company.urban.UrbanShield.dto.ViolationDto;
import com.company.urban.UrbanShield.entities.Violation;
import com.company.urban.UrbanShield.repositories.ViolationRepository;
import com.company.urban.UrbanShield.services.ViolationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ViolationServiceImpl implements ViolationService {

    private final ViolationRepository violationRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ViolationDto> findByConstructionSite(Long constructionSiteId) {
        List<Violation> violations = violationRepository.findByConstructionSiteId(constructionSiteId);
        return violations.stream()
                .map(violation -> modelMapper.map(violation, ViolationDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ViolationDto> findByResolved(boolean resolved) {
        List<Violation> violations = violationRepository.findByResolved(resolved);
        return violations.stream()
                .map(violation -> modelMapper.map(violation, ViolationDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ViolationDto> findByViolationDateBetween(LocalDateTime startDate, LocalDateTime endDate) {
        List<Violation> violations = violationRepository.findByViolationDateBetween(startDate, endDate);
        return violations.stream()
                .map(violation -> modelMapper.map(violation, ViolationDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ViolationDto createViolation(ViolationDto violationDto) {
        Violation violation = modelMapper.map(violationDto, Violation.class);

        Violation savedViolation = violationRepository.save(violation);
        return modelMapper.map(savedViolation, ViolationDto.class);
    }

    @Override
    @Transactional
    public ViolationDto resolveViolation(Long violationId) {
        Optional<Violation> violationOptional = violationRepository.findById(violationId);
        if (violationOptional.isEmpty()) {
            throw new IllegalArgumentException("Violation with ID " + violationId + " not found");
        }

        Violation violation = violationOptional.get();
        violation.setResolved(true);
        Violation updatedViolation = violationRepository.save(violation);
        return modelMapper.map(updatedViolation, ViolationDto.class);
    }
}
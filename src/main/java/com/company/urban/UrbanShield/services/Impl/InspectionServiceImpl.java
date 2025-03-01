package com.company.urban.UrbanShield.services.Impl;

import com.company.urban.UrbanShield.dto.InspectionDto;
import com.company.urban.UrbanShield.entities.Inspection;
import com.company.urban.UrbanShield.repositories.InspectionRepository;
import com.company.urban.UrbanShield.services.InspectionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InspectionServiceImpl implements InspectionService {

    private final InspectionRepository inspectionRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<InspectionDto> findByConstructionSite(Long constructionSiteId) {
        List<Inspection> inspections = inspectionRepository.findByConstructionSiteId(constructionSiteId);
        return inspections.stream()
                .map(inspection -> modelMapper.map(inspection, InspectionDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<InspectionDto> findByInspectorName(String inspectorName) {
        List<Inspection> inspections = inspectionRepository.findByInspectorName(inspectorName);
        return inspections.stream()
                .map(inspection -> modelMapper.map(inspection, InspectionDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<InspectionDto> findByInspectionDate(LocalDateTime date) {
        List<Inspection> inspections = inspectionRepository.findByInspectionDate(date);
        return inspections.stream()
                .map(inspection -> modelMapper.map(inspection, InspectionDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public InspectionDto createInspection(InspectionDto inspectionDto) {
        Inspection inspectionEntity = modelMapper.map(inspectionDto, Inspection.class);
        Inspection savedInspection = inspectionRepository.save(inspectionEntity);
        return modelMapper.map(savedInspection, InspectionDto.class);
    }

    @Override
    public InspectionDto updateInspection(Long id, InspectionDto inspectionDto) {
        Optional<Inspection> existingInspectionOpt = inspectionRepository.findById(id);
        if (existingInspectionOpt.isPresent()) {
            Inspection existingInspection = existingInspectionOpt.get();
            existingInspection.setInspectorName(inspectionDto.getInspectorName());
            existingInspection.setInspectionDate(inspectionDto.getInspectionDate());
            existingInspection.setComments(inspectionDto.getComments());


            Inspection updatedInspection = inspectionRepository.save(existingInspection);
            return modelMapper.map(updatedInspection, InspectionDto.class);
        } else {
            throw new RuntimeException("Inspection not found with ID: " + id);
        }
    }

}

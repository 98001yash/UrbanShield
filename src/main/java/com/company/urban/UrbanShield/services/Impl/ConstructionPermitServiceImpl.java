package com.company.urban.UrbanShield.services.Impl;

import com.company.urban.UrbanShield.dto.ConstructionPermitDto;
import com.company.urban.UrbanShield.entities.ConstructionPermit;
import com.company.urban.UrbanShield.entities.ConstructionSite;
import com.company.urban.UrbanShield.repositories.ConstructionPermitRepository;
import com.company.urban.UrbanShield.repositories.ConstructionSiteRepository;
import com.company.urban.UrbanShield.services.ConstructionPermitService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConstructionPermitServiceImpl implements ConstructionPermitService {

    private final ConstructionPermitRepository constructionPermitRepository;
    private final ModelMapper modelMapper;
    private final ConstructionSiteRepository constructionSiteRepository;

    @Override
    public ConstructionPermitDto findByConstructionSite(Long constructionSiteId) {
        Optional<ConstructionPermit> permitOpt = constructionPermitRepository.findByConstructionSiteId(constructionSiteId);

        return permitOpt
                .map(permit -> modelMapper.map(permit, ConstructionPermitDto.class))
                .orElseThrow(() -> new RuntimeException("Construction Permit not found for construction site ID: " + constructionSiteId));
    }

    @Override
    public ConstructionPermitDto findByPermitNumber(String permitNumber) {
        Optional<ConstructionPermit> permitOpt = constructionPermitRepository.findByPermitNumber(permitNumber);

        return permitOpt
                .map(permit -> modelMapper.map(permit, ConstructionPermitDto.class))
                .orElseThrow(() -> new RuntimeException("Construction Permit not found with permit number: " + permitNumber));
    }

    @Override
    public List<ConstructionPermitDto> findByIssueDateBetween(LocalDateTime startDate, LocalDateTime endDate) {
        List<ConstructionPermit> permits = constructionPermitRepository.findByIssueDateBetween(startDate, endDate);

        return permits.stream()
                .map(permit -> modelMapper.map(permit, ConstructionPermitDto.class))
                .collect(Collectors.toList());
    }



    @Override
    public ConstructionPermitDto createPermit(ConstructionPermitDto permitDto) {
        // Convert DTO to entity
        ConstructionPermit permitEntity = modelMapper.map(permitDto, ConstructionPermit.class);

        // Save the entity to the database
        ConstructionPermit savedPermit = constructionPermitRepository.save(permitEntity);

        // Convert the saved entity back to DTO
        return modelMapper.map(savedPermit, ConstructionPermitDto.class);
    }



    @Override
    public ConstructionPermitDto updatePermit(Long id, ConstructionPermitDto permitDto) {
        Optional<ConstructionPermit> optionalPermit = constructionPermitRepository.findById(id);

        if (optionalPermit.isPresent()) {
            ConstructionPermit existingPermit = optionalPermit.get();

            existingPermit.setPermitNumber(permitDto.getPermitNumber());
            existingPermit.setIssueDate(permitDto.getIssueDate());
            existingPermit.setExpirationDate(permitDto.getExpirationDate());

            if (permitDto.getConstructionSiteId() != null) {
                ConstructionSite constructionSite = constructionSiteRepository.findById(permitDto.getConstructionSiteId())
                        .orElseThrow(() -> new RuntimeException("Construction site not found with ID: " + permitDto.getConstructionSiteId()));
                existingPermit.setConstructionSite(constructionSite);
            }
            ConstructionPermit updatedPermit = constructionPermitRepository.save(existingPermit);

            return modelMapper.map(updatedPermit, ConstructionPermitDto.class);
        } else {
            throw new RuntimeException("Permit not found with ID: " + id);
        }
    }


}
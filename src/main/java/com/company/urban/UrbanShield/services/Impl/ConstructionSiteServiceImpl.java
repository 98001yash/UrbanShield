package com.company.urban.UrbanShield.services.Impl;

import com.company.urban.UrbanShield.dto.ConstructionSiteDto;
import com.company.urban.UrbanShield.entities.ConstructionSite;
import com.company.urban.UrbanShield.exceptions.ResourceNotFoundException;
import com.company.urban.UrbanShield.repositories.ConstructionSiteRepository;
import com.company.urban.UrbanShield.services.ConstructionSiteService;
import com.company.urban.UrbanShield.utils.GeometryUtils;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConstructionSiteServiceImpl implements ConstructionSiteService {

    private final ConstructionSiteRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public ConstructionSiteDto findByLocation(double[] coordinates) {
        Point location = GeometryUtils.createPoint(coordinates);
        Optional<ConstructionSite> site = repository.findByLocation(location);
        return site.map(this::mapToDto)
                .orElseThrow(() -> new ResourceNotFoundException("Construction site not found at the provided location."));
    }

    @Override
    public List<ConstructionSiteDto> findByOwnerName(String ownerName) {

        return List.of();
    }

    @Override
    public List<ConstructionSiteDto> findByStartDateAfter(LocalDateTime startDate) {

        return List.of();
    }

    @Override
    public ConstructionSiteDto createConstructionSite(ConstructionSiteDto constructionSiteDto) {
        ConstructionSite constructionSite = mapToEntity(constructionSiteDto);
        ConstructionSite savedSite = repository.save(constructionSite);
        return mapToDto(savedSite);
    }

    @Override
    public ConstructionSiteDto updateConstructionSite(Long id, ConstructionSiteDto constructionSiteDto) {
        ConstructionSite existingSite = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Construction site not found with id " + id));

        existingSite.setName(constructionSiteDto.getName());
        existingSite.setAddress(constructionSiteDto.getAddress());
        existingSite.setOwnerName(constructionSiteDto.getOwnerName());
        existingSite.setLocation(GeometryUtils.createPoint(constructionSiteDto.getCoordinates()));
        existingSite.setStartDate(constructionSiteDto.getStartDate());
        existingSite.setEndDate(constructionSiteDto.getEndDate());

        ConstructionSite updatedSite = repository.save(existingSite);
        return mapToDto(updatedSite);
    }

    @Override
    public void deleteConstructionSite(Long id) {
        ConstructionSite site = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Construction site not found with id " + id));
        repository.delete(site);
    }

    @Override
    public ConstructionSiteDto findById(Long id) {
        ConstructionSite site = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Construction site not found with id " + id));
        return mapToDto(site);
    }

    private ConstructionSiteDto mapToDto(ConstructionSite constructionSite) {
        return modelMapper.map(constructionSite, ConstructionSiteDto.class);
    }

    private ConstructionSite mapToEntity(ConstructionSiteDto constructionSiteDto) {
        return modelMapper.map(constructionSiteDto, ConstructionSite.class);
    }
}

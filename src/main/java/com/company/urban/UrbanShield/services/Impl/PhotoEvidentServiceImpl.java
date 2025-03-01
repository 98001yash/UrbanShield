package com.company.urban.UrbanShield.services.Impl;

import com.company.urban.UrbanShield.dto.PhotoEvidenceDto;
import com.company.urban.UrbanShield.entities.PhotoEvidence;
import com.company.urban.UrbanShield.repositories.PhotoEvidenceRepository;
import com.company.urban.UrbanShield.services.PhotoEvidentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PhotoEvidentServiceImpl implements PhotoEvidentService {

    private final PhotoEvidenceRepository photoEvidenceRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PhotoEvidenceDto> findByConstructionSite(Long constructionSiteId) {
        List<PhotoEvidence> photoEvidenceList = photoEvidenceRepository.findByConstructionSiteId(constructionSiteId);
        return photoEvidenceList.stream()
                .map(photoEvidence -> modelMapper.map(photoEvidence, PhotoEvidenceDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PhotoEvidenceDto> findByCaptureDate(LocalDateTime date) {
        List<PhotoEvidence> photoEvidenceList = photoEvidenceRepository.findByCaptureDate(date);
        return photoEvidenceList.stream()
                .map(photoEvidence -> modelMapper.map(photoEvidence, PhotoEvidenceDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PhotoEvidenceDto> findByPhotoUrlContaining(String keyword) {
        List<PhotoEvidence> photoEvidenceList = photoEvidenceRepository.findByPhotoUrlContaining(keyword);
        return photoEvidenceList.stream()
                .map(photoEvidence -> modelMapper.map(photoEvidence, PhotoEvidenceDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PhotoEvidenceDto savePhotoEvidence(PhotoEvidenceDto photoEvidenceDto) {
        PhotoEvidence photoEvidence = modelMapper.map(photoEvidenceDto, PhotoEvidence.class);
        PhotoEvidence savedPhotoEvidence = photoEvidenceRepository.save(photoEvidence);
        return modelMapper.map(savedPhotoEvidence, PhotoEvidenceDto.class);
    }
}

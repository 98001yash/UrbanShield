package com.company.urban.UrbanShield.controller;

import com.company.urban.UrbanShield.dto.PhotoEvidenceDto;
import com.company.urban.UrbanShield.services.PhotoEvidentService;
import com.company.urban.UrbanShield.advices.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/photo-evidences")
@RequiredArgsConstructor
public class PhotoEvidenceController {

    private final PhotoEvidentService photoEvidentService;

    // Existing GET methods
    @GetMapping("/construction-site/{constructionSiteId}")
    public ResponseEntity<List<PhotoEvidenceDto>> getByConstructionSite(@PathVariable Long constructionSiteId) {
        List<PhotoEvidenceDto> photoEvidenceList = photoEvidentService.findByConstructionSite(constructionSiteId);
        return new ResponseEntity<>(photoEvidenceList, HttpStatus.OK);
    }

    @GetMapping("/capture-date")
    public ResponseEntity<List<PhotoEvidenceDto>> getByCaptureDate(@RequestParam("date") String date) {
        LocalDateTime captureDate = LocalDateTime.parse(date); // Make sure the date format is correct
        List<PhotoEvidenceDto> photoEvidenceList = photoEvidentService.findByCaptureDate(captureDate);
        return new ResponseEntity<>(photoEvidenceList, HttpStatus.OK);
    }

    @GetMapping("/photo-url")
    public ResponseEntity<List<PhotoEvidenceDto>> getByPhotoUrlContaining(@RequestParam("keyword") String keyword) {
        List<PhotoEvidenceDto> photoEvidenceList = photoEvidentService.findByPhotoUrlContaining(keyword);
        return new ResponseEntity<>(photoEvidenceList, HttpStatus.OK);
    }

    // File upload method
    @PostMapping("/upload")
    public ResponseEntity<ApiResponse<String>> uploadPhotoEvidence(
            @RequestParam("file") MultipartFile file,
            @RequestParam("constructionSiteId") Long constructionSiteId,
            @RequestParam("captureDate") String captureDateStr) {
        try {
            LocalDateTime captureDate = LocalDateTime.parse(captureDateStr);
            String uploadedPhotoUrl = saveFile(file); // Call to saveFile method

            // Prepare DTO for saving in DB
            PhotoEvidenceDto photoEvidenceDto = new PhotoEvidenceDto();
            photoEvidenceDto.setPhotoUrl(uploadedPhotoUrl);
            photoEvidenceDto.setConstructionSiteId(constructionSiteId);
            photoEvidenceDto.setCaptureDate(captureDate);

            photoEvidentService.savePhotoEvidence(photoEvidenceDto);
            ApiResponse<String> response = new ApiResponse<>("Photo uploaded successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            ApiResponse<String> errorResponse = new ApiResponse<>("Error: " + e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            ApiResponse<String> errorResponse = new ApiResponse<>("File error: " + e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            ApiResponse<String> errorResponse = new ApiResponse<>("An unexpected error occurred: " + e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String saveFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }


        String directoryPath = "E:/UrbanShield/uploaded_photos/";
        File directory = new File(directoryPath);

        // Create the directory if it doesn't exist
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (created) {
                System.out.println("Directory created successfully: " + directoryPath);
            } else {
                throw new IOException("Failed to create directory: " + directoryPath);
            }
        }

        // Get the original filename and create a new file path
        String originalFilename = file.getOriginalFilename();
        String uniqueFileName = System.currentTimeMillis() + "_" + originalFilename; // Append timestamp
        String filePath = directoryPath + uniqueFileName;

        // Save the file to the server
        File serverFile = new File(filePath);
        try {
            file.transferTo(serverFile); // This will save the file
        } catch (IOException e) {
            e.printStackTrace(); // Log the exception
            throw new IOException("Error saving file: " + e.getMessage());
        }

        // Return the relative URL for the saved file
        return "/uploaded_photos/" + uniqueFileName; // Return the path to be stored in the database
    }
}
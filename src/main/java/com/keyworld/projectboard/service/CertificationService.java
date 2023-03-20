package com.keyworld.projectboard.service;

import com.keyworld.projectboard.domain.Certification;
import com.keyworld.projectboard.domain.Company;
import com.keyworld.projectboard.domain.Notice;
import com.keyworld.projectboard.dto.CertificationDTO;
import com.keyworld.projectboard.dto.NoticeDTO;
import com.keyworld.projectboard.repository.CertificationRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Getter
public class CertificationService {

    private final FileService fileService;
    @Autowired
    private CertificationRepository repository;

    public CertificationService(FileService fileService) {
        this.fileService = fileService;
    }


    public List<Certification> getAll() {
        return repository.findAll();
    }

    public List<Certification> findByLanguage(Boolean language){
        return repository.findByLanguage(language);
    }

    public CertificationDTO getById(Long id) throws IOException {
        // Retrieve the certification from the database
        Certification certification = repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException());

        CertificationDTO certificationDTO = new CertificationDTO();
        certificationDTO.setTitle(certification.getTitle());
        certificationDTO.setLanguage(certification.getLanguage());

        if (certification.getFilePath() != null) {
            Resource fileResource = fileService.downloadFile(certification.getFilePath());
            certificationDTO.setFile((MultipartFile) fileResource.getFile());
        }

        return certificationDTO;
    }

    public void save(CertificationDTO dto) throws IOException {
        Certification certification = new Certification();
        certification.setTitle(dto.getTitle());
        certification.setLanguage(dto.getLanguage());
        if (dto.getFile() != null) {
            String fileName = dto.getFile().getOriginalFilename();
            String filePath = "/certifications/" + fileName;
            certification.setFilePath(filePath);
            fileService.uploadFile(dto.getFile(), filePath);
        }
    }

    public void update(Long id, CertificationDTO certificationDTO) throws IOException {
        Certification certification = repository.findById(id).orElse(null);

        if (certification == null) {
            return;
        }

        certification.setTitle(certificationDTO.getTitle());
        certification.setLanguage(certificationDTO.getLanguage());

        if (certificationDTO.getFile() != null) {
            String fileName = certificationDTO.getFile().getOriginalFilename();
            String filePath = "/certifications/" + fileName;
            certification.setFilePath(filePath);
            fileService.uploadFile(certificationDTO.getFile(), filePath);
        }

        repository.save(certification);
    }
    public void delete(Long id) {
        repository.deleteById(id);
    }
}

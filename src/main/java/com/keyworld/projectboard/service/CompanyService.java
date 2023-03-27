package com.keyworld.projectboard.service;

import com.keyworld.projectboard.domain.Company;
import com.keyworld.projectboard.domain.Company;
import com.keyworld.projectboard.domain.Notice;
import com.keyworld.projectboard.dto.CompanyDTO;
import com.keyworld.projectboard.dto.NoticeDTO;
import com.keyworld.projectboard.repository.CompanyRepository;
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
public class CompanyService {

    private final FileService fileService;
    @Autowired
    private CompanyRepository repository;

    public CompanyService(FileService fileService) {
        this.fileService = fileService;
    }


    public List<Company> getAll() {
        return repository.findAll();
    }

    public List<Company> findByLanguage(Boolean language){
        return repository.findByLanguage(language);
    }

    public CompanyDTO getById(Long id) throws IOException {
        // Retrieve the company from the database
        Company company = repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException());

        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setTitle(company.getTitle());
        companyDTO.setLanguage(company.getLanguage());

        if (company.getFilePath() != null) {
            Resource fileResource = fileService.downloadFile(company.getFilePath());
            companyDTO.setFile((MultipartFile) fileResource.getFile());
        }

        return companyDTO;
    }

    public void save(CompanyDTO dto) throws IOException {
        Company company = new Company();
        company.setTitle(dto.getTitle());
        company.setLanguage(dto.getLanguage());
        if (dto.getFile() != null) {
            String fileName = dto.getFile().getOriginalFilename();
            String filePath = "/inspect/" + fileName;
            company.setFilePath(filePath);
            fileService.uploadFile(dto.getFile(), filePath);
        }
    }

    public void update(Long id, CompanyDTO companyDTO) throws IOException {
        Company company = repository.findById(id).orElse(null);

        if (company == null) {
            return;
        }

        company.setTitle(companyDTO.getTitle());
        company.setLanguage(companyDTO.getLanguage());

        if (companyDTO.getFile() != null) {
            String fileName = companyDTO.getFile().getOriginalFilename();
            String filePath = "/companys/" + fileName;
            company.setFilePath(filePath);
            fileService.uploadFile(companyDTO.getFile(), filePath);
        }

        repository.save(company);
    }
    public void delete(Long id) {
        repository.deleteById(id);
    }
}

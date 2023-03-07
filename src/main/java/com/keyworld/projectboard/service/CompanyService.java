package com.keyworld.projectboard.service;

import com.keyworld.projectboard.domain.Company;
import com.keyworld.projectboard.dto.CompanyDTO;
import com.keyworld.projectboard.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository repository;

    public List<Company> getAll() {
        return repository.findAll();
    }

    public byte[] getByIdAndFileIndex(Long id) {
        Company entity = getById(id);
        return entity.getFile();
    }

    public Company getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException());
    }

    public Company save(CompanyDTO dto) throws IOException {
        String title = dto.getTitle();
        Company entity = new Company(title, dto.getFile().getBytes());
        return repository.save(entity);
    }

    public Company update(Long id, CompanyDTO dto) throws IOException {
        Company entity = getById(id);
        String title = dto.getTitle();
        entity.setTitle(title);
        entity.setFile(dto.getFile().getBytes());
        return repository.save(entity);
    }

    public void delete(Long id) {
        Company entity = getById(id);
        repository.delete(entity);
    }
}

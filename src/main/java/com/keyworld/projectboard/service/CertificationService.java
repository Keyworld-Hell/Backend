package com.keyworld.projectboard.service;

import com.keyworld.projectboard.domain.Certification;
import com.keyworld.projectboard.dto.CertificationDTO;
import com.keyworld.projectboard.repository.CertificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CertificationService {
    @Autowired
    private CertificationRepository repository;

    public List<Certification> getAll() {
        return repository.findAll();
    }

    public byte[] getByIdFile(Long id) {
        Certification entity = getById(id);
        return entity.getFile();
    }

    public Certification getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException());
    }

    public Certification save(CertificationDTO dto) throws IOException {
        String title = dto.getTitle();
        Certification entity = new Certification(title, dto.getFile().getBytes());
        return repository.save(entity);
    }

    public Certification update(Long id, CertificationDTO dto) throws IOException {
        Certification entity = getById(id);
        String title = dto.getTitle();
        entity.setTitle(title);
        entity.setFile(dto.getFile().getBytes());
        return repository.save(entity);
    }

    public void delete(Long id) {
        Certification entity = getById(id);
        repository.delete(entity);
    }
}

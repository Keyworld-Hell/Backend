package com.keyworld.projectboard.service;

import java.io.IOException;
import java.util.List;

import com.keyworld.projectboard.domain.CustomLockEng;
import com.keyworld.projectboard.dto.CustomLockEngDTO;
import com.keyworld.projectboard.repository.CustomLockEngRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomLockEngService {

    @Autowired
    private CustomLockEngRepository repository;

    public List<CustomLockEng> getAll() {
        return repository.findAll();
    }

    public List<CustomLockEng> findCustomLockEngsByTitle(String title) {
        return repository.findByTitleContainingIgnoreCase(title);
    }
    public byte[] getByIdAndFileIndex(Long id, int fileIndex) {
        CustomLockEng entity = getById(id);
        switch (fileIndex) {
            case 1:
                return entity.getCam();
            case 2:
                return entity.getDwg();
            case 3:
                return entity.getDwgDown();
            case 4:
                return entity.getStepDown();
            case 5:
                return entity.getPdfDown();
            default:
                throw new IllegalArgumentException("Invalid file index");
        }
    }

    public CustomLockEng getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException());
    }


    public CustomLockEng save(CustomLockEngDTO dto) throws IOException {
        CustomLockEng entity = new CustomLockEng();
        entity.setTitle(dto.getTitle());
        entity.setMaterial(dto.getMaterial());
        entity.setSurface(dto.getSurface());
        entity.setPurpose(dto.getPurpose());
        entity.setFeature(dto.getFeature());
        if (dto.getCam() != null) {
            entity.setCam(dto.getCam().getBytes());
        } else {
            entity.setCam(null);
        }
        if (dto.getDwg() != null) {
            entity.setDwg(dto.getDwg().getBytes());
        } else {
            entity.setDwg(null);
        }
        if (dto.getDwgDown() != null) {
            entity.setDwgDown(dto.getDwgDown().getBytes());
        } else {
            entity.setDwgDown(null);
        }
        if (dto.getStepDown() != null) {
            entity.setStepDown(dto.getStepDown().getBytes());
        } else {
            entity.setStepDown(null);
        }
        if (dto.getPdfDown() != null) {
            entity.setPdfDown(dto.getPdfDown().getBytes());
        } else {
            entity.setPdfDown(null);
        }
        return repository.save(entity);
    }


    public CustomLockEng update(Long id, CustomLockEngDTO dto) throws IOException {
        CustomLockEng entity = getById(id);
        entity.setTitle(dto.getTitle());
        entity.setMaterial(dto.getMaterial());
        entity.setSurface(dto.getSurface());
        entity.setPurpose(dto.getPurpose());
        entity.setFeature(dto.getFeature());
        if (dto.getCam() != null) {
            entity.setCam(dto.getCam().getBytes());
        } else {
            entity.setCam(null);
        }
        if (dto.getDwg() != null) {
            entity.setDwg(dto.getDwg().getBytes());
        } else {
            entity.setDwg(null);
        }
        if (dto.getDwgDown() != null) {
            entity.setDwgDown(dto.getDwgDown().getBytes());
        } else {
            entity.setDwgDown(null);
        }
        if (dto.getStepDown() != null) {
            entity.setStepDown(dto.getStepDown().getBytes());
        } else {
            entity.setStepDown(null);
        }
        if (dto.getPdfDown() != null) {
            entity.setPdfDown(dto.getPdfDown().getBytes());
        } else {
            entity.setPdfDown(null);
        }
        return repository.save(entity);
    }


    public void delete(Long id) {
        CustomLockEng entity = getById(id);
        repository.delete(entity);
    }

}
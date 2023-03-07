package com.keyworld.projectboard.service;

import java.io.IOException;
import java.util.List;

import com.keyworld.projectboard.domain.Special;
import com.keyworld.projectboard.dto.SpecialDTO;
import com.keyworld.projectboard.repository.SpecialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class SpecialService {

    @Autowired
    private SpecialRepository repository;

    public List<Special> getAll() {
        return repository.findAll();
    }

    public List<Special> findSpecialsByTitle(String title) {
        return repository.findByTitleContainingIgnoreCase(title);
    }
    public byte[] getByIdAndFileIndex(Long id, int fileIndex) {
        Special entity = getById(id);
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

    public Special getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException());
    }


    public Special save(SpecialDTO dto) throws IOException {
        Special entity = new Special();
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


    public Special update(Long id, SpecialDTO dto) throws IOException {
        Special entity = getById(id);
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
        Special entity = getById(id);
        repository.delete(entity);
    }

}
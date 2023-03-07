package com.keyworld.projectboard.service;

import java.io.IOException;
import java.util.List;

import com.keyworld.projectboard.domain.Lock;
import com.keyworld.projectboard.dto.LockDTO;
import com.keyworld.projectboard.repository.LockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class LockService {

    @Autowired
    private LockRepository repository;

    public List<Lock> getAll() {
        return repository.findAll();
    }

    public List<Lock> findLocksByTitle(String title) {
        return repository.findByTitleContainingIgnoreCase(title);
    }


    public Lock getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException());
    }


    public Lock save(LockDTO dto) throws IOException {
        Lock entity = new Lock();
        entity.setTitle(dto.getTitle());
        entity.setMaterial(dto.getMaterial());
        entity.setSurface(dto.getSurface());
        entity.setPurpose(dto.getPurpose());
        entity.setFeature(dto.getFeature());
        return repository.save(entity);
    }


    public Lock update(Long id, LockDTO dto) throws IOException {
        Lock entity = getById(id);
        entity.setLanguage(dto.getLanguage());
        entity.setNumber(dto.getNumber());
        entity.setTitle(dto.getTitle());
        entity.setMaterial(dto.getMaterial());
        entity.setSurface(dto.getSurface());
        entity.setPurpose(dto.getPurpose());
        entity.setFeature(dto.getFeature());
        return repository.save(entity);
    }


    public void delete(Long id) {
        Lock entity = getById(id);
        repository.delete(entity);
    }

}
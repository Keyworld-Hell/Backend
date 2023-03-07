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

    public List<Lock> getAll(Boolean language, Long number) {
        return repository.findByLanguageAndNumber(language, number);
    }

    public List<Lock> findLocksByTitle(Boolean language, Long number, String title) {
        return repository.findByLanguageAndNumberAndTitleContainingIgnoreCase(language, number, title);
    }


    public Lock getByLanguageAndNumberAndId(Boolean language, Long number, Long id) {
        return repository.findByLanguageAndNumberAndId(language, number, id)
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


    public Lock update(Boolean language, Long number, Long id, LockDTO dto) throws IOException {
        Lock entity = getByLanguageAndNumberAndId(language, number, id);
        entity.setLanguage(dto.getLanguage());
        entity.setNumber(dto.getNumber());
        entity.setTitle(dto.getTitle());
        entity.setMaterial(dto.getMaterial());
        entity.setSurface(dto.getSurface());
        entity.setPurpose(dto.getPurpose());
        entity.setFeature(dto.getFeature());
        return repository.save(entity);
    }


    public void delete(Boolean language, Long number, Long id) {
        Lock entity = getByLanguageAndNumberAndId(language, number, id);
        repository.delete(entity);
    }

}
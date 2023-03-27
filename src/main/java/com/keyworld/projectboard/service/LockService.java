package com.keyworld.projectboard.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.keyworld.projectboard.domain.Locks;
import com.keyworld.projectboard.dto.LockDTO;
import com.keyworld.projectboard.repository.LockRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
@Getter
public class LockService {

    @Autowired
    private LockRepository repository;

    private final FileService fileService;

    public LockService(FileService fileService) {
        this.fileService = fileService;
    }

    public List<Locks> getAll(Boolean language, Long number) {
        return repository.findByLanguageAndNumber(language, number);
    }

    public List<Locks> findLocksByTitle(Boolean language, Long number, String title) {
        return repository.findByLanguageAndNumberAndTitleContainingIgnoreCase(language, number, title);
    }


    public Locks getByLanguageAndNumberAndId(Boolean language, Long number, Long id) {
        return repository.findByLanguageAndNumberAndId(language, number, id);
    }


    public Locks save(LockDTO dto) throws IOException {
        Locks entity = new Locks();
        entity.setTitle(dto.getTitle());
        entity.setMaterial(dto.getMaterial());
        entity.setNumber(dto.getNumber());
        entity.setSurface(dto.getSurface());
        entity.setLanguage(dto.getLanguage());
        entity.setPurpose(dto.getPurpose());
        entity.setDescription(dto.getDescription());
        entity.setFeature(dto.getFeature());
        String fileName = dto.getFile1().getOriginalFilename();
        String filePath = "/locks/" + fileName;
        entity.setPath1(filePath);
        fileService.uploadFile(dto.getFile1(), filePath);
        fileName = dto.getFile2().getOriginalFilename();
        filePath = "/locks/" + fileName;
        entity.setPath2(filePath);
        fileService.uploadFile(dto.getFile2(), filePath);
        List<String> filePathList = new ArrayList<>();
        if (dto.getFiles() != null) {
            for (MultipartFile file : dto.getFiles()) {
                fileName = file.getOriginalFilename();
                filePath = "/locks/" + fileName;
                filePathList.add(filePath);
                fileService.uploadFile(file, filePath);
            }
        }
        entity.setFilePathList(filePathList);
        return repository.save(entity);
    }


    public Locks update(Boolean language, Long number, Long id, LockDTO dto) throws IOException {
        Locks entity = getByLanguageAndNumberAndId(language, number, id);
        entity.setTitle(dto.getTitle());
        entity.setMaterial(dto.getMaterial());
        entity.setNumber(dto.getNumber());
        entity.setSurface(dto.getSurface());
        entity.setLanguage(dto.getLanguage());
        entity.setPurpose(dto.getPurpose());
        entity.setDescription(dto.getDescription());
        entity.setFeature(dto.getFeature());
        String fileName, filePath;
        if(dto.getFile1()!=null) {
            fileName = dto.getFile1().getOriginalFilename();
            filePath = "/locks/" + fileName;
            entity.setPath1(filePath);

            fileService.uploadFile(dto.getFile1(), filePath);
        }
        if(dto.getFile2()!=null) {
            fileName = dto.getFile2().getOriginalFilename();
            filePath = "/locks/" + fileName;
            entity.setPath2(filePath);

            fileService.uploadFile(dto.getFile2(), filePath);
        }
        List<String> filePathList = new ArrayList<>();
        if (dto.getFiles() != null) {
            for (MultipartFile file : dto.getFiles()) {
                fileName = file.getOriginalFilename();
                filePath = "/locks/" + fileName;
                filePathList.add(filePath);
                fileService.uploadFile(file, filePath);
            }
        }
        entity.setFilePathList(filePathList);
        return repository.save(entity);
    }


    public void delete(Boolean language, Long number, Long id) {
        Locks entity = getByLanguageAndNumberAndId(language, number, id);
        repository.delete(entity);
    }

}
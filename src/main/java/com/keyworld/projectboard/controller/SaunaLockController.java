package com.keyworld.projectboard.controller;

import com.keyworld.projectboard.domain.SaunaLock;
import com.keyworld.projectboard.dto.SaunaLockDTO;
import com.keyworld.projectboard.repository.SaunaLockRepository;
import com.keyworld.projectboard.service.SaunaLockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/")
public class SaunaLockController {
    @Autowired
    private SaunaLockService service;
    @GetMapping("/products/saunalock")
    public List<SaunaLock> getAll() {
        return service.getAll();
    }

    @GetMapping("/products/saunalock/{id}")
    public SaunaLock getById(@PathVariable Long id) {
        return service.getById(id);
    }
    @GetMapping("/products/saunalock/search/{title}")
    public List<SaunaLock> getSaunaLocksByTitle(@RequestParam String title) {
        return service.findSaunaLocksByTitle(title);
    }

    @GetMapping("/admin/products/saunalock/search/{title}")
    public List<SaunaLock> getSaunaLocksByTitleAdmin(@PathVariable String title) {
        return service.findSaunaLocksByTitle(title);
    }
    @GetMapping("/products/saunalock/image/{id}/{fileIndex}")
    public ResponseEntity<Resource> getImage(@PathVariable Long id, @PathVariable int fileIndex) {
        byte[] imageData = service.getByIdAndFileIndex(id, fileIndex);
        ByteArrayResource resource = new ByteArrayResource(imageData);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_JPEG_VALUE);

        return ResponseEntity.ok()
                .contentLength(imageData.length)
                .headers(headers)
                .body(resource);
    }

    @GetMapping("/products/saunalock/download/{id}/{fileIndex}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id, @PathVariable int fileIndex) throws IOException {
        byte[] fileContent = service.getByIdAndFileIndex(id, fileIndex);
        ByteArrayResource resource = new ByteArrayResource(fileContent);

        SaunaLock saunaLock = service.getById(id);
        String filename = saunaLock.getTitle(); // modify to match your use case
        if (fileIndex == 1 || fileIndex == 2){
            filename = filename + ".jpg";
        }
        else if(fileIndex == 3){
            filename = filename + ".dwg";
        }
        else if(fileIndex == 4){
            filename = filename + ".step";
        }
        else if (fileIndex == 5){
            filename = filename + ".pdf";
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"");

        return ResponseEntity.ok()
                .contentLength(fileContent.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .headers(headers)
                .body(resource);
    }

    @PostMapping("/admin/products/saunalock/upload")
    public ResponseEntity<String> uploadFile(@ModelAttribute SaunaLockDTO saunaLockDTO) throws IOException {
        service.save(saunaLockDTO);
        return ResponseEntity.ok("File uploaded successfully");
    }

    @PutMapping("/admin/products/saunalock/update/{id}")
    public SaunaLock update(@PathVariable Long id, @ModelAttribute SaunaLockDTO dto) throws IOException {
        return service.update(id, dto);
    }

    @DeleteMapping("/admin/products/saunalock/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
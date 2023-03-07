package com.keyworld.projectboard.controller;

import com.keyworld.projectboard.domain.DigitalLock;
import com.keyworld.projectboard.dto.DigitalLockDTO;
import com.keyworld.projectboard.repository.DigitalLockRepository;
import com.keyworld.projectboard.service.DigitalLockService;
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
public class DigitalLockController {
    @Autowired
    private DigitalLockService service;
    @GetMapping("/products/digitallock")
    public List<DigitalLock> getAll() {
        return service.getAll();
    }

    @GetMapping("/products/digitallock/{id}")
    public DigitalLock getById(@PathVariable Long id) {
        return service.getById(id);
    }
    @GetMapping("/products/digitallock/search/{title}")
    public List<DigitalLock> getDigitalLocksByTitle(@RequestParam String title) {
        return service.findDigitalLocksByTitle(title);
    }

    @GetMapping("/admin/products/digitallock/search/{title}")
    public List<DigitalLock> getDigitalLocksByTitleAdmin(@PathVariable String title) {
        return service.findDigitalLocksByTitle(title);
    }

    @GetMapping("/products/digitallock/image/{id}/{fileIndex}")
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

    @GetMapping("/products/digitallock/download/{id}/{fileIndex}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id, @PathVariable int fileIndex) throws IOException {
        byte[] fileContent = service.getByIdAndFileIndex(id, fileIndex);
        ByteArrayResource resource = new ByteArrayResource(fileContent);

        DigitalLock digitalLock = service.getById(id);
        String filename = digitalLock.getTitle(); // modify to match your use case
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

    @PostMapping("/admin/products/digitallock/upload")
    public ResponseEntity<String> uploadFile(@ModelAttribute DigitalLockDTO digitalLockDTO) throws IOException {
        service.save(digitalLockDTO);
        return ResponseEntity.ok("File uploaded successfully");
    }

    @PutMapping("/admin/products/digitallock/update/{id}")
    public DigitalLock update(@PathVariable Long id, @ModelAttribute DigitalLockDTO dto) throws IOException {
        return service.update(id, dto);
    }

    @DeleteMapping("/admin/products/digitallock/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
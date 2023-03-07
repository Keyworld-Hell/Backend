package com.keyworld.projectboard.controller;

import com.keyworld.projectboard.domain.CustomLock;
import com.keyworld.projectboard.dto.CustomLockDTO;
import com.keyworld.projectboard.repository.CustomLockRepository;
import com.keyworld.projectboard.service.CustomLockService;
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
public class CustomLockController {
    @Autowired
    private CustomLockService service;
    @GetMapping("/products/customlock")
    public List<CustomLock> getAll() {
        return service.getAll();
    }

    @GetMapping("/products/customlock/{id}")
    public CustomLock getById(@PathVariable Long id) {
        return service.getById(id);
    }
    @GetMapping("/products/customlock/search/{title}")
    public List<CustomLock> getCustomLocksByTitle(@RequestParam String title) {
        return service.findCustomLocksByTitle(title);
    }

    @GetMapping("/admin/products/customlock/search/{title}")
    public List<CustomLock> getCustomLocksByTitleAdmin(@PathVariable String title) {
        return service.findCustomLocksByTitle(title);
    }

    @GetMapping("/products/customlock/image/{id}/{fileIndex}")
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

    @GetMapping("/products/customlock/download/{id}/{fileIndex}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id, @PathVariable int fileIndex) throws IOException {
        byte[] fileContent = service.getByIdAndFileIndex(id, fileIndex);
        ByteArrayResource resource = new ByteArrayResource(fileContent);

        CustomLock customLock = service.getById(id);
        String filename = customLock.getTitle(); // modify to match your use case
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

    @PostMapping("/admin/products/customlock/upload")
    public ResponseEntity<String> uploadFile(@ModelAttribute CustomLockDTO customLockDTO) throws IOException {
        service.save(customLockDTO);
        return ResponseEntity.ok("File uploaded successfully");
    }

    @PutMapping("/admin/products/customlock/update/{id}")
    public CustomLock update(@PathVariable Long id, @ModelAttribute CustomLockDTO dto) throws IOException {
        return service.update(id, dto);
    }

    @DeleteMapping("/admin/products/customlock/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
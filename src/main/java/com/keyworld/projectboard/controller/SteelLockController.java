package com.keyworld.projectboard.controller;

import com.keyworld.projectboard.domain.SteelLock;
import com.keyworld.projectboard.dto.SteelLockDTO;
import com.keyworld.projectboard.repository.SteelLockRepository;
import com.keyworld.projectboard.service.SteelLockService;
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
public class SteelLockController {
    @Autowired
    private SteelLockService service;
    @GetMapping("/products/steellock")
    public List<SteelLock> getAll() {
        return service.getAll();
    }

    @GetMapping("/products/steellock/{id}")
    public SteelLock getById(@PathVariable Long id) {
        return service.getById(id);
    }
    @GetMapping("/products/steellock/search/{title}")
    public List<SteelLock> getSteelLocksByTitle(@RequestParam String title) {
        return service.findSteelLocksByTitle(title);
    }

    @GetMapping("/admin/products/steellock/search/{title}")
    public List<SteelLock> getSteelLocksByTitleAdmin(@PathVariable String title) {
        return service.findSteelLocksByTitle(title);
    }
    @GetMapping("/products/steellock/image/{id}/{fileIndex}")
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

    @GetMapping("/products/steellock/download/{id}/{fileIndex}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id, @PathVariable int fileIndex) throws IOException {
        byte[] fileContent = service.getByIdAndFileIndex(id, fileIndex);
        ByteArrayResource resource = new ByteArrayResource(fileContent);

        SteelLock steelLock = service.getById(id);
        String filename = steelLock.getTitle(); // modify to match your use case
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

    @PostMapping("/admin/products/steellock/upload")
    public ResponseEntity<String> uploadFile(@ModelAttribute SteelLockDTO steelLockDTO) throws IOException {
        service.save(steelLockDTO);
        return ResponseEntity.ok("File uploaded successfully");
    }

    @PutMapping("/admin/products/steellock/update/{id}")
    public SteelLock update(@PathVariable Long id, @ModelAttribute SteelLockDTO dto) throws IOException {
        return service.update(id, dto);
    }

    @DeleteMapping("/admin/products/steellock/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
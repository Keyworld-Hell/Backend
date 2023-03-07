package com.keyworld.projectboard.controller;

import com.keyworld.projectboard.domain.CircularLock;
import com.keyworld.projectboard.dto.CircularLockDTO;
import com.keyworld.projectboard.repository.CircularLockRepository;
import com.keyworld.projectboard.service.CircularLockService;
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
public class CircularLockController {
    @Autowired
    private CircularLockService service;
    @GetMapping("/products/circularlock")
    public List<CircularLock> getAll() {
        return service.getAll();
    }

    @GetMapping("/products/circularlock/{id}")
    public CircularLock getById(@PathVariable Long id) {
        return service.getById(id);
    }
    @GetMapping("/products/circularlock/search/{title}")
    public List<CircularLock> getCircularLocksByTitle(@RequestParam String title) {
        return service.findCircularLocksByTitle(title);
    }

    @GetMapping("/admin/products/circularlock/search/{title}")
    public List<CircularLock> getCircularLocksByTitleAdmin(@PathVariable String title) {
        return service.findCircularLocksByTitle(title);
    }
    @GetMapping("/products/circularlock/image/{id}/{fileIndex}")
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

    @GetMapping("/products/circularlock/download/{id}/{fileIndex}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id, @PathVariable int fileIndex) throws IOException {
        byte[] fileContent = service.getByIdAndFileIndex(id, fileIndex);
        ByteArrayResource resource = new ByteArrayResource(fileContent);

        CircularLock circularLock = service.getById(id);
        String filename = circularLock.getTitle(); // modify to match your use case
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

    @PostMapping("/admin/products/circularlock/upload")
    public ResponseEntity<String> uploadFile(@ModelAttribute CircularLockDTO circularLockDTO) throws IOException {
        service.save(circularLockDTO);
        return ResponseEntity.ok("File uploaded successfully");
    }

    @PutMapping("/admin/products/circularlock/update/{id}")
    public CircularLock update(@PathVariable Long id, @ModelAttribute CircularLockDTO dto) throws IOException {
        return service.update(id, dto);
    }

    @DeleteMapping("/admin/products/circularlock/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
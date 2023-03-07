package com.keyworld.projectboard.controller;

import com.keyworld.projectboard.domain.DiskLock;
import com.keyworld.projectboard.dto.DiskLockDTO;
import com.keyworld.projectboard.repository.DiskLockRepository;
import com.keyworld.projectboard.service.DiskLockService;
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
public class DiskLockController {
    @Autowired
    private DiskLockService service;
    @GetMapping("/products/disklock")
    public List<DiskLock> getAll() {
        return service.getAll();
    }

    @GetMapping("/products/disklock/{id}")
    public DiskLock getById(@PathVariable Long id) {
        return service.getById(id);
    }
    @GetMapping("/products/disklock/search/{title}")
    public List<DiskLock> getDiskLocksByTitle(@RequestParam String title) {
        return service.findDiskLocksByTitle(title);
    }

    @GetMapping("/admin/products/disklock/search/{title}")
    public List<DiskLock> getDiskLocksByTitleAdmin(@PathVariable String title) {
        return service.findDiskLocksByTitle(title);
    }
    @GetMapping("/products/disklock/image/{id}/{fileIndex}")
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

    @GetMapping("/products/disklock/download/{id}/{fileIndex}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id, @PathVariable int fileIndex) throws IOException {
        byte[] fileContent = service.getByIdAndFileIndex(id, fileIndex);
        ByteArrayResource resource = new ByteArrayResource(fileContent);

        DiskLock diskLock = service.getById(id);
        String filename = diskLock.getTitle(); // modify to match your use case
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

    @PostMapping("/admin/products/disklock/upload")
    public ResponseEntity<String> uploadFile(@ModelAttribute DiskLockDTO diskLockDTO) throws IOException {
        service.save(diskLockDTO);
        return ResponseEntity.ok("File uploaded successfully");
    }

    @PutMapping("/admin/products/disklock/update/{id}")
    public DiskLock update(@PathVariable Long id, @ModelAttribute DiskLockDTO dto) throws IOException {
        return service.update(id, dto);
    }

    @DeleteMapping("/admin/products/disklock/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
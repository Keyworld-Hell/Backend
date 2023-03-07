package com.keyworld.projectboard.controller;

import com.keyworld.projectboard.domain.DiskLockEng;
import com.keyworld.projectboard.dto.DiskLockEngDTO;
import com.keyworld.projectboard.service.DiskLockEngService;
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
public class DiskLockEngController {
    @Autowired
    private DiskLockEngService service;

    @GetMapping("/eng/products/disklock")
    public List<DiskLockEng> getAll() {
        return service.getAll();
    }

    @GetMapping("/eng/products/disklock/{id}")
    public DiskLockEng getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/eng/products/disklock/search/{title}")
    public List<DiskLockEng> getDiskLockEngsByTitle(@RequestParam String title) {
        return service.findDiskLockEngsByTitle(title);
    }

    @GetMapping("/eng/admin/products/disklock/search/{title}")
    public List<DiskLockEng> getDiskLockEngsByTitleAdmin(@PathVariable String title) {
        return service.findDiskLockEngsByTitle(title);
    }

    @GetMapping("/eng/products/disklock/image/{id}/{fileIndex}")
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

    @GetMapping("/eng/products/disklock/download/{id}/{fileIndex}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id, @PathVariable int fileIndex) throws IOException {
        byte[] fileContent = service.getByIdAndFileIndex(id, fileIndex);
        ByteArrayResource resource = new ByteArrayResource(fileContent);

        DiskLockEng diskLockEng = service.getById(id);
        String filename = diskLockEng.getTitle(); // modify to match your use case
        if (fileIndex == 1 || fileIndex == 2) {
            filename = filename + ".jpg";
        } else if (fileIndex == 3) {
            filename = filename + ".dwg";
        } else if (fileIndex == 4) {
            filename = filename + ".step";
        } else if (fileIndex == 5) {
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

    @PostMapping("/eng/admin/products/disklock/upload")
    public ResponseEntity<String> uploadFile(@ModelAttribute DiskLockEngDTO diskLockEngDTO) throws IOException {
        service.save(diskLockEngDTO);
        return ResponseEntity.ok("File uploaded successfully");
    }

    @PutMapping("/eng/admin/products/disklock/update/{id}")
    public DiskLockEng update(@PathVariable Long id, @ModelAttribute DiskLockEngDTO dto) throws IOException {
        return service.update(id, dto);
    }

    @DeleteMapping("/eng/admin/products/disklock/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}



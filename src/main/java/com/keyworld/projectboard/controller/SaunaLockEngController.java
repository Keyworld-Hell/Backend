package com.keyworld.projectboard.controller;

import com.keyworld.projectboard.domain.SaunaLockEng;
import com.keyworld.projectboard.dto.SaunaLockEngDTO;
import com.keyworld.projectboard.repository.SaunaLockEngRepository;
import com.keyworld.projectboard.service.SaunaLockEngService;
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
public class SaunaLockEngController {
    @Autowired
    private SaunaLockEngService service;

    @GetMapping("/eng/products/saunalock")
    public List<SaunaLockEng> getAll() {
        return service.getAll();
    }

    @GetMapping("/eng/products/saunalock/{id}")
    public SaunaLockEng getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/eng/products/saunalock/search/{title}")
    public List<SaunaLockEng> getSaunaLockEngsByTitle(@RequestParam String title) {
        return service.findSaunaLockEngsByTitle(title);
    }

    @GetMapping("/eng/admin/products/saunalock/search/{title}")
    public List<SaunaLockEng> getSaunaLockEngsByTitleAdmin(@PathVariable String title) {
        return service.findSaunaLockEngsByTitle(title);
    }

    @GetMapping("/eng/products/saunalock/image/{id}/{fileIndex}")
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

    @GetMapping("/eng/products/saunalock/download/{id}/{fileIndex}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id, @PathVariable int fileIndex) throws IOException {
        byte[] fileContent = service.getByIdAndFileIndex(id, fileIndex);
        ByteArrayResource resource = new ByteArrayResource(fileContent);

        SaunaLockEng saunalockEng = service.getById(id);
        String filename = saunalockEng.getTitle(); // modify to match your use case
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

    @PostMapping("/eng/admin/products/saunalock/upload")
    public ResponseEntity<String> uploadFile(@ModelAttribute SaunaLockEngDTO saunalockEngDTO) throws IOException {
        service.save(saunalockEngDTO);
        return ResponseEntity.ok("File uploaded successfully");
    }

    @PutMapping("/eng/admin/products/saunalock/update/{id}")
    public SaunaLockEng update(@PathVariable Long id, @ModelAttribute SaunaLockEngDTO dto) throws IOException {
        return service.update(id, dto);
    }

    @DeleteMapping("/eng/admin/products/saunalock/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}



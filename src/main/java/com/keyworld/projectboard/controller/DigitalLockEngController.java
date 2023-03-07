package com.keyworld.projectboard.controller;

import com.keyworld.projectboard.domain.DigitalLockEng;
import com.keyworld.projectboard.dto.DigitalLockEngDTO;
import com.keyworld.projectboard.repository.DigitalLockEngRepository;
import com.keyworld.projectboard.service.DigitalLockEngService;
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
public class DigitalLockEngController {
    @Autowired
    private DigitalLockEngService service;

    @GetMapping("/eng/products/digitallock")
    public List<DigitalLockEng> getAll() {
        return service.getAll();
    }

    @GetMapping("/eng/products/digitallock/{id}")
    public DigitalLockEng getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/eng/products/digitallock/search/{title}")
    public List<DigitalLockEng> getDigitalLockEngsByTitle(@RequestParam String title) {
        return service.findDigitalLockEngsByTitle(title);
    }

    @GetMapping("/eng/admin/products/digitallock/search/{title}")
    public List<DigitalLockEng> getDigitalLockEngsByTitleAdmin(@PathVariable String title) {
        return service.findDigitalLockEngsByTitle(title);
    }

    @GetMapping("/eng/products/digitallock/image/{id}/{fileIndex}")
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

    @GetMapping("/eng/products/digitallock/download/{id}/{fileIndex}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id, @PathVariable int fileIndex) throws IOException {
        byte[] fileContent = service.getByIdAndFileIndex(id, fileIndex);
        ByteArrayResource resource = new ByteArrayResource(fileContent);

        DigitalLockEng digitalLockEng = service.getById(id);
        String filename = digitalLockEng.getTitle(); // modify to match your use case
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

    @PostMapping("/eng/admin/products/digitallock/upload")
    public ResponseEntity<String> uploadFile(@ModelAttribute DigitalLockEngDTO digitalLockEngDTO) throws IOException {
        service.save(digitalLockEngDTO);
        return ResponseEntity.ok("File uploaded successfully");
    }

    @PutMapping("/eng/admin/products/digitallock/update/{id}")
    public DigitalLockEng update(@PathVariable Long id, @ModelAttribute DigitalLockEngDTO dto) throws IOException {
        return service.update(id, dto);
    }

    @DeleteMapping("/eng/admin/products/digitallock/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}



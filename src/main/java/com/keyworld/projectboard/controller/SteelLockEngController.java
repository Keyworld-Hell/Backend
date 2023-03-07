package com.keyworld.projectboard.controller;

import com.keyworld.projectboard.domain.SteelLockEng;
import com.keyworld.projectboard.dto.SteelLockEngDTO;
import com.keyworld.projectboard.repository.SteelLockEngRepository;
import com.keyworld.projectboard.service.SteelLockEngService;
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
public class SteelLockEngController {
    @Autowired
    private SteelLockEngService service;

    @GetMapping("/eng/products/steellock")
    public List<SteelLockEng> getAll() {
        return service.getAll();
    }

    @GetMapping("/eng/products/steellock/{id}")
    public SteelLockEng getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/eng/products/steellock/search/{title}")
    public List<SteelLockEng> getSteelLockEngsByTitle(@RequestParam String title) {
        return service.findSteelLockEngsByTitle(title);
    }

    @GetMapping("/eng/admin/products/steellock/search/{title}")
    public List<SteelLockEng> getSteelLockEngsByTitleAdmin(@PathVariable String title) {
        return service.findSteelLockEngsByTitle(title);
    }

    @GetMapping("/eng/products/steellock/image/{id}/{fileIndex}")
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

    @GetMapping("/eng/products/steellock/download/{id}/{fileIndex}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id, @PathVariable int fileIndex) throws IOException {
        byte[] fileContent = service.getByIdAndFileIndex(id, fileIndex);
        ByteArrayResource resource = new ByteArrayResource(fileContent);

        SteelLockEng steellockEng = service.getById(id);
        String filename = steellockEng.getTitle(); // modify to match your use case
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

    @PostMapping("/eng/admin/products/steellock/upload")
    public ResponseEntity<String> uploadFile(@ModelAttribute SteelLockEngDTO steellockEngDTO) throws IOException {
        service.save(steellockEngDTO);
        return ResponseEntity.ok("File uploaded successfully");
    }

    @PutMapping("/eng/admin/products/steellock/update/{id}")
    public SteelLockEng update(@PathVariable Long id, @ModelAttribute SteelLockEngDTO dto) throws IOException {
        return service.update(id, dto);
    }

    @DeleteMapping("/eng/admin/products/steellock/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}



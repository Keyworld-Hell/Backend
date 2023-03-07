package com.keyworld.projectboard.controller;

import com.keyworld.projectboard.domain.CircularLockEng;
import com.keyworld.projectboard.dto.CircularLockEngDTO;
import com.keyworld.projectboard.repository.CircularLockEngRepository;
import com.keyworld.projectboard.service.CircularLockEngService;
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
public class CircularLockEngController {
    @Autowired
    private CircularLockEngService service;

    @GetMapping("/eng/products/circularlock")
    public List<CircularLockEng> getAll() {
        return service.getAll();
    }

    @GetMapping("/eng/products/circularlock/{id}")
    public CircularLockEng getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/eng/products/circularlock/search/{title}")
    public List<CircularLockEng> getCircularLockEngsByTitle(@RequestParam String title) {
        return service.findCircularLockEngsByTitle(title);
    }

    @GetMapping("/eng/admin/products/circularlock/search/{title}")
    public List<CircularLockEng> getCircularLockEngsByTitleAdmin(@PathVariable String title) {
        return service.findCircularLockEngsByTitle(title);
    }

    @GetMapping("/eng/products/circularlock/image/{id}/{fileIndex}")
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

    @GetMapping("/eng/products/circularlock/download/{id}/{fileIndex}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id, @PathVariable int fileIndex) throws IOException {
        byte[] fileContent = service.getByIdAndFileIndex(id, fileIndex);
        ByteArrayResource resource = new ByteArrayResource(fileContent);

        CircularLockEng circularLockEng = service.getById(id);
        String filename = circularLockEng.getTitle(); // modify to match your use case
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

    @PostMapping("/eng/admin/products/circularlock/upload")
    public ResponseEntity<String> uploadFile(@ModelAttribute CircularLockEngDTO circularLockEngDTO) throws IOException {
        service.save(circularLockEngDTO);
        return ResponseEntity.ok("File uploaded successfully");
    }

    @PutMapping("/eng/admin/products/circularlock/update/{id}")
    public CircularLockEng update(@PathVariable Long id, @ModelAttribute CircularLockEngDTO dto) throws IOException {
        return service.update(id, dto);
    }

    @DeleteMapping("/eng/admin/products/circularlock/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}



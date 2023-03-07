package com.keyworld.projectboard.controller;

import com.keyworld.projectboard.domain.SpecialEng;
import com.keyworld.projectboard.dto.SpecialEngDTO;
import com.keyworld.projectboard.repository.SpecialEngRepository;
import com.keyworld.projectboard.service.SpecialEngService;
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
public class SpecialEngController {
    @Autowired
    private SpecialEngService service;

    @GetMapping("/eng/products/special")
    public List<SpecialEng> getAll() {
        return service.getAll();
    }

    @GetMapping("/eng/products/special/{id}")
    public SpecialEng getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/eng/products/special/search/{title}")
    public List<SpecialEng> getSpecialEngsByTitle(@RequestParam String title) {
        return service.findSpecialEngsByTitle(title);
    }

    @GetMapping("/eng/admin/products/special/search/{title}")
    public List<SpecialEng> getSpecialEngsByTitleAdmin(@PathVariable String title) {
        return service.findSpecialEngsByTitle(title);
    }

    @GetMapping("/eng/products/special/image/{id}/{fileIndex}")
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

    @GetMapping("/eng/products/special/download/{id}/{fileIndex}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id, @PathVariable int fileIndex) throws IOException {
        byte[] fileContent = service.getByIdAndFileIndex(id, fileIndex);
        ByteArrayResource resource = new ByteArrayResource(fileContent);

        SpecialEng specialEng = service.getById(id);
        String filename = specialEng.getTitle(); // modify to match your use case
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

    @PostMapping("/eng/admin/products/special/upload")
    public ResponseEntity<String> uploadFile(@ModelAttribute SpecialEngDTO specialEngDTO) throws IOException {
        service.save(specialEngDTO);
        return ResponseEntity.ok("File uploaded successfully");
    }

    @PutMapping("/eng/admin/products/special/update/{id}")
    public SpecialEng update(@PathVariable Long id, @ModelAttribute SpecialEngDTO dto) throws IOException {
        return service.update(id, dto);
    }

    @DeleteMapping("/eng/admin/products/special/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}



package com.keyworld.projectboard.controller;

import com.keyworld.projectboard.domain.CamLockEng;
import com.keyworld.projectboard.dto.CamLockEngDTO;
import com.keyworld.projectboard.repository.CamLockEngRepository;
import com.keyworld.projectboard.service.CamLockEngService;
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
public class CamLockEngController {
    @Autowired
    private CamLockEngService service;

    @GetMapping("/eng/products/camlock")
    public List<CamLockEng> getAll() {
        return service.getAll();
    }

    @GetMapping("/eng/products/camlock/{id}")
    public CamLockEng getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/eng/products/camlock/search/{title}")
    public List<CamLockEng> getCamLockEngsByTitle(@RequestParam String title) {
        return service.findCamLockEngsByTitle(title);
    }

    @GetMapping("/eng/admin/products/camlock/search/{title}")
    public List<CamLockEng> getCamLockEngsByTitleAdmin(@PathVariable String title) {
        return service.findCamLockEngsByTitle(title);
    }

    @GetMapping("/eng/products/camlock/image/{id}/{fileIndex}")
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

    @GetMapping("/eng/products/camlock/download/{id}/{fileIndex}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id, @PathVariable int fileIndex) throws IOException {
        byte[] fileContent = service.getByIdAndFileIndex(id, fileIndex);
        ByteArrayResource resource = new ByteArrayResource(fileContent);

        CamLockEng camLockEng = service.getById(id);
        String filename = camLockEng.getTitle(); // modify to match your use case
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

    @PostMapping("/eng/admin/products/camlock/upload")
    public ResponseEntity<String> uploadFile(@ModelAttribute CamLockEngDTO camLockEngDTO) throws IOException {
        service.save(camLockEngDTO);
        return ResponseEntity.ok("File uploaded successfully");
    }

    @PutMapping("/eng/admin/products/camlock/update/{id}")
    public CamLockEng update(@PathVariable Long id, @ModelAttribute CamLockEngDTO dto) throws IOException {
        return service.update(id, dto);
    }

    @DeleteMapping("/eng/admin/products/camlock/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}



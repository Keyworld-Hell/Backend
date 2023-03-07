package com.keyworld.projectboard.controller;

import com.keyworld.projectboard.domain.NpartLockEng;
import com.keyworld.projectboard.dto.NpartLockEngDTO;
import com.keyworld.projectboard.repository.NpartLockEngRepository;
import com.keyworld.projectboard.service.NpartLockEngService;
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
public class NpartLockEngController {
    @Autowired
    private NpartLockEngService service;

    @GetMapping("/eng/products/npartlock")
    public List<NpartLockEng> getAll() {
        return service.getAll();
    }

    @GetMapping("/eng/products/npartlock/{id}")
    public NpartLockEng getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/eng/products/npartlock/search/{title}")
    public List<NpartLockEng> getNpartLockEngsByTitle(@RequestParam String title) {
        return service.findNpartLockEngsByTitle(title);
    }

    @GetMapping("/eng/admin/products/npartlock/search/{title}")
    public List<NpartLockEng> getNpartLockEngsByTitleAdmin(@PathVariable String title) {
        return service.findNpartLockEngsByTitle(title);
    }

    @GetMapping("/eng/products/npartlock/image/{id}/{fileIndex}")
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

    @GetMapping("/eng/products/npartlock/download/{id}/{fileIndex}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id, @PathVariable int fileIndex) throws IOException {
        byte[] fileContent = service.getByIdAndFileIndex(id, fileIndex);
        ByteArrayResource resource = new ByteArrayResource(fileContent);

        NpartLockEng npartlockEng = service.getById(id);
        String filename = npartlockEng.getTitle(); // modify to match your use case
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

    @PostMapping("/eng/admin/products/npartlock/upload")
    public ResponseEntity<String> uploadFile(@ModelAttribute NpartLockEngDTO npartlockEngDTO) throws IOException {
        service.save(npartlockEngDTO);
        return ResponseEntity.ok("File uploaded successfully");
    }

    @PutMapping("/eng/admin/products/npartlock/update/{id}")
    public NpartLockEng update(@PathVariable Long id, @ModelAttribute NpartLockEngDTO dto) throws IOException {
        return service.update(id, dto);
    }

    @DeleteMapping("/eng/admin/products/npartlock/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}



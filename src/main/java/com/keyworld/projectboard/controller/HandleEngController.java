package com.keyworld.projectboard.controller;

import com.keyworld.projectboard.domain.HandleEng;
import com.keyworld.projectboard.dto.HandleEngDTO;
import com.keyworld.projectboard.repository.HandleEngRepository;
import com.keyworld.projectboard.service.HandleEngService;
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
public class HandleEngController {
    @Autowired
    private HandleEngService service;

    @GetMapping("/eng/products/handle")
    public List<HandleEng> getAll() {
        return service.getAll();
    }

    @GetMapping("/eng/products/handle/{id}")
    public HandleEng getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/eng/products/handle/search/{title}")
    public List<HandleEng> getHandleEngsByTitle(@RequestParam String title) {
        return service.findHandleEngsByTitle(title);
    }

    @GetMapping("/eng/admin/products/handle/search/{title}")
    public List<HandleEng> getHandleEngsByTitleAdmin(@PathVariable String title) {
        return service.findHandleEngsByTitle(title);
    }

    @GetMapping("/eng/products/handle/image/{id}/{fileIndex}")
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

    @GetMapping("/eng/products/handle/download/{id}/{fileIndex}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id, @PathVariable int fileIndex) throws IOException {
        byte[] fileContent = service.getByIdAndFileIndex(id, fileIndex);
        ByteArrayResource resource = new ByteArrayResource(fileContent);

        HandleEng handleEng = service.getById(id);
        String filename = handleEng.getTitle(); // modify to match your use case
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

    @PostMapping("/eng/admin/products/handle/upload")
    public ResponseEntity<String> uploadFile(@ModelAttribute HandleEngDTO handleEngDTO) throws IOException {
        service.save(handleEngDTO);
        return ResponseEntity.ok("File uploaded successfully");
    }

    @PutMapping("/eng/admin/products/handle/update/{id}")
    public HandleEng update(@PathVariable Long id, @ModelAttribute HandleEngDTO dto) throws IOException {
        return service.update(id, dto);
    }

    @DeleteMapping("/eng/admin/products/handle/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}



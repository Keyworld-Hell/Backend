package com.keyworld.projectboard.controller;

import com.keyworld.projectboard.domain.CustomLockEng;
import com.keyworld.projectboard.dto.CustomLockEngDTO;
import com.keyworld.projectboard.repository.CustomLockEngRepository;
import com.keyworld.projectboard.service.CustomLockEngService;
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
public class CustomLockEngController {
    @Autowired
    private CustomLockEngService service;
    @GetMapping("/eng/products/customlock")
    public List<CustomLockEng> getAll() {
        return service.getAll();
    }

    @GetMapping("/eng/products/customlock/{id}")
    public CustomLockEng getById(@PathVariable Long id) {
        return service.getById(id);
    }
    @GetMapping("/eng/products/customlock/search/{title}")
    public List<CustomLockEng> getCustomLockEngsByTitle(@RequestParam String title) {
        return service.findCustomLockEngsByTitle(title);
    }

    @GetMapping("/eng/admin/products/customlock/search/{title}")
    public List<CustomLockEng> getCustomLockEngsByTitleAdmin(@PathVariable String title) {
        return service.findCustomLockEngsByTitle(title);
    }

    @GetMapping("/eng/products/customlock/image/{id}/{fileIndex}")
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

    @GetMapping("/eng/products/customlock/download/{id}/{fileIndex}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id, @PathVariable int fileIndex) throws IOException {
        byte[] fileContent = service.getByIdAndFileIndex(id, fileIndex);
        ByteArrayResource resource = new ByteArrayResource(fileContent);

        CustomLockEng customLockEng = service.getById(id);
        String filename = customLockEng.getTitle(); // modify to match your use case
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

    @PostMapping("/eng/admin/products/customlock/upload")
    public ResponseEntity<String> uploadFile(@ModelAttribute CustomLockEngDTO customLockEngDTO) throws IOException {
        service.save(customLockEngDTO);
        return ResponseEntity.ok("File uploaded successfully");
    }

    @PutMapping("/eng/admin/products/customlock/update/{id}")
    public CustomLockEng update(@PathVariable Long id, @ModelAttribute CustomLockEngDTO dto) throws IOException {
        return service.update(id, dto);
    }

    @DeleteMapping("/eng/admin/products/customlock/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
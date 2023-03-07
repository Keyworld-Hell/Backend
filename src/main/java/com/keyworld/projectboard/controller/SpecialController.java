package com.keyworld.projectboard.controller;

import com.keyworld.projectboard.domain.Special;
import com.keyworld.projectboard.dto.SpecialDTO;
import com.keyworld.projectboard.repository.SpecialRepository;
import com.keyworld.projectboard.service.SpecialService;
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
public class SpecialController {
    @Autowired
    private SpecialService service;
    @GetMapping("/products/special")
    public List<Special> getAll() {
        return service.getAll();
    }

    @GetMapping("/products/special/{id}")
    public Special getById(@PathVariable Long id) {
        return service.getById(id);
    }
    @GetMapping("/products/special/search/{title}")
    public List<Special> getSpecialsByTitle(@RequestParam String title) {
        return service.findSpecialsByTitle(title);
    }

    @GetMapping("/admin/products/special/search/{title}")
    public List<Special> getSpecialsByTitleAdmin(@PathVariable String title) {
        return service.findSpecialsByTitle(title);
    }
    @GetMapping("/products/special/image/{id}/{fileIndex}")
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

    @GetMapping("/products/special/download/{id}/{fileIndex}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id, @PathVariable int fileIndex) throws IOException {
        byte[] fileContent = service.getByIdAndFileIndex(id, fileIndex);
        ByteArrayResource resource = new ByteArrayResource(fileContent);

        Special steelLock = service.getById(id);
        String filename = steelLock.getTitle(); // modify to match your use case
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

    @PostMapping("/admin/products/special/upload")
    public ResponseEntity<String> uploadFile(@ModelAttribute SpecialDTO steelLockDTO) throws IOException {
        service.save(steelLockDTO);
        return ResponseEntity.ok("File uploaded successfully");
    }

    @PutMapping("/admin/products/special/update/{id}")
    public Special update(@PathVariable Long id, @ModelAttribute SpecialDTO dto) throws IOException {
        return service.update(id, dto);
    }

    @DeleteMapping("/admin/products/special/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
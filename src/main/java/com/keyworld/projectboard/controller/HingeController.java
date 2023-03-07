package com.keyworld.projectboard.controller;

import com.keyworld.projectboard.domain.Hinge;
import com.keyworld.projectboard.dto.HingeDTO;
import com.keyworld.projectboard.repository.HingeRepository;
import com.keyworld.projectboard.service.HingeService;
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
public class HingeController {
    @Autowired
    private HingeService service;
    @GetMapping("/products/hinge")
    public List<Hinge> getAll() {
        return service.getAll();
    }

    @GetMapping("/products/hinge/{id}")
    public Hinge getById(@PathVariable Long id) {
        return service.getById(id);
    }
    @GetMapping("/products/hinge/search/{title}")
    public List<Hinge> getHingesByTitle(@RequestParam String title) {
        return service.findHingesByTitle(title);
    }

    @GetMapping("/admin/products/hinge/search/{title}")
    public List<Hinge> getHingesByTitleAdmin(@PathVariable String title) {
        return service.findHingesByTitle(title);
    }
    @GetMapping("/products/hinge/image/{id}/{fileIndex}")
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

    @GetMapping("/products/hinge/download/{id}/{fileIndex}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id, @PathVariable int fileIndex) throws IOException {
        byte[] fileContent = service.getByIdAndFileIndex(id, fileIndex);
        ByteArrayResource resource = new ByteArrayResource(fileContent);

        Hinge diskLock = service.getById(id);
        String filename = diskLock.getTitle(); // modify to match your use case
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

    @PostMapping("/admin/products/hinge/upload")
    public ResponseEntity<String> uploadFile(@ModelAttribute HingeDTO diskLockDTO) throws IOException {
        service.save(diskLockDTO);
        return ResponseEntity.ok("File uploaded successfully");
    }

    @PutMapping("/admin/products/hinge/update/{id}")
    public Hinge update(@PathVariable Long id, @ModelAttribute HingeDTO dto) throws IOException {
        return service.update(id, dto);
    }

    @DeleteMapping("/admin/products/hinge/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
package com.keyworld.projectboard.controller;

import com.keyworld.projectboard.domain.NpartLock;
import com.keyworld.projectboard.dto.NpartLockDTO;
import com.keyworld.projectboard.repository.NpartLockRepository;
import com.keyworld.projectboard.service.NpartLockService;
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
public class NpartLockController {
    @Autowired
    private NpartLockService service;
    @GetMapping("/products/npartlock")
    public List<NpartLock> getAll() {
        return service.getAll();
    }

    @GetMapping("/products/npartlock/{id}")
    public NpartLock getById(@PathVariable Long id) {
        return service.getById(id);
    }
    @GetMapping("/products/npartlock/search/{title}")
    public List<NpartLock> getNpartLocksByTitle(@RequestParam String title) {
        return service.findNpartLocksByTitle(title);
    }

    @GetMapping("/admin/products/npartlock/search/{title}")
    public List<NpartLock> getNpartLocksByTitleAdmin(@PathVariable String title) {
        return service.findNpartLocksByTitle(title);
    }
    @GetMapping("/products/npartlock/image/{id}/{fileIndex}")
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

    @GetMapping("/products/npartlock/download/{id}/{fileIndex}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id, @PathVariable int fileIndex) throws IOException {
        byte[] fileContent = service.getByIdAndFileIndex(id, fileIndex);
        ByteArrayResource resource = new ByteArrayResource(fileContent);

        NpartLock npartLock = service.getById(id);
        String filename = npartLock.getTitle(); // modify to match your use case
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

    @PostMapping("/admin/products/npartlock/upload")
    public ResponseEntity<String> uploadFile(@ModelAttribute NpartLockDTO npartLockDTO) throws IOException {
        service.save(npartLockDTO);
        return ResponseEntity.ok("File uploaded successfully");
    }

    @PutMapping("/admin/products/npartlock/update/{id}")
    public NpartLock update(@PathVariable Long id, @ModelAttribute NpartLockDTO dto) throws IOException {
        return service.update(id, dto);
    }

    @DeleteMapping("/admin/products/npartlock/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
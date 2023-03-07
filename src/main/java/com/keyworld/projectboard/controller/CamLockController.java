package com.keyworld.projectboard.controller;

import com.keyworld.projectboard.dto.CamLockDTO;
import com.keyworld.projectboard.service.CamLockService;
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
public class CamLockController {
    @Autowired
    private CamLockService service;
    @GetMapping("/products/camlock")
    public List<CamLock> getAll() {
        return service.getAll();
    }

    @GetMapping("/products/camlock/{id}")
    public CamLock getById(@PathVariable Long id) {
        return service.getById(id);
    }
    @GetMapping("/products/camlock/search/{title}")
    public List<CamLock> getCamLocksByTitle(@RequestParam String title) {
        return service.findCamLocksByTitle(title);
    }

    @GetMapping("/admin/products/camlock/search/{title}")
    public List<CamLock> getCamLocksByTitleAdmin(@PathVariable String title) {
        return service.findCamLocksByTitle(title);
    }
    @GetMapping("/products/camlock/image/{id}/{fileIndex}")
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

    @GetMapping("/products/camlock/download/{id}/{fileIndex}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id, @PathVariable int fileIndex) throws IOException {
        byte[] fileContent = service.getByIdAndFileIndex(id, fileIndex);
        ByteArrayResource resource = new ByteArrayResource(fileContent);

        CamLock camLock = service.getById(id);
        String filename = camLock.getTitle(); // modify to match your use case
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

    @PostMapping("/admin/products/camlock/upload")
    public ResponseEntity<String> uploadFile(@ModelAttribute CamLockDTO camLockDTO) throws IOException {
        service.save(camLockDTO);
        return ResponseEntity.ok("File uploaded successfully");
    }

    @PutMapping("/admin/products/camlock/update/{id}")
    public CamLock update(@PathVariable Long id, @ModelAttribute CamLockDTO dto) throws IOException {
        return service.update(id, dto);
    }

    @DeleteMapping("/admin/products/camlock/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

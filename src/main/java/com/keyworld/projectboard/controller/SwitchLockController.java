package com.keyworld.projectboard.controller;

import com.keyworld.projectboard.domain.SwitchLock;
import com.keyworld.projectboard.dto.SwitchLockDTO;
import com.keyworld.projectboard.repository.SwitchLockRepository;
import com.keyworld.projectboard.service.SwitchLockService;
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
public class SwitchLockController {
    @Autowired
    private SwitchLockService service;
    @GetMapping("/products/switchlock")
    public List<SwitchLock> getAll() {
        return service.getAll();
    }

    @GetMapping("/products/switchlock/{id}")
    public SwitchLock getById(@PathVariable Long id) {
        return service.getById(id);
    }
    @GetMapping("/products/switchlock/search/{title}")
    public List<SwitchLock> getSwitchLocksByTitle(@RequestParam String title) {
        return service.findSwitchLocksByTitle(title);
    }

    @GetMapping("/admin/products/switchlock/search/{title}")
    public List<SwitchLock> getSwitchLocksByTitleAdmin(@PathVariable String title) {
        return service.findSwitchLocksByTitle(title);
    }
    @GetMapping("/products/switchlock/image/{id}/{fileIndex}")
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

    @GetMapping("/products/switchlock/download/{id}/{fileIndex}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id, @PathVariable int fileIndex) throws IOException {
        byte[] fileContent = service.getByIdAndFileIndex(id, fileIndex);
        ByteArrayResource resource = new ByteArrayResource(fileContent);

        SwitchLock switchLock = service.getById(id);
        String filename = switchLock.getTitle(); // modify to match your use case
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

    @PostMapping("/admin/products/switchlock/upload")
    public ResponseEntity<String> uploadFile(@ModelAttribute SwitchLockDTO switchLockDTO) throws IOException {
        service.save(switchLockDTO);
        return ResponseEntity.ok("File uploaded successfully");
    }

    @PutMapping("/admin/products/switchlock/update/{id}")
    public SwitchLock update(@PathVariable Long id, @ModelAttribute SwitchLockDTO dto) throws IOException {
        return service.update(id, dto);
    }

    @DeleteMapping("/admin/products/switchlock/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
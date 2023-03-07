package com.keyworld.projectboard.controller;

import com.keyworld.projectboard.domain.Handle;
import com.keyworld.projectboard.dto.HandleDTO;
import com.keyworld.projectboard.repository.HandleRepository;
import com.keyworld.projectboard.service.HandleService;
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
public class HandleController {
    @Autowired
    private HandleService service;
    @GetMapping("/products/handle")
    public List<Handle> getAll() {
        return service.getAll();
    }

    @GetMapping("/products/handle/{id}")
    public Handle getById(@PathVariable Long id) {
        return service.getById(id);
    }
    @GetMapping("/products/handle/search/{title}")
    public List<Handle> getHandlesByTitle(@RequestParam String title) {
        return service.findHandlesByTitle(title);
    }

    @GetMapping("/admin/products/handle/search/{title}")
    public List<Handle> getHandlesByTitleAdmin(@PathVariable String title) {
        return service.findHandlesByTitle(title);
    }
    @GetMapping("/products/handle/image/{id}/{fileIndex}")
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

    @GetMapping("/products/handle/download/{id}/{fileIndex}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id, @PathVariable int fileIndex) throws IOException {
        byte[] fileContent = service.getByIdAndFileIndex(id, fileIndex);
        ByteArrayResource resource = new ByteArrayResource(fileContent);

        Handle diskLock = service.getById(id);
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

    @PostMapping("/admin/products/handle/upload")
    public ResponseEntity<String> uploadFile(@ModelAttribute HandleDTO diskLockDTO) throws IOException {
        service.save(diskLockDTO);
        return ResponseEntity.ok("File uploaded successfully");
    }

    @PutMapping("/admin/products/handle/update/{id}")
    public Handle update(@PathVariable Long id, @ModelAttribute HandleDTO dto) throws IOException {
        return service.update(id, dto);
    }

    @DeleteMapping("/admin/products/handle/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
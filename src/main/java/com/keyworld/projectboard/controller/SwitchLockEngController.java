package com.keyworld.projectboard.controller;

import com.keyworld.projectboard.domain.SwitchLockEng;
import com.keyworld.projectboard.dto.SwitchLockEngDTO;
import com.keyworld.projectboard.repository.SwitchLockEngRepository;
import com.keyworld.projectboard.service.SwitchLockEngService;
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
public class SwitchLockEngController {
    @Autowired
    private SwitchLockEngService service;

    @GetMapping("/eng/products/switchlock")
    public List<SwitchLockEng> getAll() {
        return service.getAll();
    }

    @GetMapping("/eng/products/switchlock/{id}")
    public SwitchLockEng getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/eng/products/switchlock/search/{title}")
    public List<SwitchLockEng> getSwitchLockEngsByTitle(@RequestParam String title) {
        return service.findSwitchLockEngsByTitle(title);
    }

    @GetMapping("/eng/admin/products/switchlock/search/{title}")
    public List<SwitchLockEng> getSwitchLockEngsByTitleAdmin(@PathVariable String title) {
        return service.findSwitchLockEngsByTitle(title);
    }

    @GetMapping("/eng/products/switchlock/image/{id}/{fileIndex}")
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

    @GetMapping("/eng/products/switchlock/download/{id}/{fileIndex}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id, @PathVariable int fileIndex) throws IOException {
        byte[] fileContent = service.getByIdAndFileIndex(id, fileIndex);
        ByteArrayResource resource = new ByteArrayResource(fileContent);

        SwitchLockEng switchlockEng = service.getById(id);
        String filename = switchlockEng.getTitle(); // modify to match your use case
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

    @PostMapping("/eng/admin/products/switchlock/upload")
    public ResponseEntity<String> uploadFile(@ModelAttribute SwitchLockEngDTO switchlockEngDTO) throws IOException {
        service.save(switchlockEngDTO);
        return ResponseEntity.ok("File uploaded successfully");
    }

    @PutMapping("/eng/admin/products/switchlock/update/{id}")
    public SwitchLockEng update(@PathVariable Long id, @ModelAttribute SwitchLockEngDTO dto) throws IOException {
        return service.update(id, dto);
    }

    @DeleteMapping("/eng/admin/products/switchlock/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}



package com.keyworld.projectboard.controller;

import com.keyworld.projectboard.domain.HingeEng;
import com.keyworld.projectboard.dto.HingeEngDTO;
import com.keyworld.projectboard.repository.HingeEngRepository;
import com.keyworld.projectboard.service.HingeEngService;
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
public class HingeEngController {
    @Autowired
    private HingeEngService service;

    @GetMapping("/eng/products/hinge")
    public List<HingeEng> getAll() {
        return service.getAll();
    }

    @GetMapping("/eng/products/hinge/{id}")
    public HingeEng getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/eng/products/hinge/search/{title}")
    public List<HingeEng> getHingeEngsByTitle(@RequestParam String title) {
        return service.findHingeEngsByTitle(title);
    }

    @GetMapping("/eng/admin/products/hinge/search/{title}")
    public List<HingeEng> getHingeEngsByTitleAdmin(@PathVariable String title) {
        return service.findHingeEngsByTitle(title);
    }

    @GetMapping("/eng/products/hinge/image/{id}/{fileIndex}")
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

    @GetMapping("/eng/products/hinge/download/{id}/{fileIndex}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id, @PathVariable int fileIndex) throws IOException {
        byte[] fileContent = service.getByIdAndFileIndex(id, fileIndex);
        ByteArrayResource resource = new ByteArrayResource(fileContent);

        HingeEng hingeEng = service.getById(id);
        String filename = hingeEng.getTitle(); // modify to match your use case
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

    @PostMapping("/eng/admin/products/hinge/upload")
    public ResponseEntity<String> uploadFile(@ModelAttribute HingeEngDTO hingeEngDTO) throws IOException {
        service.save(hingeEngDTO);
        return ResponseEntity.ok("File uploaded successfully");
    }

    @PutMapping("/eng/admin/products/hinge/update/{id}")
    public HingeEng update(@PathVariable Long id, @ModelAttribute HingeEngDTO dto) throws IOException {
        return service.update(id, dto);
    }

    @DeleteMapping("/eng/admin/products/hinge/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}



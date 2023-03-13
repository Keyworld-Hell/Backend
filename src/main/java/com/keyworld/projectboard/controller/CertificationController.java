package com.keyworld.projectboard.controller;
import com.keyworld.projectboard.domain.Certification;
import com.keyworld.projectboard.dto.CertificationDTO;
import com.keyworld.projectboard.service.CertificationService;
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
public class CertificationController {
    @Autowired
    private CertificationService service;

    @GetMapping("{language}/certification")
    public List<Certification> getByLanguage(@PathVariable Boolean language) {
        return service.findByLanguage(language);
    }

    @GetMapping("/certification/{id}")
    public Certification getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/certification/image/{id}/{fileIndex}")
    public ResponseEntity<Resource> getImage(@PathVariable Long id, @PathVariable int fileIndex) {
        Certification certification = service.getById(id);
        byte[] imageData = certification.getFile();
        ByteArrayResource resource = new ByteArrayResource(imageData);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_JPEG_VALUE);

        return ResponseEntity.ok()
                .contentLength(imageData.length)
                .headers(headers)
                .body(resource);
    }

    @PostMapping("/admin/certification/upload")
    public ResponseEntity<String> uploadFile(@ModelAttribute CertificationDTO certificationDTO) throws IOException {

        service.save(certificationDTO);
        return ResponseEntity.ok("File uploaded successfully");
    }

    @PutMapping("/admin/certification/update/{id}")
    public Certification update(@PathVariable Long id, @ModelAttribute CertificationDTO certificationDTO) throws IOException {
        return service.update(id, certificationDTO);
    }

    @DeleteMapping("/admin/certification/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

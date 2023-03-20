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
    public ResponseEntity<CertificationDTO> getById(@PathVariable Long id) throws IOException {
        CertificationDTO certificationDTO = service.getById(id);
        return ResponseEntity.ok(certificationDTO);
    }



    @PostMapping("/adm/certification/new")
    public ResponseEntity<String> uploadFile(@ModelAttribute CertificationDTO certificationDTO) throws IOException {

        service.save(certificationDTO);
        return ResponseEntity.ok("File uploaded successfully");
    }

    @PutMapping("/adm/certification/update/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @ModelAttribute CertificationDTO certificationDTO) throws IOException {
        service.update(id, certificationDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/adm/certification/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

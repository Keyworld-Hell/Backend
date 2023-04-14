package com.keyworld.projectboard.controller;
import com.keyworld.projectboard.domain.Company;
import com.keyworld.projectboard.dto.CompanyDTO;
import com.keyworld.projectboard.service.CompanyService;
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
public class CompanyController {
    @Autowired
    private CompanyService service;

    @GetMapping("{language}/company")
    public List<Company> getByLanguage(@PathVariable Boolean language) {
        return service.findByLanguage(language);
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<CompanyDTO> getById(@PathVariable Long id) throws IOException {
        CompanyDTO certificationDTO = service.getById(id);
        return ResponseEntity.ok(certificationDTO);
    }

    @PostMapping("/adm/company/new")
    public ResponseEntity<String> uploadFile(@ModelAttribute CompanyDTO certificationDTO) throws IOException {

        service.save(certificationDTO);
        return ResponseEntity.ok("File uploaded successfully");
    }

    @PutMapping("/adm/company/update/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @ModelAttribute CompanyDTO certificationDTO) throws IOException {
        service.update(id, certificationDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/adm/company/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

package com.keyworld.projectboard.controller;

import com.keyworld.projectboard.domain.Certification;
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

    @GetMapping("/inspect")
    public List<Company> getAll() {
        return service.getAll();
    }

    @GetMapping("/{language}/inspect")
    public List<Company> findByLanguage(@PathVariable Boolean language){
        return service.findByLanguage(language);
    }

    @GetMapping("/inspect/{id}")
    public Company getById(@PathVariable Long id) {
        return service.getById(id);
    }
    @GetMapping("/inspect/image/{id}/{fileIndex}")
    public ResponseEntity<Resource> getImage(@PathVariable Long id, @PathVariable int fileIndex) {
        Company company = service.getById(id);
        byte[] imageData = company.getFile();
        ByteArrayResource resource = new ByteArrayResource(imageData);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_JPEG_VALUE);

        return ResponseEntity.ok()
                .contentLength(imageData.length)
                .headers(headers)
                .body(resource);
    }

    @PostMapping("/adm/inspect/upload")
    public ResponseEntity<String> uploadFile(@ModelAttribute CompanyDTO companyDTO) throws IOException {
        service.save(companyDTO);
        return ResponseEntity.ok("File uploaded successfully");
    }

    @PutMapping("/adm/inspect/update/{id}")
    public Company update(@PathVariable Long id, @ModelAttribute CompanyDTO companyDTO) throws IOException {
        return service.update(id, companyDTO);
    }

    @DeleteMapping("/adm/inspect/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

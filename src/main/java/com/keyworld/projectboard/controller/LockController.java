package com.keyworld.projectboard.controller;

import com.keyworld.projectboard.domain.Locks;
import com.keyworld.projectboard.dto.LockDTO;
import com.keyworld.projectboard.service.LockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/")
public class LockController {
    @Autowired
    private LockService service;
    @GetMapping("/{language}/products/{number}")
    public List<Locks> getAll(@PathVariable Boolean language, @PathVariable Long number) {
        return service.getAll(language, number);
    }

    @GetMapping("/{language}/products/{number}/{id}")
    public Locks getById(@PathVariable Boolean language, @PathVariable Long number, @PathVariable Long id) {
        return service.getByLanguageAndNumberAndId(language, number, id);
    }

    @GetMapping("/{language}/products/{number}/search/{title}")
    public List<Locks> getLockByTitle(@PathVariable Boolean language, @PathVariable Long number, @PathVariable String title) {
        return service.findLocksByTitle(language, number, title);
    }

    @PostMapping("/adm/products/upload")
    public ResponseEntity<String> uploadFile(@ModelAttribute LockDTO lockDTO) throws IOException {
        service.save(lockDTO);
        return ResponseEntity.ok("File uploaded successfully");
    }

    @PutMapping("/adm/{language}/products/update/{number}/{id}")
    public Locks update(@PathVariable Boolean language, @PathVariable Long number, @PathVariable Long id, @ModelAttribute LockDTO dto) throws IOException {
        return service.update(language, number, id, dto);
    }

    @DeleteMapping("/adm/{language}/products/delete/{number}/{id}")
    public ResponseEntity<Void> delete(@PathVariable Boolean language, @PathVariable Long number, @PathVariable Long id) {
        service.delete(language, number, id);
        return ResponseEntity.noContent().build();
    }
}

package com.keyworld.projectboard.controller;

import com.keyworld.projectboard.domain.Lock;
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
    public List<Lock> getAll(@PathVariable Boolean language, @PathVariable Long number) {
        return service.getAll(language, number);
    }

    @GetMapping("/{language}/products/{number}/{id}")
    public Lock getById(@PathVariable Boolean language, @PathVariable Long number, @PathVariable Long id) {
        return service.getByLanguageAndNumberAndId(language, number, id);
    }

    @GetMapping("/{language}/products/{number}/search/{title}")
    public List<Lock> getLockByTitle(@PathVariable Boolean language, @PathVariable Long number, @PathVariable String title) {
        return service.findLocksByTitle(language, number, title);
    }

    @PostMapping("/admin/products/upload")
    public ResponseEntity<String> uploadFile(@ModelAttribute LockDTO lockDTO) throws IOException {
        service.save(lockDTO);
        return ResponseEntity.ok("File uploaded successfully");
    }

    @PutMapping("{language}/admin/products/update/{number}/{id}")
    public Lock update(@PathVariable Boolean language, @PathVariable Long number, @PathVariable Long id, @ModelAttribute LockDTO dto) throws IOException {
        return service.update(language, number, id, dto);
    }

    @DeleteMapping("{language}/admin/products/delete/{number}/{id}")
    public ResponseEntity<Void> delete(@PathVariable Boolean language, @PathVariable Long number, @PathVariable Long id) {
        service.delete(language, number, id);
        return ResponseEntity.noContent().build();
    }
}

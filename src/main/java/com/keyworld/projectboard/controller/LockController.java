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
    @GetMapping("/products/camlock")
    public List<Lock> getAll() {
        return service.getAll();
    }

    @GetMapping("/products/camlock/{id}")
    public Lock getById(@PathVariable Long id) {
        return service.getById(id);
    }
    @GetMapping("/products/camlock/search/{title}")
    public List<Lock> getCamLocksByTitle(@RequestParam String title) {
        return service.findLocksByTitle(title);
    }

    @GetMapping("/admin/products/camlock/search/{title}")
    public List<Lock> getCamLocksByTitleAdmin(@PathVariable String title) {
        return service.findLocksByTitle(title);
    }


    @PostMapping("/admin/products/camlock/upload")
    public ResponseEntity<String> uploadFile(@ModelAttribute LockDTO lockDTO) throws IOException {
        service.save(lockDTO);
        return ResponseEntity.ok("File uploaded successfully");
    }

    @PutMapping("/admin/products/camlock/update/{id}")
    public Lock update(@PathVariable Long id, @ModelAttribute LockDTO dto) throws IOException {
        return service.update(id, dto);
    }

    @DeleteMapping("/admin/products/camlock/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

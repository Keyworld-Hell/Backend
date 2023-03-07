package com.keyworld.projectboard.repository;

import com.keyworld.projectboard.domain.CircularLockEng;
import com.keyworld.projectboard.domain.DiskLock;
import com.keyworld.projectboard.domain.Special;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecialRepository extends
        JpaRepository<Special, Long> {
    List<Special> findByTitleContainingIgnoreCase(String title);
}
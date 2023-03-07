package com.keyworld.projectboard.repository;

import com.keyworld.projectboard.domain.CircularLockEng;
import com.keyworld.projectboard.domain.DiskLockEng;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiskLockEngRepository extends
        JpaRepository<DiskLockEng, Long> {
    List<DiskLockEng> findByTitleContainingIgnoreCase(String title);
}
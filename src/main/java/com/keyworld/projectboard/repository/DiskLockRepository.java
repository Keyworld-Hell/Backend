package com.keyworld.projectboard.repository;

import com.keyworld.projectboard.domain.CircularLockEng;
import com.keyworld.projectboard.domain.DiskLock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiskLockRepository extends
        JpaRepository<DiskLock, Long> {
    List<DiskLock> findByTitleContainingIgnoreCase(String title);
}
package com.keyworld.projectboard.repository;

import com.keyworld.projectboard.domain.CircularLock;
import com.keyworld.projectboard.domain.CircularLockEng;
import com.keyworld.projectboard.domain.DimpleLock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DimpleLockRepository extends
        JpaRepository<DimpleLock, Long> {
    List<DimpleLock> findByTitleContainingIgnoreCase(String title);
}
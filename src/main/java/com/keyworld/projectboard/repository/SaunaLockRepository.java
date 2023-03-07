package com.keyworld.projectboard.repository;

import com.keyworld.projectboard.domain.CircularLockEng;
import com.keyworld.projectboard.domain.SaunaLock;
import com.keyworld.projectboard.domain.Special;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaunaLockRepository extends
        JpaRepository<SaunaLock, Long> {
    List<SaunaLock> findByTitleContainingIgnoreCase(String title);
}
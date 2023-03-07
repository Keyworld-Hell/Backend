package com.keyworld.projectboard.repository;

import com.keyworld.projectboard.domain.CircularLockEng;
import com.keyworld.projectboard.domain.SaunaLock;
import com.keyworld.projectboard.domain.SteelLock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SteelLockRepository extends
        JpaRepository<SteelLock, Long> {
    List<SteelLock> findByTitleContainingIgnoreCase(String title);
}
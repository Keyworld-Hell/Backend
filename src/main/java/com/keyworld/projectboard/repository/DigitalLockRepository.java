package com.keyworld.projectboard.repository;

import com.keyworld.projectboard.domain.CircularLockEng;
import com.keyworld.projectboard.domain.DigitalLock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DigitalLockRepository extends
        JpaRepository<DigitalLock, Long> {
    List<DigitalLock> findByTitleContainingIgnoreCase(String title);
}
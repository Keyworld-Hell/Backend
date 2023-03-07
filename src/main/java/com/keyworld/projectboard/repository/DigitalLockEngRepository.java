package com.keyworld.projectboard.repository;

import com.keyworld.projectboard.domain.CircularLockEng;
import com.keyworld.projectboard.domain.DigitalLockEng;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DigitalLockEngRepository extends
        JpaRepository<DigitalLockEng, Long> {
    List<DigitalLockEng> findByTitleContainingIgnoreCase(String title);
        }
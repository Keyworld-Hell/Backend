package com.keyworld.projectboard.repository;

import com.keyworld.projectboard.domain.CircularLockEng;
import com.keyworld.projectboard.domain.SaunaLockEng;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaunaLockEngRepository extends
        JpaRepository<SaunaLockEng, Long> {
    List<SaunaLockEng> findByTitleContainingIgnoreCase(String title);
}
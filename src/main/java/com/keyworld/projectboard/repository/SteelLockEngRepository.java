package com.keyworld.projectboard.repository;

import com.keyworld.projectboard.domain.CircularLockEng;
import com.keyworld.projectboard.domain.SteelLockEng;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SteelLockEngRepository extends
        JpaRepository<SteelLockEng, Long> {
    List<SteelLockEng> findByTitleContainingIgnoreCase(String title);
}
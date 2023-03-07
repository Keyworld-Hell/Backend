package com.keyworld.projectboard.repository;

import com.keyworld.projectboard.domain.CircularLockEng;
import com.keyworld.projectboard.domain.DimpleLockEng;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DimpleLockEngRepository extends
        JpaRepository<DimpleLockEng, Long> {
    List<DimpleLockEng> findByTitleContainingIgnoreCase(String title);
}
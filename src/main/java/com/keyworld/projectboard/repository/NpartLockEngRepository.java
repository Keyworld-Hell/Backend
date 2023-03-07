package com.keyworld.projectboard.repository;

import com.keyworld.projectboard.domain.CircularLockEng;
import com.keyworld.projectboard.domain.NpartLockEng;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NpartLockEngRepository extends
        JpaRepository<NpartLockEng, Long> {
    List<NpartLockEng> findByTitleContainingIgnoreCase(String title);
}
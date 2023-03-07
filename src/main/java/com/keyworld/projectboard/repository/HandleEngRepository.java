package com.keyworld.projectboard.repository;

import com.keyworld.projectboard.domain.CircularLockEng;
import com.keyworld.projectboard.domain.HandleEng;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HandleEngRepository extends
        JpaRepository<HandleEng, Long> {
    List<HandleEng> findByTitleContainingIgnoreCase(String title);
}
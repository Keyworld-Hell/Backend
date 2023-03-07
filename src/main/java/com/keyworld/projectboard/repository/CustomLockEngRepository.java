package com.keyworld.projectboard.repository;

import com.keyworld.projectboard.domain.CircularLock;
import com.keyworld.projectboard.domain.CircularLockEng;
import com.keyworld.projectboard.domain.CustomLockEng;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomLockEngRepository extends
        JpaRepository<CustomLockEng, Long> {
    List<CustomLockEng> findByTitleContainingIgnoreCase(String title);
}
package com.keyworld.projectboard.repository;

import com.keyworld.projectboard.domain.CircularLock;
import com.keyworld.projectboard.domain.CircularLockEng;
import com.keyworld.projectboard.domain.CustomLock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomLockRepository extends
        JpaRepository<CustomLock, Long> {
    List<CustomLock> findByTitleContainingIgnoreCase(String title);
}
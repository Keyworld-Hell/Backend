package com.keyworld.projectboard.repository;

import com.keyworld.projectboard.domain.CamLockEng;
import com.keyworld.projectboard.domain.CircularLock;
import com.keyworld.projectboard.domain.CircularLockEng;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CircularLockEngRepository extends
        JpaRepository<CircularLockEng, Long> {
    List<CircularLockEng> findByTitleContainingIgnoreCase(String title);
}
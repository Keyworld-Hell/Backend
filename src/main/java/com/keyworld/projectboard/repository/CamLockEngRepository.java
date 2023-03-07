package com.keyworld.projectboard.repository;

import com.keyworld.projectboard.domain.CamLock;
import com.keyworld.projectboard.domain.CamLockEng;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CamLockEngRepository extends
        JpaRepository<CamLockEng, Long> {
    List<CamLockEng> findByTitleContainingIgnoreCase(String title);
}
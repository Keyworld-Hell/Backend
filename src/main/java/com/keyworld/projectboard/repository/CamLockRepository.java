package com.keyworld.projectboard.repository;

import com.keyworld.projectboard.domain.CamLock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CamLockRepository extends
        JpaRepository<CamLock, Long> {
    List<CamLock> findByTitleContainingIgnoreCase(String title);
}
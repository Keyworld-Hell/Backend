package com.keyworld.projectboard.repository;

import com.keyworld.projectboard.domain.CircularLockEng;
import com.keyworld.projectboard.domain.SwitchLock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SwitchLockRepository extends
        JpaRepository<SwitchLock, Long> {
    List<SwitchLock> findByTitleContainingIgnoreCase(String title);
}
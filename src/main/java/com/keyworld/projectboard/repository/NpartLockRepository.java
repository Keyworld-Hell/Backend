package com.keyworld.projectboard.repository;

import com.keyworld.projectboard.domain.CircularLockEng;
import com.keyworld.projectboard.domain.Handle;
import com.keyworld.projectboard.domain.NpartLock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NpartLockRepository extends
        JpaRepository<NpartLock, Long> {
    List<NpartLock> findByTitleContainingIgnoreCase(String title);
}
package com.keyworld.projectboard.repository;

import com.keyworld.projectboard.domain.CircularLockEng;
import com.keyworld.projectboard.domain.DigitalLock;
import com.keyworld.projectboard.domain.Handle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HandleRepository extends
        JpaRepository<Handle, Long> {
    List<Handle> findByTitleContainingIgnoreCase(String title);
}
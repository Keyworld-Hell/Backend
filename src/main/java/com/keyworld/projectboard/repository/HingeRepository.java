package com.keyworld.projectboard.repository;

import com.keyworld.projectboard.domain.CircularLockEng;
import com.keyworld.projectboard.domain.Hinge;
import com.keyworld.projectboard.domain.NpartLock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HingeRepository extends
        JpaRepository<Hinge, Long> {
    List<Hinge> findByTitleContainingIgnoreCase(String title);
}
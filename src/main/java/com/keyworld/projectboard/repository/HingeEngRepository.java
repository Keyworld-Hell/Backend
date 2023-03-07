package com.keyworld.projectboard.repository;

import com.keyworld.projectboard.domain.CircularLockEng;
import com.keyworld.projectboard.domain.HingeEng;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HingeEngRepository extends
        JpaRepository<HingeEng, Long> {
    List<HingeEng> findByTitleContainingIgnoreCase(String title);
}
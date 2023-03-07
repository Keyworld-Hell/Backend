package com.keyworld.projectboard.repository;

import com.keyworld.projectboard.domain.CircularLockEng;
import com.keyworld.projectboard.domain.SwitchLockEng;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SwitchLockEngRepository extends
        JpaRepository<SwitchLockEng, Long> {
    List<SwitchLockEng> findByTitleContainingIgnoreCase(String title);
}

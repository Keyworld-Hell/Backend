package com.keyworld.projectboard.repository;

import com.keyworld.projectboard.domain.CircularLockEng;
import com.keyworld.projectboard.domain.SpecialEng;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecialEngRepository extends JpaRepository<SpecialEng, Long> {
    List<SpecialEng> findByTitleContainingIgnoreCase(String title);
}

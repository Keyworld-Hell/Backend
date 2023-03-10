package com.keyworld.projectboard.repository;

import com.keyworld.projectboard.domain.Lock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LockRepository extends
        JpaRepository<com.keyworld.projectboard.domain.Lock, Long> {
    List<Lock> findByLanguageAndNumberAndTitleContainingIgnoreCase(Boolean Language, Long number, String title);

    List<Lock> findByLanguageAndNumber(Boolean language, Long number);

    Lock findByLanguageAndNumberAndId(Boolean language, Long number, Long Id);
}
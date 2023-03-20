package com.keyworld.projectboard.repository;

import com.keyworld.projectboard.domain.Locks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LockRepository extends
        JpaRepository<Locks, Long> {
    List<Locks> findByLanguageAndNumberAndTitleContainingIgnoreCase(Boolean Language, Long number, String title);

    List<Locks> findByLanguageAndNumber(Boolean language, Long number);

    Locks findByLanguageAndNumberAndId(Boolean language, Long number, Long Id);
}
package com.keyworld.projectboard.repository;

import com.keyworld.projectboard.domain.Inquiry;
import com.keyworld.projectboard.repository.querydsl.InquiryRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource

public interface InquiryRepository extends
        JpaRepository<Inquiry, Long>{

    Page<Inquiry> findByTitleContaining(String title, Pageable pageable);

    Page<Inquiry> findByContentContaining(String content, Pageable pageable);

    void deleteById(Long inquiryId);
}

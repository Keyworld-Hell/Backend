package com.keyworld.projectboard.repository;

import com.keyworld.projectboard.domain.Inquiry;
import com.keyworld.projectboard.domain.QInquiry;
import com.keyworld.projectboard.repository.querydsl.InquiryRepositoryCustom;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource

public interface InquiryRepository extends
        JpaRepository<Inquiry, Long>,
        InquiryRepositoryCustom,
        QuerydslPredicateExecutor<Inquiry>,
        QuerydslBinderCustomizer<QInquiry> {

    Page<Inquiry> findByTitleContaining(String title, Pageable pageable);

    Page<Inquiry> findByContentContaining(String content, Pageable pageable);

    void deleteById(Long inquiryId);

    default void customize(QuerydslBindings bindings, QInquiry root) {
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.title, root.content, root.createdAt, root.createdBy);
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
    }
}

package com.keyworld.projectboard.dto;

import com.keyworld.projectboard.domain.Article;

import java.time.LocalDateTime;

public record ArticleDTO(
        Long id,

        String author,
        String title,
        String content,
        String password,
        Boolean adm,

        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {

    public static ArticleDTO of(String author, String title, String content, String password, Boolean adm ) {
        return new ArticleDTO(null, author, title, content, password,adm, LocalDateTime.now(), LocalDateTime.now());
    }

    public static ArticleDTO of(Long id, String author, String title, String content, String password, Boolean adm, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        return new ArticleDTO(id, author, title, content, password,adm,  createdAt, modifiedAt);
    }

    public static ArticleDTO from(Article entity) {
        return new ArticleDTO(
                entity.getId(),
                entity.getAuthor(),
                entity.getTitle(),
                entity.getContent(),
                entity.getPassword(),
                entity.getAdm(),
                entity.getCreatedAt(),
                entity.getModifiedAt()
        );
    }

    public Article toEntity() {
        return Article.of(
                author,
                title,
                content,
                password,
                adm
        );
    }

}

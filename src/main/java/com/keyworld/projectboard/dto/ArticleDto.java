package com.keyworld.projectboard.dto;

import com.keyworld.projectboard.domain.Article;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public record ArticleDto(
        Long id,

        String author,
        String title,
        String content,
        String password,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {

    public static ArticleDto of(String author, String title, String content, String password) {
        return new ArticleDto(null, author, title, content,password, null, null);
    }

    public static ArticleDto of(Long id, String author, String title, String content, String password, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        return new ArticleDto(id, author, title, content, password, createdAt, modifiedAt);
    }

    public static ArticleDto from(Article entity) {
        return new ArticleDto(
                entity.getId(),
                entity.getAuthor(),
                entity.getTitle(),
                entity.getContent(),
                entity.getPassword(),
                entity.getCreatedAt(),
                entity.getModifiedAt()
        );
    }

    public Article toEntity() {
        return Article.of(
                author,
                title,
                content,
                password
        );
    }

}

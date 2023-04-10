package com.keyworld.projectboard.dto.response;

import com.keyworld.projectboard.dto.ArticleDto;

import java.time.LocalDateTime;

public record ArticleResponse(
        Long id,
        String author,
        String title,
        String content,
        String password,
        Boolean adm,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {

    public static ArticleResponse of(Long id, String author, String title, String content, String password, Boolean adm, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        return new ArticleResponse(id, author, title, content, password, adm, createdAt, modifiedAt);
    }

    public static ArticleResponse from(ArticleDto dto) {

        return new ArticleResponse(
                dto.id(),
                dto.author(),
                dto.title(),
                dto.content(),
                dto.password(),
                dto.adm(),
                dto.createdAt(),
                dto.modifiedAt()
        );
    }

}

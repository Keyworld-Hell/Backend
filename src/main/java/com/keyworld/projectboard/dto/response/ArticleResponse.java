package com.keyworld.projectboard.dto.response;

import com.keyworld.projectboard.dto.ArticleDto;

import java.time.LocalDateTime;

public record ArticleResponse(
        Long id,
        String author,
        String title,
        String content,
        LocalDateTime createdAt
) {

    public static ArticleResponse of(Long id, String author, String title, String content,LocalDateTime createdAt) {
        return new ArticleResponse(id, author, title, content, createdAt);
    }

    public static ArticleResponse from(ArticleDto dto) {

        return new ArticleResponse(
                dto.id(),
                dto.author(),
                dto.title(),
                dto.content(),
                dto.createdAt()
        );
    }

}

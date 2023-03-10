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
        LocalDateTime createdAt
) {

    public static ArticleResponse of(Long id, String author, String title, String content, Boolean adm, String password, LocalDateTime createdAt) {
        return new ArticleResponse(id, author, title, content, adm, password,  createdAt);
    }

    public static ArticleResponse from(ArticleDto dto) {

        return new ArticleResponse(
                dto.id(),
                dto.author(),
                dto.title(),
                dto.content(),
                dto.adm(),
                dto.password(),
                dto.createdAt()
        );
    }

}

package com.keyworld.projectboard.dto.request;

import com.keyworld.projectboard.dto.ArticleDto;

public record ArticleRequest(
        String author,
        String title,
        String content
) {

    public static ArticleRequest of(String author, String title, String content) {
        return new ArticleRequest(author, title, content);
    }

    public ArticleDto toDto() {
        return ArticleDto.of(
                author,
                title,
                content
        );
    }
}

package com.keyworld.projectboard.dto.request;

import com.keyworld.projectboard.dto.ArticleDto;

public record ArticleRequest(
        String author,
        String title,
        String content,

        String password
) {

    public static ArticleRequest of(String author, String title, String content, String password) {
        return new ArticleRequest(author, title, content, password);
    }

    public ArticleDto toDto() {
        return ArticleDto.of(
                author,
                title,
                content,
                password
        );
    }
}

package com.keyworld.projectboard.dto.request;

import com.keyworld.projectboard.dto.ArticleDTO;

public record ArticleRequest(
        String author,
        String title,
        String content,

        String password,
        Boolean adm


) {

    public static ArticleRequest of(String author, String title, String content, String password, Boolean adm) {
        return new ArticleRequest(author, title, content, password, adm);
    }

    public ArticleDTO toDto() {
        return ArticleDTO.of(
                author,
                title,
                content,
                password,
                adm
        );
    }
}

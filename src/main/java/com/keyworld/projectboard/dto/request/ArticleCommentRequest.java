package com.keyworld.projectboard.dto.request;

import com.keyworld.projectboard.dto.ArticleCommentDto;
import com.keyworld.projectboard.dto.ArticleDto;

public record ArticleCommentRequest(
        Long articleId,
        String content,
        Boolean admin
) {

    public static ArticleCommentRequest of(Long articleId, String content, Boolean admin) {
        return ArticleCommentRequest.of(articleId, content, admin);
    }


    public ArticleCommentDto toDto() {
        return ArticleCommentDto.of(
                articleId,
                content,
                admin
        );
    }



}

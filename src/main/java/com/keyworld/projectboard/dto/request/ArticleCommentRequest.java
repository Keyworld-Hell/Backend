package com.keyworld.projectboard.dto.request;

import com.keyworld.projectboard.dto.ArticleCommentDto;
import com.keyworld.projectboard.dto.ArticleDto;

public record ArticleCommentRequest(
        Long articleId,
        Long parentCommentId,
        String author,
        String content
) {

    public static ArticleCommentRequest of(Long articleId, String author,  String content) {
        return ArticleCommentRequest.of(articleId, null,author, content);
    }

    public static ArticleCommentRequest of(Long articleId, Long parentCommentId, String author, String content) {
        return new ArticleCommentRequest(articleId, parentCommentId, author, content);
    }

    public ArticleCommentDto toDto() {
        return ArticleCommentDto.of(
                articleId,
                author,
                content
        );
    }



}

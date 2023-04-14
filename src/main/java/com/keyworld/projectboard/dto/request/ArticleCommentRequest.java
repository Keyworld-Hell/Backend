package com.keyworld.projectboard.dto.request;

import com.keyworld.projectboard.dto.ArticleCommentDTO;

public record ArticleCommentRequest(
        Long articleId,
        String content,
        Boolean admin
) {

    public static ArticleCommentRequest of(Long articleId, String content, Boolean admin) {
        return ArticleCommentRequest.of(articleId, content, admin);
    }


    public ArticleCommentDTO toDto() {
        return ArticleCommentDTO.of(
                articleId,
                content,
                admin
        );
    }



}

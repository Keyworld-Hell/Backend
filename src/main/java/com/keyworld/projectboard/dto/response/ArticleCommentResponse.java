package com.keyworld.projectboard.dto.response;

import com.keyworld.projectboard.dto.ArticleCommentDTO;

public record ArticleCommentResponse(
        Long id,
        String content,
        Boolean adm
) {

    public static ArticleCommentResponse of(Long id, String content, Boolean adm) {
        return new ArticleCommentResponse(id, content, adm);
    }


    public static ArticleCommentResponse from(ArticleCommentDTO dto) {
        return ArticleCommentResponse.of(
                dto.id(),
                dto.content(),
                dto.adm()
        );
    }

}

package com.keyworld.projectboard.dto.response;

import com.keyworld.projectboard.dto.ArticleCommentDto;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public record ArticleCommentResponse(
        Long id,
        String content,
        Boolean adm,
        LocalDateTime createdAt
) {

    public static ArticleCommentResponse of(Long id, String content, Boolean adm, LocalDateTime createdAt) {
        return new ArticleCommentResponse(id, content, adm, createdAt);
    }


    public static ArticleCommentResponse from(ArticleCommentDto dto) {
        return ArticleCommentResponse.of(
                dto.id(),
                dto.content(),
                dto.adm(),
                dto.createdAt()
        );
    }

}

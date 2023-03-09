package com.keyworld.projectboard.dto.response;

import com.keyworld.projectboard.dto.ArticleCommentDto;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public record ArticleCommentResponse(
        Long id,
        String content,
        LocalDateTime createdAt
) {

    public static ArticleCommentResponse of(Long id, String content, LocalDateTime createdAt) {
        return new ArticleCommentResponse(id, content, createdAt);
    }


    public static ArticleCommentResponse from(ArticleCommentDto dto) {
        return ArticleCommentResponse.of(
                dto.id(),
                dto.content(),
                dto.createdAt()
        );
    }

}

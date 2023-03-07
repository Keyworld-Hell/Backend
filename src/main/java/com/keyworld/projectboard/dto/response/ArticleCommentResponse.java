package com.keyworld.projectboard.dto.response;

import com.keyworld.projectboard.dto.ArticleCommentDto;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public record ArticleCommentResponse(
        Long id,
        String author,
        String content,
        LocalDateTime createdAt,
        Long parentCommentId,
        Set<ArticleCommentResponse> childComments
) {

    public static ArticleCommentResponse of(Long id, String author, String content, LocalDateTime createdAt) {
        return ArticleCommentResponse.of(id, author, content, createdAt, null);
    }

    public static ArticleCommentResponse of(Long id, String author, String content, LocalDateTime createdAt, Long parentCommentId) {
        Comparator<ArticleCommentResponse> childCommentComparator = Comparator
                .comparing(ArticleCommentResponse::createdAt)
                .thenComparingLong(ArticleCommentResponse::id);
        return new ArticleCommentResponse(id, author, content, createdAt, parentCommentId, new TreeSet<>(childCommentComparator));
    }

    public static ArticleCommentResponse from(ArticleCommentDto dto) {

        return ArticleCommentResponse.of(
                dto.id(),
                dto.author(),
                dto.content(),
                dto.createdAt(),
                dto.parentCommentId()
        );
    }

    public boolean hasParentComment() {
        return parentCommentId != null;
    }

}

package com.keyworld.projectboard.dto;

import com.keyworld.projectboard.domain.Article;
import com.keyworld.projectboard.domain.ArticleComment;

import java.time.LocalDateTime;

public record ArticleCommentDto(
        Long id,
        Long articleId,
        Long parentCommentId,
        String author,
        String content,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {

    public static ArticleCommentDto of(Long articleId, String author, String content) {
        return ArticleCommentDto.of(articleId,null,  author, content);
    }

    public static ArticleCommentDto of(Long articleId, Long parentCommentId, String author, String content) {
        return ArticleCommentDto.of(null, articleId, parentCommentId, author, content, null, null);
    }

    public static ArticleCommentDto of(Long id, Long articleId, Long parentCommentId, String author, String content, LocalDateTime createdAt,LocalDateTime modifiedAt) {
        return new ArticleCommentDto(id, articleId, parentCommentId, author,  content, createdAt, modifiedAt);
    }

    public static ArticleCommentDto from(ArticleComment entity) {
        return new ArticleCommentDto(
                entity.getId(),
                entity.getArticle().getId(),
                entity.getParentCommentId(),
                entity.getAuthor(),
                entity.getContent(),
                entity.getCreatedAt(),
                entity.getModifiedAt()
        );
    }

    public ArticleComment toEntity(Article article) {
        return ArticleComment.of(
                article,
                author,
                content
        );
    }

}

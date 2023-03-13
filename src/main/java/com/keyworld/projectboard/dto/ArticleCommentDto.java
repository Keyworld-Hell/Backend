package com.keyworld.projectboard.dto;

import com.keyworld.projectboard.domain.Article;
import com.keyworld.projectboard.domain.ArticleComment;

import java.time.LocalDateTime;

public record ArticleCommentDto(
        Long id,
        Long articleId,
        String content,
        Boolean adm,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {

    public ArticleCommentDto of(String content, Boolean adm) {
        return ArticleCommentDto.of(articleId, content, adm);
    }

    public static ArticleCommentDto of(Long articleId, String content, Boolean adm) {
        return ArticleCommentDto.of(null, articleId, content, adm, null, null);
    }

    public static ArticleCommentDto of(Long id, Long articleId, String content, Boolean adm, LocalDateTime createdAt,LocalDateTime modifiedAt) {
        return new ArticleCommentDto(id, articleId,  content, adm, createdAt, modifiedAt);
    }

    public static ArticleCommentDto from(ArticleComment entity) {
        return new ArticleCommentDto(
                entity.getId(),
                entity.getArticle().getId(),
                entity.getContent(),
                entity.getAdm(),
                entity.getCreatedAt(),
                entity.getModifiedAt()
        );
    }

    public ArticleComment toEntity(Article article) {
        return ArticleComment.of(
                article,
                content,
                adm
        );
    }

}

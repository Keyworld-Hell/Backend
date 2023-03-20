package com.keyworld.projectboard.dto;

import com.keyworld.projectboard.domain.Article;
import com.keyworld.projectboard.domain.ArticleComment;

import java.time.LocalDateTime;

public record ArticleCommentDto(
        Long id,
        Long articleId,
        String content,
        Boolean adm
) {

    public ArticleCommentDto of(String content, Boolean adm) {
        return ArticleCommentDto.of(articleId, content, adm);
    }

    public static ArticleCommentDto of(Long articleId, String content, Boolean adm) {
        return ArticleCommentDto.of(null, articleId, content, adm);
    }

    public static ArticleCommentDto of(Long id, Long articleId, String content, Boolean adm) {
        return new ArticleCommentDto(id, articleId,  content, adm);
    }

    public static ArticleCommentDto from(ArticleComment entity) {
        return new ArticleCommentDto(
                entity.getId(),
                entity.getArticle().getId(),
                entity.getContent(),
                entity.getAdm()
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

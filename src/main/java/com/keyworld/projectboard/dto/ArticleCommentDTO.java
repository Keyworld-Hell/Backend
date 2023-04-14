package com.keyworld.projectboard.dto;

import com.keyworld.projectboard.domain.Article;
import com.keyworld.projectboard.domain.ArticleComment;

public record ArticleCommentDTO(
        Long id,
        Long articleId,
        String content,
        Boolean adm
) {

    public ArticleCommentDTO of(String content, Boolean adm) {
        return ArticleCommentDTO.of(articleId, content, adm);
    }

    public static ArticleCommentDTO of(Long articleId, String content, Boolean adm) {
        return ArticleCommentDTO.of(null, articleId, content, adm);
    }

    public static ArticleCommentDTO of(Long id, Long articleId, String content, Boolean adm) {
        return new ArticleCommentDTO(id, articleId,  content, adm);
    }

    public static ArticleCommentDTO from(ArticleComment entity) {
        return new ArticleCommentDTO(
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

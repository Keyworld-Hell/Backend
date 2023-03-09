package com.keyworld.projectboard.dto;

import com.keyworld.projectboard.domain.Article;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public record ArticleWithCommentsDto(
        Long id,

        Set<ArticleCommentDto> articleCommentDtos,
        String author,
        String title,

        String content,
        String password,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
    public static ArticleWithCommentsDto of(Long id,  Set<ArticleCommentDto> articleCommentDtos, String author, String title, String content, String password, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        return new ArticleWithCommentsDto(id, articleCommentDtos, author, title, content, password, createdAt, modifiedAt);
    }

    public static ArticleWithCommentsDto from(Article entity) {
        return new ArticleWithCommentsDto(
                entity.getId(),
                entity.getArticleComments().stream()
                        .map(ArticleCommentDto::from)
                        .collect(Collectors.toCollection(LinkedHashSet::new))
                ,
                entity.getAuthor(),
                entity.getTitle(),
                entity.getContent(),
                entity.getPassword(),
                entity.getCreatedAt(),
                entity.getModifiedAt()
        );
    }

}

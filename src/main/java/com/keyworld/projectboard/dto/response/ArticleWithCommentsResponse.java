package com.keyworld.projectboard.dto.response;

import com.keyworld.projectboard.dto.ArticleCommentDto;
import com.keyworld.projectboard.dto.ArticleWithCommentsDto;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public record ArticleWithCommentsResponse(
        Long id,
        String author,
        String title,
        String content,
        String password,
        LocalDateTime createdAt,
        Set<ArticleCommentResponse> articleCommentsResponse
) {

    public static ArticleWithCommentsResponse of(Long id, String author, String title, String content, String password, LocalDateTime createdAt,String userId, Set<ArticleCommentResponse> articleCommentResponses) {
        return new ArticleWithCommentsResponse(id, author, title, content,password, createdAt, articleCommentResponses);
    }

    public static ArticleWithCommentsResponse from(ArticleWithCommentsDto dto) {

        return new ArticleWithCommentsResponse(
                dto.id(),
                dto.author(),
                dto.title(),
                dto.content(),
                dto.password(),
                dto.createdAt(),
                organizeChildComments(dto.articleCommentDtos())
        );
    }


    private static Set<ArticleCommentResponse> organizeChildComments(Set<ArticleCommentDto> dtos) {
        Map<Long, ArticleCommentResponse> map = dtos.stream()
                .map(ArticleCommentResponse::from)
                .collect(Collectors.toMap(ArticleCommentResponse::id, Function.identity()));

        map.values().stream()
                .filter(ArticleCommentResponse::hasParentComment)
                .forEach(comment -> {
                    ArticleCommentResponse parentComment = map.get(comment.parentCommentId());
                    parentComment.childComments().add(comment);
                });

        return map.values().stream()
                .filter(comment -> !comment.hasParentComment())
                .collect(Collectors.toCollection(() ->
                        new TreeSet<>(Comparator
                                .comparing(ArticleCommentResponse::createdAt)
                                .reversed()
                                .thenComparingLong(ArticleCommentResponse::id)
                        )
                ));
    }
}

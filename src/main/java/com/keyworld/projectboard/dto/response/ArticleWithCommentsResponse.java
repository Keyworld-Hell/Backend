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
        Boolean adm,
        Set<ArticleCommentResponse> articleCommentsResponse
) {

    public static ArticleWithCommentsResponse of(Long id, String author, String title, String content, String password, Boolean adm, Set<ArticleCommentResponse> articleCommentResponses) {
        return new ArticleWithCommentsResponse(id, author, title, content, password, adm, articleCommentResponses);
    }

    public static ArticleWithCommentsResponse from(ArticleWithCommentsDto dto) {

        return new ArticleWithCommentsResponse(
                dto.id(),
                dto.author(),
                dto.title(),
                dto.content(),
                dto.password(),
                dto.adm(),
                organizeChildComments(dto.articleCommentDtos())
        );
    }


    private static Set<ArticleCommentResponse> organizeChildComments(Set<ArticleCommentDto> dtos) {
        Map<Long, ArticleCommentResponse> map = dtos.stream()
                .map(ArticleCommentResponse::from)
                .collect(Collectors.toMap(ArticleCommentResponse::id, Function.identity()));
        return new TreeSet<>(map.values());
    }
}

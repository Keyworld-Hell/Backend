package com.keyworld.projectboard.controller;

import com.keyworld.projectboard.dto.request.ArticleCommentRequest;
import com.keyworld.projectboard.service.ArticleCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/")
@RestController
public class ArticleCommentController {

    private final ArticleCommentService articleCommentService;

    @PostMapping("/board/comment/new")
    public void postNewArticleComment(
            @RequestBody ArticleCommentRequest articleCommentRequest
    ) {
        articleCommentService.saveArticleComment(articleCommentRequest.toDto());
    }

    @DeleteMapping("/adm/board/comment/delete/{commentId}")
    public void deleteArticleComment(
            @PathVariable Long commentId
    ) {
        articleCommentService.deleteArticleComment(commentId);
    }

}
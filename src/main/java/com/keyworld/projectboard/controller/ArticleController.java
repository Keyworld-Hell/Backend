package com.keyworld.projectboard.controller;

import com.keyworld.projectboard.domain.constant.FormStatus;
import com.keyworld.projectboard.domain.constant.SearchType;
import com.keyworld.projectboard.dto.request.ArticleRequest;
import com.keyworld.projectboard.dto.response.ArticleResponse;
import com.keyworld.projectboard.dto.response.ArticleWithCommentsResponse;
import com.keyworld.projectboard.service.ArticleService;
import com.keyworld.projectboard.service.PaginationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/")
@RestController
public class ArticleController {

    private final ArticleService articleService;
    private final PaginationService paginationService;

    @GetMapping("/board")
    public Page<ArticleResponse> articles(
            @RequestParam(required = false) SearchType searchType,
            @RequestParam(required = false) String searchValue,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return articleService.searchArticles(searchType, searchValue, pageable).map(ArticleResponse::from);
    }

    @GetMapping("/board/{articleId}")
    public ArticleWithCommentsResponse article(@PathVariable Long articleId) {
        return ArticleWithCommentsResponse.from(articleService.getArticleWithComments(articleId));
    }

    @PostMapping("/board/new")
    public ArticleResponse postNewArticle(@ModelAttribute ArticleRequest articleRequest) {
        return ArticleResponse.from(articleService.saveArticle(articleRequest.toDto()));
    }

    @PutMapping("/adm/board/{articleId}")
    public ArticleResponse updateArticle(@PathVariable Long articleId, @ModelAttribute ArticleRequest articleRequest) {
        return ArticleResponse.from(articleService.updateArticle(articleId, articleRequest.toDto()));
    }

    @DeleteMapping("/adm/board/delete/{articleId}")
    public void deleteArticle(@PathVariable Long articleId) {
        articleService.deleteArticle(articleId);
    }

}
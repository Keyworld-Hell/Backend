package com.keyworld.projectboard.service;

import com.keyworld.projectboard.domain.Article;
import com.keyworld.projectboard.domain.constant.SearchType;
import com.keyworld.projectboard.dto.ArticleDTO;
import com.keyworld.projectboard.dto.ArticleWithCommentsDto;
import com.keyworld.projectboard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public Page<ArticleDTO> searchArticles(SearchType searchType, String searchKeyword, Pageable pageable) {
        if (searchKeyword == null || searchKeyword.isBlank()) {
            return articleRepository.findAll(pageable).map(ArticleDTO::from);
        }

        return switch (searchType) {
            case AUTHOR -> articleRepository.findByAuthorContaining(searchKeyword, pageable).map(ArticleDTO::from);
            case TITLE -> articleRepository.findByTitleContaining(searchKeyword, pageable).map(ArticleDTO::from);
            case CONTENT -> articleRepository.findByContentContaining(searchKeyword, pageable).map(ArticleDTO::from);
        };
    }

    @Transactional(readOnly = true)
    public ArticleWithCommentsDto getArticleWithComments(Long articleId) {
        return articleRepository.findById(articleId)
                .map(ArticleWithCommentsDto::from)
                .orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다 - articleId: " + articleId));
    }

    @Transactional(readOnly = true)
    public ArticleDTO getArticle(Long articleId) {
        return articleRepository.findById(articleId)
                .map(ArticleDTO::from)
                .orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다 - articleId: " + articleId));
    }

    public ArticleDTO saveArticle(ArticleDTO dto) {
        Article article = dto.toEntity();
        articleRepository.save(article);
        return dto;
    }

    public ArticleDTO updateArticle(Long articleId, ArticleDTO dto) {
        try {
            Article article = articleRepository.getReferenceById(articleId);
            if (dto.author() != null) {
                article.setAuthor(dto.author());
            }
            if (dto.title() != null) {
                article.setTitle(dto.title());
            }
            if (dto.content() != null) {
                article.setContent(dto.content());
            }

            articleRepository.flush();

        } catch (EntityNotFoundException e) {
            log.warn("게시글 업데이트 실패. 게시글을 수정하는데 필요한 정보를 찾을 수 없습니다 - {}", e.getLocalizedMessage());
        }
        return dto;
    }

    public void deleteArticle(long articleId) {
        Article article = articleRepository.getReferenceById(articleId);

        articleRepository.deleteById(articleId);
        articleRepository.flush();
    }

    public long getArticleCount() {
        return articleRepository.count();
    }


}

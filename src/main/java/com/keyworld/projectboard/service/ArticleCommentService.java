package com.keyworld.projectboard.service;

import com.keyworld.projectboard.domain.Article;
import com.keyworld.projectboard.domain.ArticleComment;
import com.keyworld.projectboard.dto.ArticleCommentDTO;
import com.keyworld.projectboard.repository.ArticleCommentRepository;
import com.keyworld.projectboard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ArticleCommentService {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    @Transactional(readOnly = true)
    public List<ArticleCommentDTO> searchArticleComments(Long articleId) {
        return articleCommentRepository.findByArticle_Id(articleId)
                .stream()
                .map(ArticleCommentDTO::from)
                .toList();
    }

    public void saveArticleComment(ArticleCommentDTO dto) {
        try {
            Article article = articleRepository.getReferenceById(dto.articleId());
            ArticleComment articleComment = dto.toEntity(article);

            articleCommentRepository.save(articleComment);

        } catch (EntityNotFoundException e) {
            log.warn("댓글 저장 실패. 댓글 작성에 필요한 정보를 찾을 수 없습니다 - {}", e.getLocalizedMessage());
        }
    }

    public void deleteArticleComment(Long articleCommentId) {
        articleCommentRepository.deleteById(articleCommentId);
    }

}

package com.keyworld.projectboard.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "createdAt"),
})
@Entity
public class ArticleComment extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    @Setter @Column(nullable = false, length = 500) private String content; // 본문

    @Setter @Column(nullable = false) private Boolean adm;

    protected ArticleComment() {}

    private ArticleComment(Article article, String content, Boolean adm) {
        this.article = article;
        this.content = content;
        this.adm = adm;
    }

    public static ArticleComment of(Article article, String content, Boolean adm) {
        return new ArticleComment(article, content, adm);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticleComment that)) return false;
        return this.getId() != null && this.getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }

}

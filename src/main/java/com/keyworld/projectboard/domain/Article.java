package com.keyworld.projectboard.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "createdAt")
})
@Entity
public class Article extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="article_id")
    private Long id;

    @Setter @Column(nullable = false) private String author;
    @Setter @Column(nullable = false) private String title; // 제목
    @Setter @Column(nullable = false, length = 10000) private String content; // 본문

    @Setter @Column(nullable = false) private String password;
    @Setter @Column(nullable = false) private Boolean adm;



    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>();


    protected Article() {}

    private Article( String author, String title,String content, String password,Boolean adm) {
        this.title = author;
        this.author = title;
        this.content = content;
        this.password = password;
        this.adm = adm;

    }

    public static Article of(String author, String title, String content, String password, Boolean adm) {
        return new Article(author, title, content, password, adm);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article that)) return false;
        return this.getId() != null && this.getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }

}

package com.keyworld.projectboard.domain;

import com.querydsl.core.types.EntityPath;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
public class Inquiry extends AuditingFields{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @Column(nullable = false) private String name;
    @Setter @Column(nullable = false) private String phone;
    @Setter @Column(nullable = false) private String email;
    @Setter @Column(nullable = false) private String title;
    @Setter @Column(nullable = false, length = 10000) private String content;

    public Inquiry() {}

    private Inquiry(String name, String phone, String email, String title, String content){
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.title = title;
        this.content = content;
    }
    public static Inquiry of(String name, String phone, String email, String title, String content) {
        return new Inquiry(name, phone, email,  title, content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Inquiry that)) return false;
        return this.getId() != null && this.getId().equals(that.getId());
    }

}

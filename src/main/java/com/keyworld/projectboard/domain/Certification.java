package com.keyworld.projectboard.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Entity(name="Certification")
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name="CERTIFICATION")
public class Certification extends BaseTime {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="certification_id")
    private Long id;
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Boolean language;

    @Lob
    @Column(name = "file")
    private byte[] file;

    public Certification(String title, byte[] file) {
        this.title = title;
        this.file = file;
    }
}


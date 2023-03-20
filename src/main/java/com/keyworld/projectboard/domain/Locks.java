package com.keyworld.projectboard.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.File;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity(name = "Locks")
@Table(name = "LOCKS")
public class Locks extends AuditingFields{
    /**
     * 제품 이름, 제품 사진, 재질, 표면 처리, 용도, 특징, 도면 사진, 도면 첨부파일
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="lock_id")
    private Long id;

    @Column(nullable = false)
    private Boolean language;

    @Column(nullable = false)
    private Long number;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String material;

    @Column(nullable = false)
    private String surface;

    @Column(nullable = false, length = 1000)
    private String purpose;

    @Column(nullable = false, length = 1000)
    private String feature;

    @Transient
    private File cam;

    @Transient
    private File dwg;

    @Transient
    private List<File> fileList;

    public Locks(Boolean language, Long number, String title, String material, String surface, String purpose, String feature, File cam, File dwg, List<File> fileList) {
        this.language = language;
        this.number = number;
        this.title = title;
        this.material = material;
        this.surface = surface;
        this.purpose = purpose;
        this.feature = feature;
        this.cam = cam;
        this.dwg = dwg;
        this.fileList = fileList;
    }
}
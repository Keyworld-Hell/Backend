package com.keyworld.projectboard.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity(name = "Lock")
@Table(name = "LOCK")
public class Lock {
    /**
     * 제품 이름, 제품 사진, 재질, 표면 처리, 용도, 특징, 도면 사진, 도면 첨부파일
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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


    public Lock(Boolean language, Long number, String title, String material, String surface, String purpose, String feature, byte[] cam, byte[] dwg, byte[] dwgDown, byte[] stepDown, byte[] pdfDown) {
        this.language = language;
        this.number = number;
        this.title = title;
        this.material = material;
        this.surface = surface;
        this.purpose = purpose;
        this.feature = feature;
    }
}

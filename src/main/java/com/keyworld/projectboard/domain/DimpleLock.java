package com.keyworld.projectboard.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity(name = "DimpleLock")
@Table(name = "DIMPLELOCK")
public class DimpleLock {
    /**
     * 제품 이름, 제품 사진, 재질, 표면 처리, 용도, 특징, 도면 사진, 도면 첨부파일
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @Lob
    @Column(nullable = false)
    private byte[] Cam;

    @Lob
    @Column(nullable = false)
    private byte[] Dwg;

    @Lob
    @Column
    private byte[] DwgDown;

    @Lob
    @Column
    private byte[] StepDown;

    @Lob
    @Column
    private byte[] PdfDown;

    public DimpleLock(String title, String material, String surface, String purpose, String feature, byte[] cam, byte[] dwg, byte[] dwgDown, byte[] stepDown, byte[] pdfDown) {
        this.title = title;
        this.material = material;
        this.surface = surface;
        this.purpose = purpose;
        this.feature = feature;
        Cam = cam;
        Dwg = dwg;
        DwgDown = dwgDown;
        StepDown = stepDown;
        PdfDown = pdfDown;
    }
}

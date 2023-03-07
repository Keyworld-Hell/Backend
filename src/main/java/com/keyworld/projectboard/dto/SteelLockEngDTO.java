package com.keyworld.projectboard.dto;

import org.springframework.web.multipart.MultipartFile;

public class SteelLockEngDTO {
    private String title;
    private String material;
    private String surface;
    private String purpose;
    private String feature;
    private MultipartFile cam;
    private MultipartFile dwg;
    private MultipartFile dwgDown;
    private MultipartFile stepDown;
    private MultipartFile pdfDown;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getSurface() {
        return surface;
    }

    public void setSurface(String surface) {
        this.surface = surface;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public MultipartFile getCam() {
        return cam;
    }

    public void setCam(MultipartFile cam) {
        this.cam = cam;
    }

    public MultipartFile getDwg() {
        return dwg;
    }

    public void setDwg(MultipartFile dwg) {
        this.dwg = dwg;
    }

    public MultipartFile getDwgDown() {
        return dwgDown;
    }

    public void setDwgDown(MultipartFile dwgDown) {
        this.dwgDown = dwgDown;
    }

    public MultipartFile getStepDown() {
        return stepDown;
    }

    public void setStepDown(MultipartFile stepDown) {
        this.stepDown = stepDown;
    }

    public MultipartFile getPdfDown() {
        return pdfDown;
    }

    public void setPdfDown(MultipartFile pdfDown) {
        this.pdfDown = pdfDown;
    }
}

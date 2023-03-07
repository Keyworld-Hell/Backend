package com.keyworld.projectboard.dto;

import org.springframework.web.multipart.MultipartFile;

public class LockDTO {
    private Boolean language;
    private Long number;
    private String title;
    private String material;
    private String surface;
    private String purpose;
    private String feature;

    public Boolean getLanguage() { return language; }

    public void setLanguage(Boolean language){ this.language = language; }

    public Long getNumber() { return number; }

    public void setNumber(Long number) { this.number = number; }

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

}

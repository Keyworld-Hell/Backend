package com.keyworld.projectboard.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class LockDTO {
    private Boolean language;
    private Long number;
    private String title;
    private String material;
    private String surface;
    private String purpose;
    private String feature;

    private String description;

    private MultipartFile file1;
    private MultipartFile file2;
    private List<MultipartFile> files;

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

    public MultipartFile getFile1() {
        return file1;
    }

    public void setFile1(MultipartFile file1) {
        this.file1 = file1;
    }

    public MultipartFile getFile2() {
        return file2;
    }

    public void setFile2(MultipartFile file2) {
        this.file2 = file2;
    }

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

package com.keyworld.projectboard.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;



import org.springframework.web.multipart.MultipartFile;

public class CertificationDTO {
    private String title;
    private MultipartFile file;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}

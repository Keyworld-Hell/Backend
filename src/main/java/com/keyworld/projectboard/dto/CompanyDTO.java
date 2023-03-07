package com.keyworld.projectboard.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class CompanyDTO {
    private String title;
    private MultipartFile file;

    public CompanyDTO(String title, MultipartFile file) {
        this.title = title;
        this.file = file;
    }
}

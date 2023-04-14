package com.keyworld.projectboard.dto;

import com.keyworld.projectboard.domain.Inquiry;

import java.time.LocalDateTime;

public record InquiryDTO(
        Long id,
        String name,
        String phone,
        String email,
        String title,
        String content,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt

) {
    public Inquiry toEntity() {
        return Inquiry.of(
                name,
                phone,
                email,
                title,
                content
        );
    }






    public static InquiryDTO of(String name, String phone, String email, String title, String content){
        return new InquiryDTO(null, name, phone, email, title, content, null,null);
    }
    public static InquiryDTO of(Long id, String name, String phone, String email, String title, String content){
        return new InquiryDTO(id, name, phone, email, title, content,null,null);
    }
    public static InquiryDTO from(Inquiry entity){
        return new InquiryDTO(
                entity.getId(),
                entity.getName(),
                entity.getPhone(),
                entity.getEmail(),
                entity.getTitle(),
                entity.getContent(),
                entity.getCreatedAt(),
                entity.getModifiedAt()
        );
    }

}
package com.keyworld.projectboard.dto;

import com.keyworld.projectboard.domain.Inquiry;

import java.time.LocalDateTime;

public record InquiryDto(
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






    public static InquiryDto of(String name, String phone, String email, String title, String content){
        return new InquiryDto(null, name, phone, email, title, content, null,null);
    }
    public static InquiryDto of(Long id, String name, String phone, String email, String title, String content){
        return new InquiryDto(id, name, phone, email, title, content,null,null);
    }
    public static InquiryDto from(Inquiry entity){
        return new InquiryDto(
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
package com.keyworld.projectboard.dto.response;

import com.keyworld.projectboard.dto.InquiryDto;
import com.keyworld.projectboard.repository.InquiryRepository;

import java.time.LocalDateTime;

public record InquiryResponse (
        Long id,
        String name,
        String phone,
        String email,
        String title,
        String content,
        LocalDateTime createdAt
){
    public static InquiryResponse of(Long id, String name, String phone, String email, String title, String content, LocalDateTime createdAt){
        return new InquiryResponse(id, name, phone, email, title, content, createdAt);
    }

    public static InquiryResponse from(InquiryDto dto){
        return new InquiryResponse(
                dto.id(),
                dto.name(),
                dto.phone(),
                dto.email(),
                dto.title(),
                dto.content(),
                dto.createdAt()
        );
    }
}

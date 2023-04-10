package com.keyworld.projectboard.dto.request;

import com.keyworld.projectboard.domain.Inquiry;
import com.keyworld.projectboard.dto.ArticleDto;
import com.keyworld.projectboard.dto.InquiryDto;

public record InquiryRequest(
        String name,
        String phone,
        String email,
        String title,
        String content
){
    public static InquiryRequest of(String name, String phone, String email, String title, String content) { return new InquiryRequest(name, phone, email, title,content);}

    public InquiryDto toDto(InquiryDto inquiryDto){ return toDto(inquiryDto);}
    public InquiryDto toDto() {
        return InquiryDto.of(
                name,
                phone,
                email,
                title,
                content
        );
    }

}

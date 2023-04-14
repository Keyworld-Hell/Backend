package com.keyworld.projectboard.dto.request;

import com.keyworld.projectboard.dto.InquiryDTO;

public record InquiryRequest(
        String name,
        String phone,
        String email,
        String title,
        String content
){
    public static InquiryRequest of(String name, String phone, String email, String title, String content) { return new InquiryRequest(name, phone, email, title,content);}

    public InquiryDTO toDto(InquiryDTO inquiryDto){ return toDto(inquiryDto);}
    public InquiryDTO toDto() {
        return InquiryDTO.of(
                name,
                phone,
                email,
                title,
                content
        );
    }

}

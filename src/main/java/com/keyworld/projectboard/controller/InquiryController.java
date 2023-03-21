package com.keyworld.projectboard.controller;

import com.keyworld.projectboard.domain.constant.SearchType;
import com.keyworld.projectboard.dto.request.InquiryRequest;
import com.keyworld.projectboard.dto.response.InquiryResponse;
import com.keyworld.projectboard.service.InquiryService;
import com.keyworld.projectboard.service.PaginationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/")
@RestController
public class InquiryController {

    private final InquiryService inquiryService;

    private final PaginationService paginationService;

    @GetMapping("/inquiry")
    public Page<InquiryResponse> inquiries(
            @RequestParam(required = false) SearchType searchType,
            @RequestParam(required = false) String searchValue,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return inquiryService.searchInquiries(searchType, searchValue, pageable).map(InquiryResponse::from);
    }

    @GetMapping("/inquiry/{inquiryId}")
    public InquiryResponse inquiry(@PathVariable Long inquiryId) {
        return InquiryResponse.from(inquiryService.getInquiry(inquiryId));
    }

    @PostMapping("/inquiry/new")
    public InquiryResponse postNewInquiry(@RequestParam InquiryRequest inquiryRequest) {
        return InquiryResponse.from(inquiryService.saveInquiry(inquiryRequest.toDto()));
    }

    @DeleteMapping("/adm/inquiry/delete/{inquiryId}")
    public void deleteInquiry(
            @PathVariable Long inquiryId
    ) {
        inquiryService.deleteInquiry(inquiryId);
    }
}

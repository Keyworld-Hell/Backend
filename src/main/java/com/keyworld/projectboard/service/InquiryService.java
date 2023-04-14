package com.keyworld.projectboard.service;

import com.keyworld.projectboard.domain.*;
import com.keyworld.projectboard.domain.constant.SearchType;
import com.keyworld.projectboard.dto.InquiryDTO;
import com.keyworld.projectboard.repository.InquiryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class InquiryService {
    private final InquiryRepository inquiryRepository;


    @Transactional(readOnly = true)
    public Page<InquiryDTO> searchInquiries(SearchType searchType, String searchKeyword, Pageable pageable){
        if (searchKeyword == null || searchKeyword.isBlank()) {
            return inquiryRepository.findAll(pageable).map(InquiryDTO::from);
        }
        return switch (searchType) {
            case AUTHOR -> null;
            case TITLE -> inquiryRepository.findByTitleContaining(searchKeyword, pageable).map(InquiryDTO::from);
            case CONTENT -> inquiryRepository.findByContentContaining(searchKeyword, pageable).map(InquiryDTO::from);

        };
    }

    @Transactional(readOnly = true)
    public InquiryDTO getInquiry(Long inquiryId) {
        return inquiryRepository.findById(inquiryId)
                .map(InquiryDTO::from)
                .orElseThrow(() -> new EntityNotFoundException("문의가 없습니다 - inquiryId: " + inquiryId));
    }

    public InquiryDTO saveInquiry(InquiryDTO dto) {
        Inquiry inquiry = dto.toEntity();
        inquiryRepository.save(inquiry);
        return InquiryDTO.from(inquiry);
    }


    public InquiryDTO updateInquiry(Long inquiryId, InquiryDTO dto) {
        Inquiry inquiry = inquiryRepository.findById(inquiryId)
                .orElseThrow(() -> new EntityNotFoundException("Inquiry not found - inquiryId: " + inquiryId));

        if (dto.title() != null) {
            inquiry.setTitle(dto.title());
        }
        if(dto.name() != null){
            inquiry.setName(dto.name());
        }
        if(dto.email() != null){
            inquiry.setEmail(dto.email());
        }
        if(dto.phone() != null){
            inquiry.setPhone(dto.phone());
        }
        if (dto.content() != null) {
            inquiry.setContent(dto.content());
        }

        inquiryRepository.save(inquiry);
        return InquiryDTO.from(inquiry);
    }


        public void deleteInquiry(long inquiryId) {
            Inquiry inquiry = inquiryRepository.getReferenceById(inquiryId);
    
            inquiryRepository.deleteById(inquiryId);
            inquiryRepository.flush();
    
    
        }

    public long getInquiryCount() {
        return inquiryRepository.count();
    }

}

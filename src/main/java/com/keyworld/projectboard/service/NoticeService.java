package com.keyworld.projectboard.service;

import com.keyworld.projectboard.domain.Notice;
import com.keyworld.projectboard.dto.NoticeDto;
import com.keyworld.projectboard.repository.NoticeRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Getter

public class NoticeService {
    private final NoticeRepository noticeRepository;

    @Transactional(readOnly = true)
    public NoticeDto.Response searchById(Long id){
        Notice entity = noticeRepository.findById(id).orElseThrow(()
        ->new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));

        return new NoticeDto.Response(entity);
    }

    @Transactional(readOnly = true)
    public List<NoticeDto.ListResponse> searchAllDesc() {

        return noticeRepository.findAllByOrderByIdDesc().stream()
                .map(NoticeDto.ListResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){

        Notice notice = noticeRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));

        noticeRepository.delete(notice);
    }

    @Transactional
    public Long save(Notice notice) throws Exception {

        Notice saveNotice = getNoticeRepository().save(notice);

        return saveNotice.getId();
    }
}

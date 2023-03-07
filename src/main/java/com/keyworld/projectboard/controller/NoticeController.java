package com.keyworld.projectboard.controller;


import com.keyworld.projectboard.domain.Notice;
import com.keyworld.projectboard.dto.NoticeDTO;
import com.keyworld.projectboard.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class NoticeController {
    private final NoticeService noticeService;

    @GetMapping("/notice")
    public NoticeDTO.Response getNotice(){
        return noticeService.searchById(noticeService.getNoticeRepository().count());
    }
    @PostMapping("/admin/notice/update")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveNotice(MultipartHttpServletRequest multiRequest) throws Exception {
        Notice notice = new Notice();
        notice.setTitle(multiRequest.getParameter("title"));
        notice.setContent(multiRequest.getParameter("content"));
        noticeService.save(notice);
    }
}
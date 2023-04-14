package com.keyworld.projectboard.controller;


import com.keyworld.projectboard.domain.Notice;
import com.keyworld.projectboard.dto.NoticeDTO;
import com.keyworld.projectboard.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class NoticeController {
    private final NoticeService noticeService;
    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }
    @PostMapping("/adm/notice/new")
    public ResponseEntity<Void> createNotice(@ModelAttribute NoticeDTO noticeDto) throws IOException {
        noticeService.createNotice(noticeDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/adm/notice/{id}")
    public ResponseEntity<NoticeDTO> getNotice(@PathVariable Long id) throws IOException {
        NoticeDTO noticeDto = noticeService.getNotice(id);
        return ResponseEntity.ok(noticeDto);
    }

    @GetMapping("/notice")
    public ResponseEntity<NoticeDTO> getRecent() throws IOException{
        NoticeDTO noticeDTO =  noticeService.getNotice(noticeService.getNoticeRepository().count());
        return ResponseEntity.ok(noticeDTO);
    }

    @GetMapping("/adm/notice")
    public List<Notice> getNoticeList() throws IOException{
        return noticeService.getNoticeList();
    }

    @PutMapping("/adm/notice/update/{id}")
    public ResponseEntity<Void> updateNotice(@PathVariable Long id, @ModelAttribute NoticeDTO noticeDto) throws IOException {
        noticeService.updateNotice(id, noticeDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/adm/notice/delete/{id}")
    public ResponseEntity<Void> deleteNotice(@PathVariable Long id) {
        noticeService.deleteNotice(id);
        return ResponseEntity.ok().build();
    }
}
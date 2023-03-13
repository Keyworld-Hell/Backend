package com.keyworld.projectboard.service;

import com.keyworld.projectboard.domain.Notice;
import com.keyworld.projectboard.dto.NoticeDTO;
import com.keyworld.projectboard.repository.NoticeRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Getter
public class NoticeService {

    private final FileService fileService;

    @Autowired
    private NoticeRepository noticeRepository;

    public NoticeService(FileService fileService) {
        this.fileService = fileService;
    }

    public void createNotice(NoticeDTO noticeDto) throws IOException {
        Notice notice = new Notice();
        notice.setTitle(noticeDto.getTitle());
        notice.setContent(noticeDto.getContent());
        notice.setYear(noticeDto.getYear());
        notice.setMonth(notice.getMonth());
        notice.setDay(notice.getDay());


        if (noticeDto.getFile() != null) {
            String fileName = noticeDto.getFile().getOriginalFilename();
            String filePath = "/notices/" + fileName;
            notice.setFilePath(filePath);
            fileService.uploadFile(noticeDto.getFile(), filePath);
        }
        noticeRepository.save(notice);
        // Save the notice to the database
    }

    public NoticeDTO getNotice(Long id) throws IOException {
        // Retrieve the notice from the database
        Notice notice = noticeRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException());

        NoticeDTO noticeDto = new NoticeDTO();
        noticeDto.setTitle(notice.getTitle());
        noticeDto.setContent(notice.getContent());
        noticeDto.setYear(notice.getYear());
        noticeDto.setMonth(notice.getMonth());
        noticeDto.setDay(notice.getDay());

        if (notice.getFilePath() != null) {
            Resource fileResource = fileService.downloadFile(notice.getFilePath());
            noticeDto.setFile((MultipartFile) fileResource.getFile());
        }

        return noticeDto;
    }

    public List<Notice> getNoticeList() throws IOException{
        return noticeRepository.findAllByOrderByIdDesc();
    }

    public void updateNotice(Long id, NoticeDTO noticeDto) throws IOException {
        Notice notice = noticeRepository.findById(id).orElse(null);

        if (notice == null) {
            return;
        }

        notice.setTitle(noticeDto.getTitle());
        notice.setContent(noticeDto.getContent());
        notice.setYear(noticeDto.getYear());
        notice.setMonth(notice.getMonth());
        notice.setDay(notice.getDay());

        if (noticeDto.getFile() != null) {
            String fileName = noticeDto.getFile().getOriginalFilename();
            String filePath = "/notices/" + fileName;
            notice.setFilePath(filePath);
            fileService.uploadFile(noticeDto.getFile(), filePath);
        }

        noticeRepository.save(notice);
    }

    public void deleteNotice(Long id) {
        noticeRepository.deleteById(id);
    }
}
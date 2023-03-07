package com.keyworld.projectboard.dto;

import com.keyworld.projectboard.domain.Notice;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class NoticeDTO {

    @Getter
    @NoArgsConstructor
    public static class Response{
        private Long id;
        private String title;
        private String content;
        private LocalDateTime createdDateTime;
        public Response(Notice entity){
            this.id = entity.getId();
            this.title = entity.getTitle();
            this.content = entity.getContent();
            this.createdDateTime = entity.getCreatedDateTime();
        }
    }
    @Getter
    public static class ListResponse{
        private Long id;
        private String title;
        private String content;
        public ListResponse(Notice entity){
            this.id = entity.getId();
            this.title = entity.getTitle();
            this.content = entity.getContent();
        }
    }

}

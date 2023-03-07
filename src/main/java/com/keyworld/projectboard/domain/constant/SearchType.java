package com.keyworld.projectboard.domain.constant;

import lombok.Getter;

public enum SearchType {
    AUTHOR("글쓴이"),
    TITLE("제목"),
    CONTENT("본문");

    @Getter private final String description;

    SearchType(String description) {
        this.description = description;
    }

}

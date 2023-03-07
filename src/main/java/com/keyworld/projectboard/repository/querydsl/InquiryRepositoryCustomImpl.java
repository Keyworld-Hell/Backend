package com.keyworld.projectboard.repository.querydsl;

import com.keyworld.projectboard.domain.Inquiry;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class InquiryRepositoryCustomImpl extends QuerydslRepositorySupport implements InquiryRepositoryCustom{

    public InquiryRepositoryCustomImpl() {super(Inquiry.class);
    }
}

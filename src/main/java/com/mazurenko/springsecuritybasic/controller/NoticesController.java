package com.mazurenko.springsecuritybasic.controller;

import com.mazurenko.springsecuritybasic.entity.NoticeDetails;
import com.mazurenko.springsecuritybasic.repository.NoticeDetailsRepository;
import org.apache.coyote.Response;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class NoticesController {

    private NoticeDetailsRepository noticeDetailsRepository;

    @Autowired
    public NoticesController(NoticeDetailsRepository noticeDetailsRepository) {
        this.noticeDetailsRepository = noticeDetailsRepository;
    }

    @GetMapping("/notices")
    public ResponseEntity<List<NoticeDetails>> getNotices() {
        List<NoticeDetails> notices = noticeDetailsRepository.findAllActiveNotices();

        if (notices != null) {
            return ResponseEntity.ok()
                    .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
                    .body(notices);
        }
        return ResponseEntity
                .badRequest()
                .body(null);
    }
}

package com.mazurenko.springsecuritybasic.repository;

import com.mazurenko.springsecuritybasic.entity.NoticeDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeDetailsRepository extends CrudRepository<NoticeDetails, Long> {

    @Query("FROM NoticeDetails n WHERE current_date BETWEEN n.noticeBegDt AND n.noticeEndDt")
    List<NoticeDetails> findAllActiveNotices();
}

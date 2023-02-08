package com.mazurenko.springsecuritybasic.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name="notice_details")
public class NoticeDetails {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="notice_id")
    private Long noticeId;

    @Column(name="notice_summary")
    private String noticeSummary;

    @Column(name="notice_details")
    private String noticeDetails;

    @Column(name="notic_beg_dt")
    private Date noticeBegDt;

    @Column(name="notic_end_dt")
    private Date noticeEndDt;

    @Column(name="create_dt")
    private Date createDt;

    @Column(name="update_dt")
    private Date updateDt;
}

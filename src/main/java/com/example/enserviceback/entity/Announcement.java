package com.example.enserviceback.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String announcementTitle ;
    private String announcementBody ;
    private String announcementFilePath ;
    private String announcementSenderEmail ;
    private Date announcementIssueDate;
    private String announcementTargetCrowd ;

    @ManyToOne
    @JsonIgnore
    private Admin admin ;




}

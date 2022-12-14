package com.example.enserviceback.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementDto {

    private String announcementTitle ;
    private String announcementBody ;
    private String announcementSenderEmail ;
    private String announcementTargetCrowd ;

}

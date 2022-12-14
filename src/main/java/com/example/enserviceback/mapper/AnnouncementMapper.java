package com.example.enserviceback.mapper;

import com.example.enserviceback.dto.AnnouncementDto;
import com.example.enserviceback.entity.Announcement;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnnouncementMapper {

    Announcement announcementFromAnnouncementDto(AnnouncementDto announcementDto);

}

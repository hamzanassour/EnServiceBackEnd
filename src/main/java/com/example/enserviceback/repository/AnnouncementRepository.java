package com.example.enserviceback.repository;

import com.example.enserviceback.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement , Long> {

    List<Announcement> findByAdmin_Email(String email);
}

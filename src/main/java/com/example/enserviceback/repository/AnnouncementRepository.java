package com.example.enserviceback.repository;

import com.example.enserviceback.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementRepository extends JpaRepository<Announcement , Long> {
}

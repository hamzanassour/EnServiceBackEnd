package com.example.enserviceback.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.enserviceback.dto.AnnouncementDto;
import com.example.enserviceback.entity.Admin;
import com.example.enserviceback.entity.Announcement;
import com.example.enserviceback.mapper.AnnouncementMapper;
import com.example.enserviceback.repository.AdminRepository;
import com.example.enserviceback.repository.AnnouncementRepository;
import com.example.enserviceback.service.AnnouncementService;
import com.example.enserviceback.utils.SignedUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/announcements")
public class AnnouncementController {
    @Autowired
    private AmazonS3 s3Client;
    @Autowired
    AnnouncementMapper announcementMapper;
    @Autowired
    AdminRepository adminRepository ;
    @Autowired
    AnnouncementService announcementService ;
    @Autowired
    private AnnouncementRepository announcementRepository;

    @PostMapping("")
    public ResponseEntity<Announcement> createAnnouncement( @RequestPart MultipartFile file , AnnouncementDto announcementDto ) throws IOException {
        // Upload the file to S3
        String key = "announcements/" + UUID.randomUUID().toString();
        s3Client.putObject(new PutObjectRequest(
                "ensapbucket",
                key,
                file.getInputStream(),
                new ObjectMetadata()
        ));
        String url  = SignedUrl.generateSignedUrl("ensapbucket" , key ,s3Client ).toString();
        //String url = s3Client.getUrl("my-bucket", key).toString();
        Announcement announcement = announcementMapper.announcementFromAnnouncementDto(announcementDto);
        announcement.setAnnouncementIssueDate(new Date());
        announcement.setAdmin(adminRepository.findByEmail(announcement.getAnnouncementSenderEmail()));
        announcement.setAnnouncementIssueDate(new Date());
        announcement.setAnnouncementFilePath(url);
        announcementService.saveAnnouncement(announcement);
        return ResponseEntity.status(HttpStatus.OK).body(announcement);
    }

    @GetMapping("/{email}")
    public ResponseEntity<List<Announcement>> getAnnouncementsByAdmin(@PathVariable String email) {
        // we should test if admin exist or not ()
        Admin admin = adminRepository.findByEmail(email);
        List<Announcement> announcements = announcementRepository.findByAdmin_Email(admin.getEmail());

        return ResponseEntity.ok().body(announcements) ;
    }

    //


}


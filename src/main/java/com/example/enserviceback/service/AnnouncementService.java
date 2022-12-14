package com.example.enserviceback.service;

import com.example.enserviceback.entity.Announcement;
import com.example.enserviceback.entity.Role;
import com.example.enserviceback.entity.Student;
import com.example.enserviceback.repository.AnnouncementRepository;
import com.example.enserviceback.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;


@Service
@Transactional
public class AnnouncementService {


    @Autowired
    AnnouncementRepository announcementRepository;
    @Autowired
    StudentRepository studentRepository;


    public Announcement saveAnnouncement(Announcement announcement){
        String targetCrowd = announcement.getAnnouncementTargetCrowd();
        List<Student> students = studentRepository.findByFiliere(targetCrowd);
        students.stream().forEach(student -> student.getAnnouncements().add(announcement));
        announcementRepository.save(announcement);
        return new Announcement();
    }


}

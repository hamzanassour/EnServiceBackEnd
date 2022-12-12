package com.example.enserviceback.repository;

import com.example.enserviceback.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher , Long> {

    Teacher findByFirstName(String firstName);

}

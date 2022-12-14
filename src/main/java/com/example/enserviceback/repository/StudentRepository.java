package com.example.enserviceback.repository;

import com.example.enserviceback.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository  extends JpaRepository<Student , Long> {
    Student findByApogee(long apogee);
    List<Student> findByFiliere(String filiere);

}

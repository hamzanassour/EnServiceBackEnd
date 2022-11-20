package com.example.enserviceback.service;

import com.example.enserviceback.entity.Student;
import com.example.enserviceback.entity.User;
import com.example.enserviceback.repository.StudentRepository;
import com.example.enserviceback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository ;

    public  void saveStudent(Student student){
        studentRepository.save (student);
    }


}

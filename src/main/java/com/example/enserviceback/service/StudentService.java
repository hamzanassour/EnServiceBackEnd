package com.example.enserviceback.service;

import com.example.enserviceback.entity.Student;
import com.example.enserviceback.repository.StudentRepository;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository ;

    public  Student saveStudent(Student student){
       return studentRepository.save (student);
    }
    public List<Student>  getAllStudents(){
        return studentRepository.findAll ();
    }


}

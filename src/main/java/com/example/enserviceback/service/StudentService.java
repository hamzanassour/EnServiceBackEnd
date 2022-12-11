package com.example.enserviceback.service;

import com.example.enserviceback.entity.Role;
import com.example.enserviceback.entity.Student;
import com.example.enserviceback.repository.RoleRepository;
import com.example.enserviceback.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class StudentService {

    @Autowired
    StudentRepository studentRepository ;
    @Autowired
    RoleRepository roleRepository;

    public  Student saveStudentWithRole(Student student , String roleName){
        Role  role = roleRepository.findByName(roleName);
        student.setRoles(Arrays.asList(role));
       return studentRepository.save (student);
    }
    public List<Student>  getAllStudents(){
        return studentRepository.findAll ();
    }


}

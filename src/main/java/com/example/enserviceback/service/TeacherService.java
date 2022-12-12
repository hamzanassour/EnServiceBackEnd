package com.example.enserviceback.service;

import com.example.enserviceback.entity.Role;
import com.example.enserviceback.entity.Teacher;
import com.example.enserviceback.repository.RoleRepository;
import com.example.enserviceback.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class TeacherService {


    @Autowired
    TeacherRepository teacherRepository ;
    @Autowired
    RoleRepository roleRepository;


    public Teacher saveTeacherWithRole(Teacher teacher , String roleName){
        Role role = roleRepository.findByName(roleName);
        teacher.setRoles(Arrays.asList(role));
        return teacherRepository.save (teacher);
    }


}

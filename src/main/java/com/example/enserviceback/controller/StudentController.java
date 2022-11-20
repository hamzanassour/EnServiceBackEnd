package com.example.enserviceback.controller;

import com.example.enserviceback.entity.Student;
import com.example.enserviceback.entity.User;
import com.example.enserviceback.service.KeycloakService;
import com.example.enserviceback.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    KeycloakService keycloakService ;
    @Autowired
    StudentService studentService ;

    // student registration .


    @GetMapping
    public String someTHing(){
        return "Hi Hi Hi ";
    }
    @PostMapping(value = "" )
    public String addUser(@RequestBody Student student){
        keycloakService.addUserToKeycloak (student);
        studentService.saveStudent (student);
        return "User Added Successfully to keycloak ";
    }


}

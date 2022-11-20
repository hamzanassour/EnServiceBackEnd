package com.example.enserviceback.controller;

import com.example.enserviceback.entity.Student;
import com.example.enserviceback.service.KeycloakService;
import com.example.enserviceback.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/students")
public class StudentController {


    private KeycloakService keycloakService ;

    private  StudentService studentService ;

    // Constructor Injection

    public  StudentController( KeycloakService keycloakService , StudentService studentService  ){
        this.studentService  = studentService ;
        this.keycloakService = keycloakService ;
    }




    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(){
        return ResponseEntity.status (HttpStatus.OK ).body (studentService.getAllStudents ());
    }
    @PostMapping(value = "" )
    public ResponseEntity<Student> addUser(@RequestBody Student student){
        keycloakService.addUserToKeycloak (student);
        Student savedStudent = studentService.saveStudent (student);
        return ResponseEntity.status (HttpStatus.CREATED).body (savedStudent);
    }


}

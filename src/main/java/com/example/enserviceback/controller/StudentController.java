package com.example.enserviceback.controller;

import com.example.enserviceback.dto.StudentDto;
import com.example.enserviceback.entity.Student;
import com.example.enserviceback.service.KeycloakService;
import com.example.enserviceback.service.StudentService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
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
    public ResponseEntity<Student> addUser(@RequestBody StudentDto studentDto){

        keycloakService.addUserToKeycloak (studentDto);
        // studentDto to student  mapping ! need refactoring when we will integrate mapstruct for mapping purposes
        Student student = new Student ();
        student.setApogee (studentDto.getApogee ());
        student.setCne (studentDto.getCne ());
        student.setFiliere (studentDto.getFiliere ());
        student.setEmail (studentDto.getEmail ());
        student.setFirstName (studentDto.getFirstName ());
        student.setLastName (studentDto.getLastName ());
        student.setLevel (studentDto.getLevel ());
        student.setBirthDate (studentDto.getBirthDate ());
        Student savedStudent = studentService.saveStudent (student);
        return ResponseEntity.status (HttpStatus.CREATED).body (savedStudent);
    }
    @PostMapping(value = "/batch" )
    public ResponseEntity<List<StudentDto>> addUsers(@RequestPart MultipartFile file){
        // users extracted from the csv file
        List<StudentDto> users = new ArrayList<> ();
        // parse CSV file to create a list of `User` objects
        try (Reader reader = new BufferedReader (new InputStreamReader (file.getInputStream()))) {

            // create csv bean reader
            CsvToBean<StudentDto> csvToBean = new CsvToBeanBuilder (reader)
                    .withType(StudentDto.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            // convert `CsvToBean` object to list of users
             users = csvToBean.parse();

            users.stream ().forEach (System.out::println);



        } catch (Exception ex) {
            ex.printStackTrace ();
        }

        for (StudentDto studentDto : users){
            keycloakService.addUserToKeycloak (studentDto);
            // studentDto to student  mapping ! need refactoring when we will integrate mapstruct for mapping purposes
            Student student = new Student ();
            student.setApogee (studentDto.getApogee ());
            student.setCne (studentDto.getCne ());
            student.setFiliere (studentDto.getFiliere ());
            student.setEmail (studentDto.getEmail ());
            student.setFirstName (studentDto.getFirstName ());
            student.setLastName (studentDto.getLastName ());
            student.setLevel (studentDto.getLevel ());
            student.setBirthDate (studentDto.getBirthDate ());
            Student savedStudent = studentService.saveStudent (student);
        }
        return ResponseEntity.status (HttpStatus.OK).body (users) ;
    }




}

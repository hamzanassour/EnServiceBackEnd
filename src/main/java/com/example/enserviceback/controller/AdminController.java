package com.example.enserviceback.controller;

import com.example.enserviceback.dto.StudentDto;
import com.example.enserviceback.entity.Student;
import com.example.enserviceback.exceptions.SavingStudentInProviderException;
import com.example.enserviceback.mapper.StudentMapper;
import com.example.enserviceback.repository.StudentRepository;
import com.example.enserviceback.service.KeycloakService;
import com.example.enserviceback.service.StudentService;
import com.example.enserviceback.utils.Constants;
import com.example.enserviceback.utils.Parser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/v0/admin")
public class AdminController {

    private KeycloakService keycloakService ;

    private  StudentService studentService ;

    private StudentMapper studentMapper ;
    private final StudentRepository studentRepository;

    // Constructor Injection

    public AdminController(KeycloakService keycloakService , StudentService studentService , StudentMapper studentMapper,
                           StudentRepository studentRepository){
        this.studentService  = studentService ;
        this.keycloakService = keycloakService ;
        this.studentMapper = studentMapper;
        this.studentRepository = studentRepository;
    }




    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(){
        return ResponseEntity.status (HttpStatus.OK ).body (studentService.getAllStudents ());
    }
    @PostMapping(value = "" )
    public ResponseEntity<Student> addUser(@RequestBody StudentDto studentDto) throws SavingStudentInProviderException {
        Student student = studentMapper.studentFromStudentDto(studentDto);
        keycloakService.addStudentToKeycloakWithRole(student , Constants.STUDENT_ROLE);
        Student savedStudent = studentService.saveStudentWithRole(student ,Constants.STUDENT_ROLE );
        return ResponseEntity.status (HttpStatus.CREATED).body (savedStudent);
    }

    @PostMapping(value = "/batch" )
    public ResponseEntity<List<Student>> addUsers(@RequestPart MultipartFile file) throws SavingStudentInProviderException {
        List<Student> students = Parser.getStudents(file);
        List<Student> savedStudents = new ArrayList<>();
        for (Student student : students){
            keycloakService.addStudentToKeycloakWithRole(student , Constants.STUDENT_ROLE);
            Student savedStudent = studentService.saveStudentWithRole(student , Constants.STUDENT_ROLE);
            savedStudents.add(savedStudent);
        }
        return ResponseEntity.status (HttpStatus.OK).body (savedStudents) ;
    }



    @GetMapping(value = "{apogee}" )
    public ResponseEntity<Student> findUser(@PathVariable long apogee){
        Student student = studentRepository.findByApogee(apogee);
        return ResponseEntity.status (HttpStatus.FOUND).body (student);
    }








}

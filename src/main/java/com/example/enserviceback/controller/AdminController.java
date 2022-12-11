package com.example.enserviceback.controller;

import com.example.enserviceback.dto.StudentDto;
import com.example.enserviceback.entity.Student;
import com.example.enserviceback.mapper.StudentMapper;
import com.example.enserviceback.repository.StudentRepository;
import com.example.enserviceback.service.KeycloakService;
import com.example.enserviceback.service.StudentService;
import com.example.enserviceback.utils.Constants;
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
import java.util.stream.Collectors;


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
    public ResponseEntity<Student> addUser(@RequestBody StudentDto studentDto){
        Student student = studentMapper.studentFromStudentDto(studentDto);
        keycloakService.addStudentToKeycloakWithRole(student , Constants.STUDENT_ROLE);
        Student savedStudent = studentService.saveStudentWithRole(student ,Constants.STUDENT_ROLE );
        return ResponseEntity.status (HttpStatus.CREATED).body (savedStudent);
    }

    @PostMapping(value = "/batch" )
    public ResponseEntity<List<Student>> addUsers(@RequestPart MultipartFile file){
        // users extracted from the csv file
        List<StudentDto> studentDtos = new ArrayList<> ();
        List<Student> students = new ArrayList<>();
        // parse CSV file to create a list of `User` objects
        try (Reader reader = new BufferedReader (new InputStreamReader (file.getInputStream()))) {

            // create csv bean reader
            CsvToBean<StudentDto> csvToBean = new CsvToBeanBuilder (reader)
                    .withType(StudentDto.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            // convert `CsvToBean` object to list of users
            studentDtos = csvToBean.parse();

          students =  studentDtos.stream ().map(studentDto -> studentMapper.studentFromStudentDto(studentDto)).collect(Collectors.toList());



        } catch (Exception ex) {
            ex.printStackTrace ();
        }

        for (Student student : students){
            keycloakService.addStudentToKeycloakWithRole(student , Constants.STUDENT_ROLE);
            // studentDto to student  mapping ! need refactoring when we will integrate mapstruct for mapping purposes
            Student savedStudent = studentService.saveStudentWithRole(student , Constants.STUDENT_ROLE);
        }
        return ResponseEntity.status (HttpStatus.OK).body (students) ;
    }

    @GetMapping(value = "{apogee}" )
    public ResponseEntity<Student> findUser(@PathVariable long apogee){
        Student student = studentRepository.findByApogee(apogee);
        return ResponseEntity.status (HttpStatus.FOUND).body (student);
    }








}

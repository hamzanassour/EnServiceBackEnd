package com.example.enserviceback.controller;

import com.example.enserviceback.dto.StudentDto;
import com.example.enserviceback.dto.TeacherDto;
import com.example.enserviceback.entity.Student;
import com.example.enserviceback.entity.Teacher;
import com.example.enserviceback.exceptions.SavingStudentInProviderException;
import com.example.enserviceback.mapper.StudentMapper;
import com.example.enserviceback.mapper.TeacherMapper;
import com.example.enserviceback.repository.StudentRepository;
import com.example.enserviceback.repository.TeacherRepository;
import com.example.enserviceback.service.KeycloakService;
import com.example.enserviceback.service.StudentService;
import com.example.enserviceback.service.TeacherService;
import com.example.enserviceback.utils.Constants;
import com.example.enserviceback.utils.Parser;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v0/admin")
public class AdminController {

    private KeycloakService keycloakService ;

    private  StudentService studentService ;
    private TeacherService teacherService ;

    private StudentMapper studentMapper ;
    private TeacherMapper teacherMapper;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;


    @GetMapping("/student")
    public ResponseEntity<List<Student>> getAllStudents(){
        return ResponseEntity.status (HttpStatus.OK ).body (studentService.getAllStudents ());
    }
    @PostMapping(value = "/student")
    public ResponseEntity<Student> addUser(@RequestBody StudentDto studentDto) throws SavingStudentInProviderException {
        Student student = studentMapper.studentFromStudentDto(studentDto);
        keycloakService.addUserToKeycloakWithRole(student , Constants.STUDENT_ROLE);
        Student savedStudent = studentService.saveStudentWithRole(student ,Constants.STUDENT_ROLE );
        return ResponseEntity.status (HttpStatus.CREATED).body (savedStudent);
    }

    @PostMapping(value = "/student/batch")
    public ResponseEntity<List<Student>> addUsers(@RequestPart MultipartFile file) throws SavingStudentInProviderException {
        List<Student> students = Parser.getStudents(file);
        List<Student> savedStudents = new ArrayList<>();
        for (Student student : students){
            keycloakService.addUserToKeycloakWithRole(student , Constants.STUDENT_ROLE);
            Student savedStudent = studentService.saveStudentWithRole(student , Constants.STUDENT_ROLE);
            savedStudents.add(savedStudent);
        }
        return ResponseEntity.status (HttpStatus.OK).body (savedStudents) ;
    }



    @GetMapping(value = "/student/{apogee}" )
    public ResponseEntity<Student> findStudent(@PathVariable long apogee){
        Student student = studentRepository.findByApogee(apogee);
        return ResponseEntity.status (HttpStatus.FOUND).body (student);
    }
    @GetMapping(value = "/teacher/{firstName}" )
    public ResponseEntity<Teacher> findTeacher(@PathVariable String firstName){
        Teacher teacher = teacherRepository.findByFirstName(firstName);
        return ResponseEntity.status (HttpStatus.FOUND).body (teacher);
    }
    @PostMapping(value = "/teacher" )
    public ResponseEntity<Teacher> addTeacher(@RequestBody TeacherDto teacherDto) throws SavingStudentInProviderException {
        Teacher teacher = teacherMapper.teacherFromTeacherDto(teacherDto);
        keycloakService.addUserToKeycloakWithRole(teacher , Constants.TEACHER_ROLE);
        Teacher  savedTeacher = teacherService.saveTeacherWithRole (teacher ,Constants.TEACHER_ROLE );
        return ResponseEntity.status (HttpStatus.CREATED).body (savedTeacher);
    }








}

package com.example.enserviceback.utils;

import com.example.enserviceback.dto.StudentDto;
import com.example.enserviceback.entity.Student;
import com.example.enserviceback.mapper.StudentMapper;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Parser {

    @Autowired
    static  StudentMapper studentMapper;


    public static  List<Student> getStudents(MultipartFile file) {
        List<StudentDto> studentDtos = new ArrayList<>();
        List<Student> students = new ArrayList<>();
        // parse CSV file to create a list of `User` objects
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

            // create csv bean reader
            CsvToBean<StudentDto> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(StudentDto.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            // convert `CsvToBean` object to list of users
            studentDtos = csvToBean.parse();
            students =  studentDtos.stream ().map(studentDto -> studentMapper.studentFromStudentDto(studentDto)).collect(Collectors.toList());
        } catch (Exception ex) {
            ex.printStackTrace ();
        }
        return students;
    }
}

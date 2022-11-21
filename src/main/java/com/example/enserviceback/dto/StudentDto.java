package com.example.enserviceback.dto;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    @CsvBindByName
    private String firstName;
    @CsvBindByName
    private String lastName;
    @CsvBindByName
    private String password;
    @CsvBindByName
    private String email;
    @CsvBindByName
    private String cne ;
    @CsvBindByName
    private long apogee ;
    @CsvBindByName
    private String level ;
    @CsvBindByName
    private String filiere ;
    @CsvDate(value = "yyyy-MM-dd")
    @CsvBindByName
    private LocalDate birthDate ;
}

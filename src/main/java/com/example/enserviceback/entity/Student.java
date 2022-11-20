package com.example.enserviceback.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student extends User {


    private String cni ;
    private String cne ;
    private long apogee ;
    private String email2 ;
    private String level ;
    private String filiere ;
    private String phone ;
    private Date  birthDate ;

}

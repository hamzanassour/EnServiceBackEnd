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


    private String cni ; //  given  by student
    private String cne ;  //  specified by admin
    private long apogee ; //  specified by admin x
    private String level ; // specified by admin
    private String filiere ; // specified by admin
    private String phone ; // given  by student
    private Date  birthDate ; //  specified by admin

}

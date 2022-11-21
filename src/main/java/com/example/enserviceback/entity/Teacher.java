package com.example.enserviceback.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher extends User{

    private  String cni ;
    private Date birthDate ;




}

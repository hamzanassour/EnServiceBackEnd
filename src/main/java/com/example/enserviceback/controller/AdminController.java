package com.example.enserviceback.controller;

import com.example.enserviceback.entity.Student;
import com.example.enserviceback.entity.User;
import com.example.enserviceback.service.KeycloakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.Media;

@RestController
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    KeycloakService keycloakService;


}

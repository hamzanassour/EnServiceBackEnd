package com.example.enserviceback.service;

import com.example.enserviceback.entity.User;
import com.example.enserviceback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository ;

    public  void saveUser(User user){
        userRepository.save (user);
    }

}

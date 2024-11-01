package org.example.taskmanager.service;

import org.example.taskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyUserService {

    private final UserRepository userRepository;

    @Autowired
    public MyUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


}

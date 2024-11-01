package org.example.taskmanager.controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AuthController {

    @PostMapping
    public String myTest() {

        System.out.println("myTest");
        return "test";
    }

}

package org.example.taskmanager.controller;

import org.example.taskmanager.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegisterController {
    
    private final MyUserService myUserService;

    @Autowired
    public RegisterController(MyUserService myUserService) {
        this.myUserService = myUserService;
    }

    @PostMapping
    public ResponseEntity<String> register(@RequestParam String username, @RequestParam String password) {

        System.out.println("Registering user " + username + " with password " + password);
        try{
            myUserService.registerUser(username, password);
            System.out.println("User registered successfully");

            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        }catch (Exception e){
            e.printStackTrace(); // Isso irá imprimir o stack trace da exceção
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error registering user: " + e.getMessage());
        }
    }
}

package org.example.taskmanager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class AuthController {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        System.out.println("Test endpoint was called!");
        return ResponseEntity.ok("Test POSTMAN,  endpoint is working!");
    }

}

package org.example.taskmanager.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping()
public class AuthController {

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        System.out.println("Test endpoint was called!");
        return ResponseEntity.status(HttpStatus.IM_USED).body("Test endpoint was called!");
    }

}

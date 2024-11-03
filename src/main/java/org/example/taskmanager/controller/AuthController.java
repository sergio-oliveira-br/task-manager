package org.example.taskmanager.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping()
public class AuthController {

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        System.out.println("Test endpoint was called!");
        return ResponseEntity.status(HttpStatus.IM_USED).body("Test endpoint was called!");
    }


    @GetMapping("/logout-manual")
    public ResponseEntity<?> logoutManual(HttpServletRequest request) {
        System.out.println("Logout Manual Request: " + request.getRequestURI() + ", " + request);

        // Invalidar a sessão manualmente
        request.getSession().invalidate();

        // Limpar o contexto de segurança
        SecurityContextHolder.clearContext();

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Logout Manual Request");
    }
}

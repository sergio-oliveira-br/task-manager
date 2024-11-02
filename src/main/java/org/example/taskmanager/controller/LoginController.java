package org.example.taskmanager.controller;

import org.example.taskmanager.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final MyUserService myUserService;

    @Autowired
    public LoginController(MyUserService myUserService) {
        this.myUserService = myUserService;
    }


//    @PostMapping
//    public String login(@RequestParam String username, @RequestParam String password) {
//
//        boolean isAuthtenticated = myUserService.userDataChecker(username, password);
//        System.out.println("Boolean Status: " + isAuthtenticated);
//
//        // Logando o estado do SecurityContext
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println("Authentication: " + authentication);
//
//        if (isAuthtenticated) {
//            return "redirect:/index.html";
//        }
//        return "redirect:/login.html";
//    }

    @GetMapping
    public String showLoginForm() {
        return "login"; // Retorna a página de login
    }
}

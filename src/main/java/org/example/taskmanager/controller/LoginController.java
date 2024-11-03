package org.example.taskmanager.controller;

import org.example.taskmanager.config.CustomUserDetailsService;
import org.example.taskmanager.service.MyUserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class LoginController {

    //private final MyUserService myUserService;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;


    public LoginController(MyUserService myUserService, AuthenticationManager authenticationManager, CustomUserDetailsService customUserDetailsService) {
        this.authenticationManager = authenticationManager;
        this.customUserDetailsService = customUserDetailsService;
    }


    @PostMapping
    public String login(@RequestParam String username, @RequestParam String password) {

        System.out.println("Acessando o metodo Login..\n");
        System.out.println("Tentando autenticar usuário: " + username);

        // Cria um token de autenticação
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
        System.out.println("Criando Token:" +  authToken);

        // Realiza a autenticação
        Authentication authentication = authenticationManager.authenticate(authToken);
        System.out.println("Realizando a autenticacao: " + authentication);

        // Armazena a autenticação no contexto de segurança
        SecurityContextHolder.getContext().setAuthentication(authentication);
        if (authentication.isAuthenticated()) {
            System.out.println("Usuário autenticado: " + authentication.getName());
            System.out.println("Authorities: " + authentication.getAuthorities());


            return "redirect:/index.html";
            //return ResponseEntity.status(HttpStatus.ACCEPTED).body(authentication.getPrincipal());
        }
        System.out.println("Usuario nao autenticado: " + authentication.getName());
        return "redirect:/login.html";
        //return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(authentication.getPrincipal());
    }
}

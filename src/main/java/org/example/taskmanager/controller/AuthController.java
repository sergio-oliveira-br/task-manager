package org.example.taskmanager.controller;

import org.springframework.ui.Model;
import org.example.taskmanager.model.MyUser;
import org.example.taskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class AuthController {

    private final UserRepository userRepository;

    @Autowired
    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public String registerUser(@RequestBody MyUser myUser) {

        myUser.setPassword("{noop}" + myUser.getPassword()); // Armazena a senha sem codificação
        userRepository.save(myUser);
        return "redirect:/login"; // Redireciona para a página de login após o registro
    }

    @GetMapping
    public String showRegistrationForm(Model model) {

        model.addAttribute("myUser", new MyUser());
        return "register"; // Retorna o nome da página de registro
    }
}

package org.example.taskmanager.service;

import org.example.taskmanager.model.MyUser;
import org.example.taskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public MyUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser (String username, String password) {

        System.out.println("Registering user in service:" + username);

        MyUser myUser = new MyUser();
        myUser.setUsername(username);
        myUser.setUserPassword(passwordEncoder.encode(password));// Criptografa a senha

        userRepository.save(myUser);

        System.out.println("User registered successfully");
    }

    // Este método verificar se o usuário existe e se a senha está correta
    public boolean userDataChecker(String username, String password) {


        Optional<MyUser> myUser = userRepository.findByUsername(username);

        if (myUser.isPresent()) {
            if (passwordEncoder.matches(password, myUser.get().getUserPassword())) {
                System.out.println("User found" + myUser.get().getUsername());
                return true;
            }
        }
        return false;
    }
}

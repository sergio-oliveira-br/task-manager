package org.example.taskmanager.config;

import org.example.taskmanager.model.MyUser;
import org.example.taskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


//Para carregar o usuário e suas permissões do banco de dados, usando o UserDetailsService do Spring.
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("Acessando o metodo Load User com o usuario: " + username);
        MyUser myUser = userRepository.findByUsername(username);

        if (myUser == null) {
            throw new UsernameNotFoundException("Usuario " + username + " no encontrado");
        }

        System.out.println("sucesso....");

        return new User("admin", "admin", new ArrayList<>());
    }
}

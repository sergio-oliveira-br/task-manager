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
import java.util.Optional;


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

        Optional<MyUser> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + username);
        }

        MyUser myUser = user.get();

        return User.builder()
                .username(myUser.getUsername())
                .password(myUser.getPassword())
                .roles(myUser.getUserProfile())  // Certifique-se de que o campo "role" existe em MyUser
                .build();
    }
}

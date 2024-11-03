package org.example.taskmanager.config;

import org.example.taskmanager.model.MyUser;
import org.example.taskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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

        System.out.println("Procurando o Usuário: " + username);
        Optional<MyUser> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Usuário " + username + " não encontrado");
        }

        System.out.println("Usuário não é nulo.");
        MyUser myUser = user.get();
        System.out.println("Procurando usuario: " + myUser);

        return new User(
            myUser.getUsername(),
            myUser.getPassword(),
            myUser.getAuthorities()
       );
    }
}

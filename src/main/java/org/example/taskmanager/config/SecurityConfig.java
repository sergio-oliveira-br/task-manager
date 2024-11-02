package org.example.taskmanager.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


// Configure as regras de autenticação e autorização no projeto.
@Configuration

// Habilita a segurança web do Spring Security para que ele intercepte e gerencie as requisições HTTP.
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    @Autowired
    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    // Esse método configura as regras de autenticação e autorização para suas requisições HTTP.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeRequests((authz) -> authz
                        .requestMatchers("/login", "/register.html", "/test").permitAll()
                    .anyRequest().authenticated()

                )

                        .formLogin(form -> form
                                .loginPage("/login.html").permitAll() // Define a página de login personalizada
                                .defaultSuccessUrl("/index.html", true) // Redireciona após login bem-sucedido
                                .failureUrl("/login.html?error=true")
                        )
                        .logout(logout -> logout
                                .logoutUrl("/logout") // URL para logout
                                .logoutSuccessUrl("/login.html?logout=true")
                        )
                        .csrf(csrf -> csrf.disable()) // Desativa CSRF temporariamente para facilitar o teste


        .csrf(AbstractHttpConfigurer::disable);


//
//                // Inicia a definição das regras de autorização.
//                .authorizeHttpRequests((requests) -> requests
//
//                    // Permite acesso irrestrito a qualquer recurso neste endpoit
//                    .requestMatchers("/register", "/login").permitAll()
//                    .requestMatchers("/register.html").permitAll()
//                    // Restringe o acesso a qualquer recurso neste endpoit
//                    .requestMatchers("/index.html").permitAll()
//                    //test
//                    .requestMatchers("/test").permitAll()
//                    // Requer autenticação para todas as outras requisições não especificadas
//                    .anyRequest().authenticated()
//
//                // Ativa o uso de formulários para userDataChecker, permitindo o uso da página personalizada userDataChecker.html.
//                ).formLogin(form -> form
//
//                    // Define a página a ser exibida quando o usuário tenta acessar uma página que requer autenticação
//                    // Permite acesso para qualquer usuário, sem necessidade de autenticação.
//                    .loginPage("/login.html").permitAll()
//
//                    // Redireciona para /index.html após o userDataChecker com sucesso
//                    .defaultSuccessUrl("/index.html", true)
//                    .failureUrl("/login.html?error=true")
//                )
//                .logout(logout -> logout
//                        .logoutSuccessUrl("/login.html?logout=true")
//                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
}


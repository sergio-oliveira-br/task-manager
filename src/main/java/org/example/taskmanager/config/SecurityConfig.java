package org.example.taskmanager.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

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

            .csrf(AbstractHttpConfigurer::disable) // Desativa CSRF temporariamente para facilitar o teste

            // Inicia a definição das regras de autorização.
            .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                // Permite acesso irrestrito a qualquer recurso neste endpoit
                .requestMatchers("/register.html", "/login.html").permitAll()
                .requestMatchers("/login", "/register").permitAll()

                // Restringe o acesso a qualquer recurso neste endpoit
                .requestMatchers("/index.html").authenticated()
                .requestMatchers("/test").authenticated()

                // Requer autenticação para todas as outras requisições não especificadas
                .anyRequest().authenticated()
            )
            .formLogin(Customizer.withDefaults())

            .logout(logout -> logout
                .logoutUrl("/perform_logout") // URL para logout
                .logoutSuccessUrl("/login?logout=true") // URL após logout//
            );
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


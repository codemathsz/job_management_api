package br.com.codemathsz.job_management.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // desabilitar o spring security
        http.csrf(csrf -> csrf.disable())// CSRF => Cross Site Request Forgery
            .authorizeHttpRequests(auth ->{
                auth.requestMatchers("/candidate/").permitAll()
                        .requestMatchers("/company/").permitAll();
                auth.anyRequest().authenticated();
            })
        ;
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEnconder(){
        return new BCryptPasswordEncoder();
    }
}

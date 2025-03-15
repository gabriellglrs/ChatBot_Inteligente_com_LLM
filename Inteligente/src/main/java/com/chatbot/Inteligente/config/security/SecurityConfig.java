package com.chatbot.Inteligente.config.security;

import com.chatbot.Inteligente.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

     @Bean
     public SecurityFilterChain securityFilterChain(HttpSecurity http, UserService userService) throws Exception {
          return http
                  .csrf(AbstractHttpConfigurer::disable)
                  .authorizeHttpRequests(auth -> auth
                          .requestMatchers("/login").permitAll()
                          .requestMatchers("/register").permitAll()
                          .requestMatchers("/user/register").permitAll()
                          .anyRequest().authenticated()
                  )
                  .formLogin(form -> form
                          .loginPage("/login") // Define a URL da sua página de login
                          .permitAll()        // Permite acesso não autenticado à página de login
                          .defaultSuccessUrl("/home", true) // Redireciona após login bem-sucedido
                          .failureUrl("/login?error=true")  // Redireciona em caso de falha
                  )
                  .httpBasic(Customizer.withDefaults())
                  .userDetailsService(userService)
                  .build();
     }

     @Bean
     public PasswordEncoder passwordEncoder() {
          return new BCryptPasswordEncoder(10); // Força o uso de 10 rounds de criptografia de senha com BCrypt
     }

     @Bean
     public GrantedAuthorityDefaults grantedAuthorityDefaults() {
          return new GrantedAuthorityDefaults(""); // Remove o prefixo ROLE_ dos authorities
     }
}

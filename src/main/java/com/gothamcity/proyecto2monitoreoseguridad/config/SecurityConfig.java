package com.gothamcity.proyecto2monitoreoseguridad.config;
import com.gothamcity.proyecto2monitoreoseguridad.service.CustomUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // Habilita la seguridad a nivel de metodo (para @Secured)
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    // Define el codificador de contraseñas como un bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configura el AuthenticationManager
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService)
            throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();

    }

    // Define la configuración de seguridad
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF para simplificar
                .authorizeHttpRequests(auth -> auth
                        // Permitir acceso a la consola de H2 sin autenticación
                        .requestMatchers("/h2-console/**").permitAll()
                        // Rutas protegidas
                        .requestMatchers("/secure/**").authenticated()
                        // Todas las demás rutas están permitidas
                        .anyRequest().permitAll()
                )
                // Configurar headers para permitir frames (requerido para la consola de H2)
                .headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }
}




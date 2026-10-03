package br.com.agroaqua.api.shared.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter filter;

    public SecurityConfig(JwtAuthenticationFilter filter) {
        this.filter = filter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session ->
                        session.sessionCreationPolicy
                                (org.springframework.security.config.http.SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        //=========================== Auth ==================================
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        //=========================== Admin =================================
                        .requestMatchers(HttpMethod.POST, "/api/admin/onboarding/employees").hasAuthority("ADMIN")
                        //========================== Employee ===============================
                        .requestMatchers(HttpMethod.GET, "/api/employee").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/employee/{id}").hasAuthority("ADMIN")
                        //============================ Crop =================================
                        .requestMatchers(HttpMethod.POST, "/api/crop").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/crop").hasAnyAuthority("ADMIN", "EMPLOYEE_MANAGER", "EMPLOYEE_WORKER")
                        .requestMatchers(HttpMethod.GET, "/api/crop/{id}").hasAnyAuthority("ADMIN", "EMPLOYEE_MANAGER", "EMPLOYEE_WORKER")
                        //============================ Plot =================================
                        .requestMatchers(HttpMethod.POST, "/api/plot").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/plot").hasAnyAuthority("ADMIN", "EMPLOYEE_MANAGER", "EMPLOYEE_WORKER")
                        .requestMatchers(HttpMethod.GET, "/api/plot/{id}").hasAnyAuthority("ADMIN", "EMPLOYEE_MANAGER", "EMPLOYEE_WORKER")
                        .requestMatchers(HttpMethod.GET, "/api/plot/code/{code}").hasAnyAuthority("ADMIN", "EMPLOYEE_MANAGER", "EMPLOYEE_WORKER")
                        .requestMatchers(HttpMethod.GET, "/api/plot/crop/{cropId}").hasAnyAuthority("ADMIN", "EMPLOYEE_MANAGER", "EMPLOYEE_WORKER")
                        //========================== Handling ===============================
                        .requestMatchers(HttpMethod.POST, "/api/handling").hasAnyAuthority("ADMIN", "EMPLOYEE_MANAGER", "EMPLOYEE_WORKER")
                        .requestMatchers(HttpMethod.GET, "/api/handling").hasAnyAuthority("ADMIN", "EMPLOYEE_MANAGER", "EMPLOYEE_WORKER")
                        .requestMatchers(HttpMethod.GET, "/api/handling/{id}").hasAnyAuthority("ADMIN", "EMPLOYEE_MANAGER", "EMPLOYEE_WORKER")
                        .requestMatchers(HttpMethod.GET, "/api/handling/employee/{employeeId}").hasAnyAuthority("ADMIN", "EMPLOYEE_MANAGER", "EMPLOYEE_WORKER")
                        .requestMatchers(HttpMethod.GET, "/api/handling/plot/{plotId}").hasAnyAuthority("ADMIN", "EMPLOYEE_MANAGER", "EMPLOYEE_WORKER")
                        //=========================== Sensor ================================
                        .requestMatchers(HttpMethod.POST, "/api/sensor").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/sensor").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/sensor/{id}").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/sensor/code/{sensorCode}").hasAuthority("ADMIN")
                        //========================== Measurement ============================
                        .requestMatchers(HttpMethod.GET, "/api/measurement").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/measurement/{id}").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/measurement/sensor/{sensorId}").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/measurement/sensor/code/{sensorCode}").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/measurement").permitAll()
                        //============================ Others ===============================
                        .requestMatchers("/error").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
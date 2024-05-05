//package com.LeaveApplication.Gateway.Config;
//
//
//import com.LeaveApplication.Gateway.Security.JwtAuthenticationEntryPoint;
//import com.LeaveApplication.Gateway.Security.JwtAuthenticationFilter;
//import lombok.AllArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@AllArgsConstructor
//public class SecurityFilterConfig {
//
//
//    private JwtAuthenticationEntryPoint point;
//    private JwtAuthenticationFilter filter;
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
//        return security.csrf(csrf-> csrf.disable())
//                .cors(cors-> cors.disable())
//                .authorizeHttpRequests(auth->auth.requestMatchers("/authenticate").permitAll()
//                        .anyRequest().authenticated())
//                .exceptionHandling(ex ->ex.authenticationEntryPoint(point))
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
//                .build();
//
//    }
//
//}

//package com.LeaveApplication.Gateway.Config;
//
//
//import com.LeaveApplication.Gateway.Model.Users;
//import com.LeaveApplication.Gateway.Repo.userRepo;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//public class SecurityConfig {
//
//    private final userRepo userRepository;
//    public SecurityConfig(userRepo userRepository) {
//        this.userRepository = userRepository;
//    }
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return (userName)
//                -> {
//            Users user = userRepository.findByusername(userName);
//
//          if(user== null)
//          throw new UsernameNotFoundException("user name not found");
//            return org.springframework.security.core.userdetails.User.builder()
//                    .username(user.getUsername())
//                    .password(passwordEncoder().encode(user.getPassword()))
//                    .roles(user.getRoles().getRole_name())
//                    .build();
//        };
////            return org.springframework.security.core.userdetails.User.builder()
////                    .username("admin")
////                    .password("admin")
////                    .roles("ADMIN")
////                    .build();
////        };
//    }
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
//        return configuration.getAuthenticationManager();
//    }
////    @Bean
////    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////
////       return  http.csrf(csrf -> csrf.disable())
////                .authorizeRequests(authorize -> authorize
////                        .requestMatchers("/", "/").permitAll() // Allowing access to the root path and the login page
////                        .anyRequest().authenticated()
////                )
////                .formLogin(login -> login
////                        .loginProcessingUrl("/login")
////                        .defaultSuccessUrl("/profile")
////                        .permitAll()
////                )
////                .logout(logout->logout.logoutSuccessUrl("/userlogout")
////                        .permitAll()
////                ).build();
////
////
////
////
////    }
//
////    @Bean
////    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
////        return configuration.getAuthenticationManager();
////    }
//
//
//}

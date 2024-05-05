//package com.LeaveApplication.UserServices.Service;
//
//
//import com.LeaveApplication.UserServices.Model.Users;
//import com.LeaveApplication.UserServices.Repo.rolesRepo;
//import com.LeaveApplication.UserServices.Repo.userRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    @Autowired
//   private userRepo roleRepository;
//
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        Users user = roleRepository.findByusername(username);
//
//        if(user== null)
//         throw new UsernameNotFoundException("user name not found");
//         else
//            return  user;
//
//    }
//}

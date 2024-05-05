package com.LeaveApplication.UserServices.Repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.LeaveApplication.UserServices.Model.Users;

import java.util.List;

@Repository
public interface userRepo extends JpaRepository<Users, Long> {


    public Users findByusername(String username);

    public List<Users> findByactive(boolean result);
}

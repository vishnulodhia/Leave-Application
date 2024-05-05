package com.LeaveApplication.Leave.Repo;




import com.LeaveApplication.Leave.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepo extends JpaRepository<Users, Long> {


    public Users findByusername(String username);
}

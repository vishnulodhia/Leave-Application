package com.LeaveApplication.Leave.Repo;


import com.LeaveApplication.Leave.Model.LeaveBalance;
import com.LeaveApplication.Leave.Model.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface leavebalanceRepo extends JpaRepository<LeaveBalance, Long> {
  public LeaveBalance findByuserid(Users user_id);
}

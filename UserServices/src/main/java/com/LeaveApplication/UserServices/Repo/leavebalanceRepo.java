package com.LeaveApplication.UserServices.Repo;

import com.LeaveApplication.UserServices.Model.*;
import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface leavebalanceRepo extends JpaRepository<LeaveBalance, Long> {


  @Modifying
  @Transactional
  @Query(value = "DELETE FROM leave_balance lr WHERE lr.user_id = :userId",nativeQuery = true)
  void deleteByuserid(Long userId);

  LeaveBalance findByuserid(Users id);
}

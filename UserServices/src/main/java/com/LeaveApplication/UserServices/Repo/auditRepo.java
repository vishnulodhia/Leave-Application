package com.LeaveApplication.UserServices.Repo;


import com.LeaveApplication.UserServices.Model.Audit;
import com.LeaveApplication.UserServices.Model.LeaveRequest;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface auditRepo extends JpaRepository<Audit, Long> {
    Audit findByrequestsid(LeaveRequest leaveRequest);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM audit_table au WHERE au.userid = :userId",nativeQuery = true)
    void deleteByuserid(Long userId);
}

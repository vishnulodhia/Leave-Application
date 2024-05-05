package com.LeaveApplication.Leave.Repo;

import com.LeaveApplication.Leave.Model.Audit;
import com.LeaveApplication.Leave.Model.LeaveRequest;
import com.LeaveApplication.Leave.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface audirRepo extends JpaRepository<Audit, Long> {
    Audit findByrequestsid(LeaveRequest leaveRequest);
   List<Audit> findByuserid(Users users);
}

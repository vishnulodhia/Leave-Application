package com.LeaveApplication.Leave.Repo;

import com.LeaveApplication.Leave.Model.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface leaveRepo extends JpaRepository<LeaveRequest,Long> {
  List<LeaveRequest> findByuserid(long user_id);
  List<LeaveRequest> findByreqlevel(Integer req_level);
  LeaveRequest findByprocessinstanceid(String req_id);

//  @Transactional
//  @Query(value = "SELECT * FROM leave_requests lr WHERE lr.user_id = :userId AND lr.status = 'pending'", nativeQuery = true)
//  LeaveRequest findByUserIdAndStatusNative(Long userId);

 List<LeaveRequest> findByUseridAndStatus(long userId, String status);


}

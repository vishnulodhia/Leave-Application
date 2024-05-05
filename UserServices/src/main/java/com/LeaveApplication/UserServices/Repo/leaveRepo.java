package com.LeaveApplication.UserServices.Repo;


import com.LeaveApplication.UserServices.Model.LeaveRequest;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface leaveRepo extends JpaRepository<LeaveRequest,Long> {
  List<LeaveRequest> findByuserid(long user_id);
  List<LeaveRequest> findByreqlevel(Integer req_level);
  LeaveRequest findByprocessinstanceid(String req_id);

  @Transactional
  @Query(value = "SELECT * FROM leave_requests lr WHERE lr.user_id = :userId AND lr.status = 'Pending'", nativeQuery = true)
  LeaveRequest findByUserIdAndStatusNative(Long userId);



  @Modifying
  @Transactional
  @Query(value = "DELETE FROM leave_requests lr WHERE lr.user_id = :userId",nativeQuery = true)
  void deleteByuserid(Long userId);

}

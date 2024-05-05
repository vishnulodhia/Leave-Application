package com.LeaveApplication.UserServices.Repo;

import com.LeaveApplication.UserServices.Model.RoleMenu;
import com.LeaveApplication.UserServices.Model.Subrole;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface subroleRepo extends JpaRepository<Subrole, Long> {
    Subrole findByuserid(long id);

    long countBylevel(Integer level);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Subrole au WHERE au.userid = :userId",nativeQuery = true)
    void deleteByuserid(Long userId);
}

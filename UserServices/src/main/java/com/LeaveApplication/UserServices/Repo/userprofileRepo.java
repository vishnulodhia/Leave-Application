package com.LeaveApplication.UserServices.Repo;

import com.LeaveApplication.UserServices.Model.UserProfiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userprofileRepo extends JpaRepository<UserProfiles, Long> {
}

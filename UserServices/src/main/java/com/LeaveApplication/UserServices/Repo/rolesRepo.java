package com.LeaveApplication.UserServices.Repo;

import com.LeaveApplication.UserServices.Model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface rolesRepo  extends JpaRepository<Roles, Long> {
//   public Roles findByrole_id(long id);
}

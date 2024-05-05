package com.LeaveApplication.UserServices.Repo;

import com.LeaveApplication.UserServices.Model.RoleMenu;
import com.LeaveApplication.UserServices.Model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface rolemenuRepo extends JpaRepository<RoleMenu, Long> {
    public List<RoleMenu> findByroles(Roles role);
}

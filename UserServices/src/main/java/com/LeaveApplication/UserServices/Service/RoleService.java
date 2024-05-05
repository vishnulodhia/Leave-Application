package com.LeaveApplication.UserServices.Service;


import com.LeaveApplication.UserServices.Model.Roles;
import com.LeaveApplication.UserServices.Repo.rolesRepo;
import com.LeaveApplication.UserServices.Repo.userRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class RoleService {
    @Autowired
    private rolesRepo roleRepository;
    public List<Roles> getRoles(){
        return roleRepository.findAll();
    }

    public Optional<Roles> getRolesById(Long id){
        return roleRepository.findById(id);
    }

    public Roles AddRoles(Roles book){
        return roleRepository.save(book);
    }


    public Roles UpdateRolesById(Long id,Roles roles){
        Optional<Roles> rolesData = roleRepository.findById(id);
        Roles  updatedrolesData=null;
        if (rolesData.isPresent()) {
       updatedrolesData = rolesData.get();
       updatedrolesData.setRole_name(roles.getRole_name());
        }
        return updatedrolesData;
    }




    public void  deleteRoleById(Long id){
        roleRepository.deleteById(id);
    }

    public void deleteAll(){
        roleRepository.deleteAll();
    }




}

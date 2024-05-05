package com.LeaveApplication.UserServices.Service;


import com.LeaveApplication.UserServices.Model.RoleMenu;
import com.LeaveApplication.UserServices.Repo.rolemenuRepo;
import com.LeaveApplication.UserServices.Repo.rolesRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class RolemenuService {
    @Autowired
    private rolemenuRepo rolemenuRepository;
    @Autowired
    private rolesRepo roleRepository;

 public List<RoleMenu> findmenubyid(Long id ){
     return rolemenuRepository.findByroles(roleRepository.findById(id).get());
 }

}

package com.LeaveApplication.UserServices.Controller;

//import com.LeaveApplication.UserServices.Model.RoleMenu;
import com.LeaveApplication.UserServices.Model.RoleMenu;
import com.LeaveApplication.UserServices.Model.Users;
//import com.LeaveApplication.UserServices.Service.RolemenuService;
import com.LeaveApplication.UserServices.Service.RolemenuService;
import com.LeaveApplication.UserServices.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class RolemenuController {


    private RolemenuService rolemenuservice;

    @GetMapping("/getmenubyid/{id}")
    public ResponseEntity<List<RoleMenu>> getmenubyid(@PathVariable Long id){
        try {
            List<RoleMenu> roleMenuList = rolemenuservice.findmenubyid(id);

            if (roleMenuList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(roleMenuList, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}


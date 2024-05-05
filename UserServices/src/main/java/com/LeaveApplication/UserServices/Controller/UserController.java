package com.LeaveApplication.UserServices.Controller;

import com.LeaveApplication.UserServices.Model.LeaveBalance;
import com.LeaveApplication.UserServices.Model.Subrole;
import com.LeaveApplication.UserServices.Model.Users;
import com.LeaveApplication.UserServices.Repo.auditRepo;
import com.LeaveApplication.UserServices.Repo.leavebalanceRepo;
import com.LeaveApplication.UserServices.Service.UserService;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
public class UserController {

    private UserService userServices;

  @GetMapping("/getmanagerlevel/{id}")
  public ResponseEntity<Subrole> getlevelmanager(@PathVariable long id){
      try{
          Subrole sb= userServices.getlevelformanager(id);
          return new ResponseEntity<>(sb, HttpStatus.OK);
      }
      catch (Exception e){
          e.printStackTrace();
         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }
  @GetMapping("/getactiveusers")
  public ResponseEntity<List<Users>> getactiveusers(){
      try {
          List<Users> UserList = userServices.getactiveusers();

          if (UserList.isEmpty()) {
              return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          }
          return new ResponseEntity<>(UserList, HttpStatus.OK);
      } catch (Exception ex) {
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

    @GetMapping("/getunactiveusers")
    public ResponseEntity<List<Users>> getunactiveusers(){
        try {
            List<Users> UserList = userServices.getunactiveusers();

            if (UserList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(UserList, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//
//    @GetMapping("/isleavepending/{id}")
//    public ResponseEntity<Boolean> isleavepending(@PathVariable long id){
//      try{
//          Boolean result = userServices.isleavepending(id);
//          return new ResponseEntity<>(result, HttpStatus.OK);
//      }
//      catch (Exception e){
//          e.printStackTrace();
//          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//      }
//    }

    @GetMapping("/getAllUser")
    public ResponseEntity<List<Users>> getAllUsers() {


        try {
            List<Users> UserList = userServices.getAllUser();

            if (UserList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(UserList, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


//    @GetMapping("/getUserById/{id}")
//    public ResponseEntity<Users> getUserById(@PathVariable Long id) {
//
//        try {
//            Optional<Book> book = bookServices.getBookById(id);
//
//            if (book.isPresent()) {
//                return new ResponseEntity<>(book.get(), HttpStatus.OK);
//            }
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        catch(Exception ex) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//    }

    @PostMapping("/addUser")
    public ResponseEntity<Users> addUser(@RequestBody Users users) {


        try {
            Users userobj = userServices.addUser(users);
            return new ResponseEntity<>(userobj, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/activateusers/{username}")
    public ResponseEntity<HttpStatus> activateusers(@PathVariable String username) {
        try {
            userServices.active(username);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/deactivateusers/{username}")
    public ResponseEntity<HttpStatus> unactivateusers(@PathVariable String username) {
        try {
            System.out.print("username : "+username);
            userServices.deactive(username);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseEntity<HttpStatus> httpStatusResponseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            return httpStatusResponseEntity;
        }
    }

    @GetMapping("/getsubrole")
    public ResponseEntity<List<Subrole>> getsubrole(){
        return  new ResponseEntity<>(userServices.getsubrole(),HttpStatus.OK);
    }

    @PostMapping("/updatesubrole/{id}")
    public ResponseEntity<Subrole> subrole(@PathVariable long id, @RequestBody Subrole s){
        Subrole sb= userServices.updatesubrole(id,s);
        return new ResponseEntity<>(sb,HttpStatus.OK);
    }

    @GetMapping ("/islastmanager/{level}")
    public ResponseEntity<Boolean> islastmanager(@PathVariable Integer level){
       try{
           Boolean result =  userServices.islastManager(level);
           return new ResponseEntity<>(result,HttpStatus.OK);
       }
       catch (Exception e){
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }

    }


    @PostMapping("/updateUsers/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable Long id, @RequestBody Users user) {
        Users UserObj = userServices.UpdateuserdataById(id, user);
        //employee

        try {
            if (UserObj == null)
                return new ResponseEntity<>(UserObj, HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(UserObj, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/getleavesbalance/{id}") public ResponseEntity<LeaveBalance> getleaves(@PathVariable long id){
        return ResponseEntity.ok().body(userServices.getleaves(id));
    }



    @DeleteMapping("/deleteUserById/{id}/{roleid}")
    public ResponseEntity<HttpStatus> deleteUserById(@PathVariable Long id,@PathVariable int roleid) {


        try {
            userServices.deleteUserById(id,roleid);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/deleteAllUsers")
    public ResponseEntity<HttpStatus> deleteAllUser() {

        try {
            userServices.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }


}
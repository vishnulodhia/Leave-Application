package com.LeaveApplication.UserServices.Service;

import com.LeaveApplication.UserServices.Exception.DuplicateUserRegistrationException;
import com.LeaveApplication.UserServices.Model.*;
import com.LeaveApplication.UserServices.Repo.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserService {
    @Autowired
    private userRepo userRepository;
    @Autowired
    private rolesRepo roleRepository;
    @Autowired
    private leaveRepo leaveRepository;
    @Autowired
    private leavebalanceRepo leavebalanceRepo;
    @Autowired
    private auditRepo auditRepo;
    @Autowired
    private subroleRepo subroleRepo;
    public List<Users> getAllUser(){
        return userRepository.findAll();
    }

    public Optional<Users> getUserById(Long id){
        return userRepository.findById(id);
    }

    public Subrole getlevelformanager(long id){
        Subrole sb = subroleRepo.findByuserid(id);
        return sb;
    }

    public Users addUser(Users user) {
        Users u = userRepository.findByusername(user.getUsername());
        System.out.println("Users:"+u);
        if(u==null) {
            user.setActive(true);
            user.setLogintime(new Timestamp(System.currentTimeMillis()));
             Users returnuser=  userRepository.save(user);

             if(user.getRoles().getRole_id()==3){
                 Subrole sb = new Subrole();
                 sb.setUserid(user.getUser_id());
                 sb.setLevel(user.getLevel());
               subroleRepo.save(sb);
             }

             if(user.getRoles().getRole_id()==2){
            LeaveBalance lb = new LeaveBalance();
            lb.setUserid(userRepository.findByusername(user.getUsername()));
            lb.setCasual_leave(10);
            lb.setSick_leave(10);
            leavebalanceRepo.save(lb);
             }
            return returnuser;
        }
        else{
            System.out.println("From inside else block");
            throw new DuplicateUserRegistrationException("User alredy register");
        }

    }


    public Users UpdateuserdataById(Long id,Users user){
       try {
           Optional<Users> userData = userRepository.findById(id);
           Roles role = roleRepository.findById(user.getRoles().getRole_id()).get();
           Users updatedUserData = new Users();
           Users updateduser = new Users();


           if (userData.get().getRoles().getRole_id() == 2  && user.getRoles().getRole_id() != 2 ) {
//           audit
//           req
//           balance
               deletefromaudit(id);
               deleteleavebyuserid(id);
               deleteuserformleavebalance(id);
           }


           if (userData.get().getRoles().getRole_id() == 3 && user.getRoles().getRole_id() != 3) {
//           subrole
               deletefromsubrole(id);
           } //manager


           if (userData.isPresent()) {
               updatedUserData = userData.get();
               updatedUserData.setUsername(user.getUsername());
               updatedUserData.setEmail(user.getEmail());
               updatedUserData.setPassword(user.getPassword());
               updatedUserData.setRoles(role);
               updateduser = userRepository.save(updatedUserData);
           }
//

           if (updateduser.getRoles().getRole_id() == 2 && userData.get().getRoles().getRole_id() !=2) {
               LeaveBalance lb = new LeaveBalance();
               lb.setUserid(userRepository.findByusername(user.getUsername()));
               lb.setCasual_leave(10);
               lb.setSick_leave(10);
               leavebalanceRepo.save(lb);
           }
           System.out.print("updateduser.getRoles().getRole_id():"+updateduser.getRoles().getRole_id()+" user.getRoles().getRole_id() "+ userData.get().getRoles().getRole_id());
           if (updateduser.getRoles().getRole_id() == 3  && userData.get().getRoles().getRole_id() !=3) {

               Subrole sb = new Subrole();
               sb.setUserid(user.getUser_id());
               sb.setLevel(user.getLevel());
               subroleRepo.save(sb);
           }
           return updateduser;
       }
       catch (Exception e){
           e.printStackTrace();
           return null;
       }


    }


    public LeaveBalance getleaves(Long id ){
       return leavebalanceRepo.findByuserid(userRepository.findById(id).get());
    }
    public List<Subrole> getsubrole(){
        return subroleRepo.findAll();
    }

    public Subrole updatesubrole(long id,Subrole subrole){
        try {
            Subrole sr = subroleRepo.findById(id).get();
            if (sr != null)
                sr.setLevel(subrole.getLevel());
            return subroleRepo.save(sr);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    public void deleteleavebyuserid(Long id){
        List<LeaveRequest> userData = leaveRepository.findByuserid(id);
        if(!userData.isEmpty())
        leaveRepository.deleteByuserid(id);

    }
    public void deleteuserformleavebalance(long id){
     LeaveBalance userData = leavebalanceRepo.findByuserid(userRepository.findById(id).get());
     if(userData!=null)
        leavebalanceRepo.deleteByuserid(id);

    }

//    public boolean isleavepending(long id){
//    LeaveRequest leaveRequest = leaveRepository.findByUserIdAndStatusNative(id);
//    if(leaveRequest!=null)
//        return false;
//    return true;
//    }

    public void deletefromaudit(long id){
//        Audit userData = auditRepo.
        auditRepo.deleteByuserid(id);
    }

    public void deletefromsubrole(long id){

        Subrole userData = subroleRepo.findByuserid(id);
        if(userData!=null)
        subroleRepo.deleteByuserid(id);
    }

    public void  deleteUserById(Long id,int roleid){
        System.out.println(id);
        System.out.println("Roleid"+roleid);

       try {
           if (roleid == 2) {
               deletefromaudit(id);
               deleteuserformleavebalance(id);
               deleteleavebyuserid(id);
           }

           if (roleid == 3) {

               deletefromsubrole(id);
//            System.out.println("Is it good inside if");
           }
           System.out.println("Is it good outside if");
           userRepository.deleteById(id);
       }
       catch ( Exception e){
           e.printStackTrace();
       }

     }


    public void deleteAll(){
        userRepository.deleteAll();
    }

    public boolean isactive(String username){
        Users user = userRepository.findByusername(username);

        if(user.getRoles().getRole_id()!=4) {
            long difference = ((new Timestamp(System.currentTimeMillis())).getTime() - user.getLogintime().getTime()) / 1000 / 3600;
            System.out.print("DIFFERENCE" + difference);

            if(difference>24){
                return false;
            }

            return true;
        }


     return true;
    }

    public List<Users> getactiveusers(){
        return userRepository.findByactive(true);
    }

    public List<Users> getunactiveusers(){

        return userRepository.findByactive(false);
    }

    public long levelcount(Integer level){
    return subroleRepo.countBylevel(level);
    }

    public void active(String username){
        Users user = userRepository.findByusername(username);
        user.setActive(true);
        user.setLogintime(new Timestamp(System.currentTimeMillis()));
        userRepository.save(user);
    }

    public void deactive(String username){
        System.out.println("Username"+username);
        Users user = userRepository.findByusername(username);
        user.setActive(false);
        userRepository.save(user);
    }

    public Boolean getactive(String username){
        return userRepository.findByusername(username).getActive();
    }

    public Boolean islastManager(Integer level){
        if(subroleRepo.countBylevel(level)==1)
            return true;
        return false;
    }




}

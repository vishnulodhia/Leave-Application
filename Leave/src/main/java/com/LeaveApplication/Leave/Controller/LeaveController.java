package com.LeaveApplication.Leave.Controller;


import com.LeaveApplication.Leave.Model.Audit;
import com.LeaveApplication.Leave.Model.LeaveBalance;
import com.LeaveApplication.Leave.Model.LeaveForm;
import com.LeaveApplication.Leave.Model.LeaveRequest;
import com.LeaveApplication.Leave.Service.LeaveRequestService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;





@AllArgsConstructor
@RestController
public class LeaveController {

    private LeaveRequestService leaveService;

    @GetMapping("/getleave/{id}")
    public ResponseEntity<List<LeaveRequest>> getleave(@PathVariable Long id)
    {   try{
        System.out.println("Inside getleave" + id);
    }
    catch (Exception e){
        e.printStackTrace();
    }
        return ResponseEntity.ok().body(leaveService.getLeaveByUser(id));
    }


    @GetMapping("/isleavepending/{id}")
    public ResponseEntity<Boolean> isleavepending(@PathVariable long id){
        try{
            Boolean result = leaveService.isleavepending(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/apply")
    public ResponseEntity<?> save(@RequestBody LeaveForm leavereq)
    {
        System.out.println("Here is leave request"+leavereq);
        return leaveService.create(leavereq);
    }

    @GetMapping("/getLeaveByLevel/{req_level}")
    public ResponseEntity<List<LeaveRequest>>getCurrentLeaveRequestLevel(@PathVariable Integer req_level)
    {  try {
        System.out.println("Level" + req_level);
        return ResponseEntity.ok().body(leaveService.getLeaveByLevel(req_level));
    }
    catch (Exception e){
        e.printStackTrace();
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }

    @GetMapping("/getauditByid/{id}")
    public ResponseEntity<List<Audit>>getCurrentLeaveRequestLevel(@PathVariable long id){
//    {   System.out.println("Level"+req_level);

        return ResponseEntity.ok().body(leaveService.getsuditbyuser(id));
    }

    @GetMapping("/completeTask/{processInstanceId}/{Assigne}/{isTerminate}")
    public ResponseEntity<?>CompleteTask(
            @PathVariable String processInstanceId,
            @PathVariable String Assigne,
            @PathVariable  boolean isTerminate)
    {
        System.out.println("Inside complete task processid"+processInstanceId+"Assign"+Assigne+"Isterminated"+isTerminate);
        return ResponseEntity.ok().body(leaveService.CompleteTask(processInstanceId, Assigne, isTerminate));
    }


}

package com.LeaveApplication.Leave.Service;


import com.LeaveApplication.Leave.Model.Audit;
import com.LeaveApplication.Leave.Model.LeaveBalance;
import com.LeaveApplication.Leave.Model.LeaveForm;
import com.LeaveApplication.Leave.Model.LeaveRequest;
import com.LeaveApplication.Leave.Repo.audirRepo;
import com.LeaveApplication.Leave.Repo.leaveRepo;
import com.LeaveApplication.Leave.Repo.leavebalanceRepo;
import com.LeaveApplication.Leave.Repo.userRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.ObjectUtils;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("LeaveRequestService")
@AllArgsConstructor
@NoArgsConstructor
public class LeaveRequestService {

    @Autowired
    private leaveRepo LeaveRepo;

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    @Autowired
    private userRepo userRepository;

    @Autowired
    private leavebalanceRepo leavebalanceRepo;

    @Autowired
    private audirRepo auditRepo;

    @Autowired
    private leaveRepo leaveRepo;



    public  List<Audit> getsuditbyuser(long userid){
        return auditRepo.findByuserid(userRepository.findById(userid).get());
    }

    public List<LeaveRequest> getLeaveByUser(Long user_id) {
        try {
            List<LeaveRequest> leavereq = LeaveRepo.findByuserid((user_id));
            return leavereq;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<LeaveRequest> getLeaveByLevel(int req_level) {

        List<LeaveRequest> leavereq = LeaveRepo.findByreqlevel(req_level);
        for(int i=0;i<leavereq.size();i++){
       leavereq.get(i).setUsername(userRepository.findById(leavereq.get(i).getUserid()).get().getUsername());
        }
        System.out.println("Inside getleavebyLevel services");
        return leavereq;
    }

    public ResponseEntity<?> create(LeaveForm leaveReq) {

        LeaveRequest leaverequest = new LeaveRequest();

        leaverequest.setUserid(leaveReq.getUser_id());
        leaverequest.setStart_date(leaveReq.getStart_date());
        leaverequest.setEnd_date(leaveReq.getEnd_date());

        if(!(leaveReq.getReason() == null))
        leaverequest.setReason(leaveReq.getReason());

        leaverequest.setLeave_type(leaveReq.getLeave_type());
        leaverequest.setStatus("Pending");
        leaverequest.setProcessinstanceid(startFlow());
        leaverequest.setReqlevel(1);
        leaverequest.setNo_days(leaveReq.getno_days());
        LeaveRepo.save(leaverequest);

        Audit audit = new Audit();
        System.out.println("USERS"+userRepository.findById(leaveReq.getUser_id()).get());
        audit.setUserid(userRepository.findById(leaveReq.getUser_id()).get());
        audit.setRequestsid(leaverequest);
        auditRepo.save(audit);



        HashMap<String, Object> response = new HashMap<>();
        response.put("msg", "Leave Request Created successfully");
        return ResponseEntity.ok().body(response);


    }

    public String startFlow() {
        Map<String, Object> delegateExecutionData = new HashMap<>();

        delegateExecutionData.put("Request Level", 1);

        ProcessInstance processInstance = this.runtimeService.startProcessInstanceByKey("myProcess", delegateExecutionData);
        return processInstance.getProcessInstanceId();
    }

    public boolean CompleteTask(String processInstanceId, String taskType, boolean isTerminate) {
        List<ProcessInstance> processInstanceList = runtimeService
                .createProcessInstanceQuery().processDefinitionKey("myProcess")
                .processInstanceId(processInstanceId)
                .active().list();
        if (processInstanceList.isEmpty()){
            System.out.println("Failed");
            return false;
        }


        Task task = taskService.createTaskQuery()
                .processInstanceId(processInstanceId)
                .taskAssignee(taskType)
                .active().singleResult();
        if (task == null) return false;


        LeaveRequest leaveRequest = LeaveRepo.findByprocessinstanceid(processInstanceId);
        int currentReqLevel = leaveRequest.getReqlevel();
        currentReqLevel++;
        leaveRequest.setReqlevel(currentReqLevel);

        Audit  audit = auditRepo.findByrequestsid(leaveRequest);

        if(currentReqLevel==2){
            audit.setManager1("Manager-1");
            audit.setM1(LocalDateTime.now());
            audit.setM1status(isTerminate?"Rejected":"Approved");
        }
         if( currentReqLevel == 3){
            audit.setManager2("Manager-2");
            audit.setM2(LocalDateTime.now());
            audit.setM2status(isTerminate?"Rejected":"Approved");
        }

        if(currentReqLevel ==4){
            audit.setManager3("Manager-3");
            audit.setM3(LocalDateTime.now());
            audit.setM3status(isTerminate?"Rejected":"Approved");
        }
         auditRepo.save(audit);
        LeaveRepo.save(leaveRequest);

        Map<String, Object> variables = new HashMap<>();
        variables.put("isTerminate", isTerminate);
        variables.put("id", leaveRequest.getRequest_id());
        taskService.complete(task.getId(), variables);

        return true;
    }

    public boolean isleavepending(long id){
        List<LeaveRequest> leaveRequest = leaveRepo.findByUseridAndStatus(id,"Pending");
        if(leaveRequest.isEmpty())
            return false;
        return true;
    }

    public boolean Approve(Long id) {
        LeaveRequest leaveRequest = LeaveRepo.findById(id).orElse(null);;
        leaveRequest.setStatus("Approved");
        leaveRequest.setReqlevel(4);
       LeaveBalance lb = leavebalanceRepo.findByuserid(userRepository.findById(leaveRequest.getUserid()).get());

        if(leaveRequest.getLeave_type().equals("Sick Leave"))
        lb.setSick_leave(lb.getSick_leave()-leaveRequest.getNo_days());

        if(leaveRequest.getLeave_type().equals("Casual Leave"))
            lb.setSick_leave(lb.getCasual_leave()-leaveRequest.getNo_days());

        leavebalanceRepo.save(lb);
        LeaveRepo.save(leaveRequest);
        return true;
    }

    public boolean Reject(Long id) {
        LeaveRequest leaveReq = LeaveRepo.findById(id).orElse(null);;
        leaveReq.setStatus("Rejected");
        leaveReq.setReqlevel(0);
        LeaveRepo.save(leaveReq);

        return true;
    }



}

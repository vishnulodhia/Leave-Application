package com.LeaveApplication.Leave.Model;

import java.util.Date;

public class LeaveForm {

    private Long user_id;

    private String leave_type;

    private Date start_date;

    private Date end_date;

    private String reason;

    private Integer no_days;

    public int getno_days(){
        return no_days;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getLeave_type() {
        return leave_type;
    }

    public void setLeave_type(String leave_type) {
        this.leave_type = leave_type;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "LeaveForm [user_id=" + user_id + ", leave_type=" + leave_type + ", start_date=" + start_date
                + ", end_date=" + end_date + ", reason=" + reason + "]";
    }


}

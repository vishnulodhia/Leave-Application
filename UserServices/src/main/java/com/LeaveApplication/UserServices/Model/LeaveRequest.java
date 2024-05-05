package com.LeaveApplication.UserServices.Model;


import jakarta.persistence.*;
import lombok.*;


import java.util.Date;

//@Entity
//@Table(name="users")
//@NoArgsConstructor
//@AllArgsConstructor
//@Setter
//@Getter
//@ToString
//public class Users{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long user_id;
//    @Column(nullable = false)
//    private String username;
//
//    @Column(nullable = false)
//    private String email;
//
//    @Column(nullable = false)
//    private String password;
//
//    @OneToOne
//    @JoinColumn(name = "role_id", nullable = false,referencedColumnName = "role_id")
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private Roles roles;
//
//}

@Entity
@Table(name="leave_requests")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class LeaveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long request_id;

    @Column(nullable = false, name = "user_id")
    private Long userid;

    @Column(nullable = false)
    private String leave_type;

    @Column(nullable = false)
    private Date start_date;

    @Column(nullable = false)
    private Date end_date;

    @Column(nullable = false)
    private String processinstanceid;

    @Column
    private String reason;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false,name = "req_level")
    private Integer reqlevel;

    @Transient
    private String username;

}

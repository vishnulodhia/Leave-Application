//package com.LeaveApplication.Gateway.Model;
//
//import jakarta.persistence.*;
//import lombok.*;
//import org.hibernate.annotations.OnDelete;
//import org.hibernate.annotations.OnDeleteAction;
//
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

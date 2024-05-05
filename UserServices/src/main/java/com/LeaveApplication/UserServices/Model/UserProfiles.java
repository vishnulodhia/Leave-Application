package com.LeaveApplication.UserServices.Model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="user_profile")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class UserProfiles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  profile_id;

    @Column(nullable = false)
    private String full_name;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false,referencedColumnName = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Users users;





}

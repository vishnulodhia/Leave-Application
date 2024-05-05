package com.LeaveApplication.UserServices.Model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;



@Entity
@Table(name="leave_balance")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class LeaveBalance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leave_balance_id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false,referencedColumnName = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Users userid;

    @Column(nullable = false)
    private Integer  sick_leave;

    @Column(nullable = false)
    private Integer casual_leave;


}

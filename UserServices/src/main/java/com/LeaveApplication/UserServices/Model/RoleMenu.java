package com.LeaveApplication.UserServices.Model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="rolemenu")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class RoleMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  menu_id;

    @Column(nullable = false)
    private String  menu_name;

    @Column(nullable = false)
    private String  menu_link;

    @Column(nullable = false)
    private String  menu_icon;

    @Column(nullable = false)
    private long  menu_sequence;

    @OneToOne
    @JoinColumn(name = "role_id", nullable = false,referencedColumnName = "role_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Roles roles;


}

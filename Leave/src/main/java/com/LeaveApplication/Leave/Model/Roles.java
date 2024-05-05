package com.LeaveApplication.Leave.Model;



import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="roles")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long role_id;
    @Column(nullable = false)
    private String role_name;

}

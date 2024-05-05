package com.LeaveApplication.UserServices.Model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="Subrole")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Subrole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subroleid;

    @Column(nullable = false)
    private long userid;

    @Column(nullable = false)
    private Integer level;


}
